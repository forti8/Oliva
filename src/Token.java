// define a classe de um token para ser usado no separador
public class Token {

    // atributos do token
    private String Conteudo;
    private int Tipo;

    // setar todo o token
    public void setConteudo (String Conteudo) {
        this.Conteudo = Conteudo;
    } 
    public void setTipo (int Tipo) {
        this.Tipo = Tipo;
    }

    // resgatar todo o token
    public String getConteudo () {
        return this.Conteudo;
    }
    public int getTipo () {
        return this.Tipo;
    }
}