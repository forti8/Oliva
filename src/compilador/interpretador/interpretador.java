package compilador.interpretador;
import compilador.token.Token; 
import java.util.ArrayList;
import java.util.List;

public class interpretador {
    private List<Token> TokensDaLinha = new ArrayList<Token>();

    public interpretador (List<Token> lista) {
        super();
        this.TokensDaLinha = lista;
    }

    public void Ativar () {
        for (int i = 0; i < this.TokensDaLinha.size(); i++) {
            Token tokenAtual = this.TokensDaLinha.get(i);

            
        }
    }
}