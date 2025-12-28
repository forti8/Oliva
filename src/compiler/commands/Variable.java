package compiler.commands;
import compiler.libs.memo.Memory;

public class Variable {

    public enum varType {
        INT, FLOAT, BOOL, CHAR
    }

    public String variableName;
    public varType variableType;
    private long memoryLocation;
   
    public void call () {

        return;
    } 

    public void define (Object value) {
        switch (this.variableType) {
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

        
        return;
    }

    public void put () {
        return;
    }

    public void delete () {
        return;
    }

    public long getMemoryLocation () {
        return this.memoryLocation;
    }
}