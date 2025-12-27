package compiler.command;

public class Command {
    public enum FunctionEnum {
        CALL,
        DEFINE,
        PUT,
        DELETE
    }

    public FunctionEnum function;
}