package compilador.commands;
import compilador.comando.Comando;

public class Variable extends Comando {

    private String NomeVariavel;
    private String ValorVariavel;
    private long localMemoria;
    
    public Variable (EnumFuncao funcao, String nome, String valor) {
        super();
        this.func = funcao;
        this.NomeVariavel = nome;
        this.ValorVariavel = valor;
    }
    
    public void EXE () {
        switch (this.func) {
            case CALL: CALL();
            break;
            case DEFINE: DEF();
            break;
            case PUT: PUT();
            break;
            case DELETE: DEL();
            break;

            default:
                System.out.println("função não reconhecida");
            break;
        }
    }

    private void CALL () {
        return;
    } 
    private void DEF () {
        return;
    }
    private void PUT () {
        return;
    }
    private void DEL () {
        return;
    }
}