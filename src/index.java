// classe index
public class index {

    // metodo main reponsável pela inicialização do sistema no java
    public static void main(String[] args) {

        // prefixo de argumento de execução
        String PrefixoDeExecucao = "-";

        // prefixo de argumento de informação
        String PrefixoDeInformacao = "--";

        // para cada argumento contido em args
        for (String Argumento : args) {

            // transforma todo argumento em lowercase
            Argumento.toLowerCase();
            
            // verifica o prefixo
            // prefixo de informação : help, version ...
            if (Argumento.startsWith(PrefixoDeInformacao)) {
                System.out.println("prefixo de inf");
            }

            // prefixo de execucao : configurações
            else if (Argumento.startsWith(PrefixoDeExecucao)) {
                System.out.println("prefixo de exec");
            }

            // sem nenhum prefixo caminho do arquivo
            else {
                System.out.println("caminho");
            }

            System.out.println(Argumento);
        }
    }
}