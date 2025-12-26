// define a classe de um token para ser usado no separador
public class Token {

    // tipagem
    public static enum EnumTipo {
        CHAR,
        INT, 
        VOID,
        STR,
        BOOL, 
        FLOAT,
        PALAVRA,
        PALAVRA_RESERVADA,
        ABRE_PAR,
        FECHA_PAR,
        ABRE_CHAVES,
        FECHA_CHAVES,
        ABRE_COLC,
        FECHA_COLC,
        PONTO,
        VIRGULA,
        INTERROGACAO,
        DOIS_PONTOS,
        IGUAL,
        MAIS,
        MENOS, 
        DIVISAO,
        MULTI,
        E,
        PIPE,
        OU,
        NAO,
        MAIOR,
        MAIOR_IGUAL,
        MENOR,
        MENOR_IGUAL,
        DIFERENTE, 
        IGUAL_COMPARACAO,
        CIRCUNFLEXO,
        EXPOENTE,
        ASPAS_DUPLAS,
        ASPAS_SIMPLES,
        CONTRA_BARRA
    }

    // atributos do token
    private String Conteudo;
    private EnumTipo Tipo;

    // seters
    public void setConteudo (String c) {
        this.Conteudo = c;
    }
    public void setTipo (EnumTipo t) {
        this.Tipo = t;
    }

    // resgatar todo o token
    public String getConteudo () {
        return this.Conteudo;
    }
    public EnumTipo getTipo () {
        return this.Tipo;
    }
}