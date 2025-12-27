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

    // apenas vê se uma palavra é reservada
    // vendo pelo o array
    public static boolean palavraReservada (String palavra) {

        final String[] PalavrasReservadas = {
            "int",
            "str",
            "char",
            "bool",
            "float",
            "if",
            "else",
            "elif",
            "return",
            "continue",
            "for",
            "while",
            "do",
            "fun",
            "imp",
            "try",
            "catch",
            "imut",
            "var",
            "true",
            "false",
            "null",
            "new",
            "del",
            "void",
            "exp",
            "class"
        };

        for (String p : PalavrasReservadas) {
            if (palavra.equals(p)) {
                return true;
            }
        }

        return false;
    }

    // função para separar
    public void Separar () {

        // tamanho de caracteres da linha
        int Tamanho = this.LinhaSeparada.length();

        // caracter atual
        int c = 0;

        // para percorrer a string
        while (c < Tamanho) {

            // variaveis base
            char caracter = this.LinhaSeparada.charAt(c);

            // pula algum espaço
            if (Character.isSpaceChar(caracter)) {
                c++;
                continue;
            }

            // se for uma letra
            else if (Character.isLetter(caracter) || caracter == '_') {

                // string builder
                StringBuilder SB = new StringBuilder();

                // enquanto for uma letra ou digito     ou      for o caracter '_'
                while ( c < Tamanho && (Character.isLetterOrDigit(this.LinhaSeparada.charAt(c)) || this.LinhaSeparada.charAt(c) == '_')) {
                     
                    // adiciona o próximo caracter a string 
                    SB.append(this.LinhaSeparada.charAt(c++));
                }

                // palavra armazenada
                String palavra = SB.toString();
                // verifica se é uma palavra reservada
                if (palavraReservada(palavra)) {
                    
                    // adiciona o token
                    Token  T = new Token();
                    T.setConteudo(palavra);
                    T.setTipo(Token.EnumTipo.PALAVRA_RESERVADA);
                    lista.add(T);

                    // pula o código para a próxima execução
                    continue;
                }

                // adiciona o token
                Token  T = new Token();
                T.setConteudo(palavra);
                T.setTipo(Token.EnumTipo.PALAVRA);
                lista.add(T);

                // pula o código para a próxima execução
                continue;
            }

            else if (Character.isDigit(caracter)) {

                // contem ponto
                boolean FLOAT = false;

                // string builder
                StringBuilder SB = new StringBuilder();

                // enquanto for uma letra ou digito     ou      for o caracter '.'
                while ( c < Tamanho && (Character.isDigit(this.LinhaSeparada.charAt(c)) || (this.LinhaSeparada.charAt(c) == '.' && !FLOAT))) {
                     
                    // define como ponto flutuante
                    if (this.LinhaSeparada.charAt(c) == '.') FLOAT = true;

                    // adiciona o próximo caracter a string 
                    SB.append(this.LinhaSeparada.charAt(c++));

                }

                // num armazenado
                String numero = SB.toString();

                // é um numero com ponto
                if (FLOAT) {
                    Token  T = new Token();
                    T.setConteudo(numero);
                    T.setTipo(Token.EnumTipo.FLOAT);
                    lista.add(T);
                    continue;
                }

                Token  T = new Token();
                T.setConteudo(numero);
                T.setTipo(Token.EnumTipo.INT);
                lista.add(T);
                continue;
            }

            
            // operadores
            // ======================================
            // ======================================
            if (caracter == '=') {

                if (this.LinhaSeparada.charAt(c+1) == '=') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.IGUAL_COMPARACAO);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.IGUAL);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '+') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.MAIS);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '-') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.MENOS);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '/') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.DIVISAO);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '*') {

                if (this.LinhaSeparada.charAt(c+1) == '*') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.EXPOENTE);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.MULTI);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '&') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.E);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '|') {
            
                if (this.LinhaSeparada.charAt(c+1) == '|') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.OU);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.PIPE);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '>') {

                if (this.LinhaSeparada.charAt(c+1) == '=') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.MAIOR_IGUAL);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.MAIOR);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '<') {

                if (this.LinhaSeparada.charAt(c+1) == '=') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.MENOR_IGUAL);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.MENOR);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '^') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.CIRCUNFLEXO);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '"') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.ASPAS_DUPLAS);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '\'') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.ASPAS_SIMPLES);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '\\') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.CONTRA_BARRA);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '.') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.PONTO);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == ',') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.VIRGULA);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '(') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.ABRE_PAR);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == ')') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.FECHA_PAR);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '{') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.ABRE_CHAVES);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '}') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.FECHA_CHAVES);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '[') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.ABRE_COLC);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == ']') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.FECHA_COLC);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '?') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.INTERROGACAO);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == ':') {
                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.DOIS_PONTOS);
                lista.add(T);
                c++;
                continue;
            }

            else if (caracter == '!') {

                if (this.LinhaSeparada.charAt(c+1) == '=') {
                    Token  T = new Token();
                    T.setConteudo("" + caracter);
                    T.setTipo(Token.EnumTipo.DIFERENTE);
                    lista.add(T);
                    c+=2;
                    continue;
                }

                Token  T = new Token();
                T.setConteudo("" + caracter);
                T.setTipo(Token.EnumTipo.NAO);
                lista.add(T);
                c++;
                continue;
            }

            // ======================================
            // ======================================
            // operadores


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