package compiler.commands;
import compiler.command.Command;

public class Variable extends Command {

    private String variableName;
    private String variableValue;
    private long memoryLocation;
    
    public Variable (FunctionEnum function, String name, String value) {
        super();
        this.function = function;
        this.variableName = name;
        this.variableValue = value;
    }
    
    public void execute () {
        switch (this.function) {
            case CALL: call();
            break;
            case DEFINE: define();
            break;
            case PUT: put();
            break;
            case DELETE: delete();
            break;

            default:
                System.out.println("unrecognized function");
            break;
        }
    }

    private void call () {
        return;
    } 
    private void define () {
        return;
    }
    private void put () {
        return;
    }
    private void delete () {
        return;
    }
}