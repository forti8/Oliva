import java.util.ArrayList;
import java.util.List;

// classe de um separador
public class Separador {

    // atributo que armazena a linha a ser separada
    private String LinhaSeparada;
    private List<Token> lista = new ArrayList<>();

    // construtor
    public Separador (String LinhaSeparada) {
        super();
        this.LinhaSeparada = LinhaSeparada;
    }

    // função para separar
    public void Separar () {

        // tamanho de caracteres da linha
        int Tamanho = this.LinhaSeparada.length();

        // caracter atual
        int c = 0;

        // para percorrer a string
        while (c < Tamanho) {

            // token atual
            Token TA = new Token();
            char caracter = this.LinhaSeparada.charAt(c);
            boolean b;

            // se for uma letra
            if ((b = Character.isLetter(caracter)) || caracter == '_') {

                // inicia uma palavra 
                String palavra = "" + caracter;

                // pega o próximo caracter
                char proximoCaracter = this.LinhaSeparada.charAt(++c);

                // enquanto for uma letra ou digito     ou      for o caracter '_'
                while ((b = Character.isLetterOrDigit(proximoCaracter)) || proximoCaracter == '_') {
                     
                    // adiciona o próximo caracter a string 
                    palavra += proximoCaracter;

                    // define o próximo caracter
                    proximoCaracter = this.LinhaSeparada.charAt(++c);
                }

                // log (temporário)
                System.out.println(palavra);

                // pula o código para a próxima execução
                continue;
            }

            else {

                // log (temporário)
                System.out.println(caracter);
            }

            // pula para o proximo caracter
            c++;
        }
    }

    // verifica se o metodo separar adicionou algo a lista
    // ou apenas usado para ver se a lista esta vázia
    public int tamanhoLista () {
        return this.lista.size();
    } 

    // geter
    public List<Token> getLista () {
        return this.lista;
    }
}