package compiler.commands;
import compiler.libs.memo.Memory;

public class Variable {

    public enum varType {
        INT, FLOAT, BOOL, CHAR, STR
    }

    public String variableName;
    public varType variableType;
    private long memoryLocation;
    private int memorySize = 0;

    public void SetMemorySize (int memoSize) {
        this.memorySize = memoSize;
    }
   
    public Object call () {

        Object memoValue;
        switch (this.variableType) {
            case STR:
                StringBuilder sb = new StringBuilder();
                long currentPos = this.memoryLocation;
                char c;
                
                while ((c = Memory.ReadChar(currentPos)) != '\0') {
                    sb.append(c);
                    currentPos++;
                }

                memoValue = sb.toString();
            break;

            case INT:
                memoValue = Memory.ReadInt(this.memoryLocation);
            break;

            case FLOAT:
                memoValue = Memory.ReadFloat(this.memoryLocation);
            break;

            case CHAR:
                memoValue = Memory.ReadChar(this.memoryLocation);
            break;

            case BOOL:
                memoValue = Memory.ReadBool(this.memoryLocation);
            break;

            default:
                memoValue = null;
        }

        return memoValue;
    } 


    /*
        mudança necessaria para definir o tamanho em bytes
        criar a função readByte e writeByte
    */
    public void define (Object value) {

        int sz = 1;
        if (value != null) {
            switch (this.variableType) {
                case STR:
                    String str = (String) value;
                    byte[] bytes = str.getBytes();
    
                    this.memoryLocation = Memory.Alloc(bytes.length + 1);
                    for (int i = 0; i < bytes.length; i++) {
                        Memory.WriteChar(this.memoryLocation + i, (char) bytes[i]);
                    }
    
                    Memory.WriteChar(this.memoryLocation + bytes.length, '\0');
                break;
    
                case INT:
                    sz = this.memorySize > 0 ? this.memorySize : 4; 
                    System.out.println(sz);
                    this.memoryLocation = Memory.Alloc(sz);
                    Memory.WriteInt(this.memoryLocation, (int) value);
                break;
    
                case FLOAT:
                    sz = this.memorySize > 0 ? this.memorySize : 4; 
                    System.out.println(sz);
                    this.memoryLocation = Memory.Alloc(sz);
                    Memory.WriteFloat(this.memoryLocation, (float) value);
                break;
    
                case CHAR:
                    this.memoryLocation = Memory.Alloc(1);
                    Memory.WriteChar(this.memoryLocation, (char) value);
                break;
    
                case BOOL:
                    this.memoryLocation = Memory.Alloc(1);
                    Memory.WriteBool(this.memoryLocation, (boolean) value);
                break;
    
                default:
            }
        }

        else {
            switch (this.variableType) {
                case STR:
                    sz = this.memorySize > 0 ? this.memorySize : 1; 
                    this.memoryLocation = Memory.Alloc(sz + 1);
                    Memory.WriteChar(this.memoryLocation + sz, '\0');
                break;
    
                case INT:
                    sz = this.memorySize > 0 ? this.memorySize : 4; 
                    System.out.println(sz);
                    this.memoryLocation = Memory.Alloc(sz);
                break;
    
                case FLOAT:
                    sz = this.memorySize > 0 ? this.memorySize : 4; 
                    this.memoryLocation = Memory.Alloc(sz);
                break;
    
                case CHAR:
                    this.memoryLocation = Memory.Alloc(sz);
                break;
    
                case BOOL:
                    this.memoryLocation = Memory.Alloc(sz);
                break;
    
                default:
            }
        }
        
        return;
    }

    public void put (Object value) {
        switch (this.variableType) {
            case STR:
                Memory.Free(this.memoryLocation);
                String str = (String) value;
                byte[] bytes = str.getBytes();

                this.memoryLocation = Memory.Alloc(bytes.length + 1);
                for (int i = 0; i < bytes.length; i++) {
                    Memory.WriteChar(this.memoryLocation + i, (char) bytes[i]);
                }

                Memory.WriteChar(this.memoryLocation + bytes.length, '\0');
            break;

            case INT:
                Memory.WriteInt(this.memoryLocation, (int) value);
            break;

            case FLOAT:
                Memory.WriteFloat(this.memoryLocation, (float) value);
            break;

            case CHAR:
                Memory.WriteChar(this.memoryLocation, (char) value);
            break;

            case BOOL:
                Memory.WriteBool(this.memoryLocation, (boolean) value);
            break;


            default:
        }
        return;
    }

    public void delete () {
        Memory.Free(this.memoryLocation);
        this.memoryLocation = -1L;
        return;
    }

    public long getMemoryLocation () {
        return this.memoryLocation;
    }
}