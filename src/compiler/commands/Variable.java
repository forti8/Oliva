package compiler.commands;
import compiler.libs.memo.Memory;

public class Variable {

    public enum varType {
        INT, FLOAT, BOOL, CHAR, STR
    }

    public String variableName;
    public varType variableType;
    private long memoryLocation;
   
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

    public void define (Object value) {

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
                    this.memoryLocation = Memory.Alloc(4);
                    Memory.WriteInt(this.memoryLocation, (int) value);
                break;
    
                case FLOAT:
                    this.memoryLocation = Memory.Alloc(4);
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
                    String str = "";
                    byte[] bytes = str.getBytes();
    
                    this.memoryLocation = Memory.Alloc(bytes.length + 1);
                    Memory.WriteChar(this.memoryLocation + bytes.length, '\0');
                break;
    
                case INT:
                    this.memoryLocation = Memory.Alloc(4);
                break;
    
                case FLOAT:
                    this.memoryLocation = Memory.Alloc(4);
                break;
    
                case CHAR:
                    this.memoryLocation = Memory.Alloc(1);
                break;
    
                case BOOL:
                    this.memoryLocation = Memory.Alloc(1);
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