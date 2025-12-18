import java.util.ArrayList;
import java.util.List;

// classe de um separador
public class Separador {

    // atributo que armazena a linha a ser separada
    private String LinhaSeparada;

    // função para separar
    public static Token[] Separar () {

        // array que vai ser retornado
        List<Token> lista = new ArrayList<>();

        // retorna como esperado
        return lista.toArray(new Token[0]);
    }
}