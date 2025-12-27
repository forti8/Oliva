package compilador.comando;

public class Comando {
    public enum EnumFuncao {
        CALL,
        DEFINE,
        PUT,
        DELETE
    }

    public EnumFuncao func;
}