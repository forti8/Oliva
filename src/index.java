import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder;
import java.lang.InterruptedException;

// classe index
public class index {

    // para interpretar um numero como boolean
    public static boolean bool(int numero, boolean not) {

        // variavel que vai armazenar o retorno
        boolean retorno = true;

        // se for um numero igual de zero é false
        if (numero == 0) {

            // caso seja zero significa que é falso
            retorno = false;
        }
        
        // se precisa inverter o retorno
        if (not) return !retorno;

        // caso não precise
        return retorno;
    }

    // metodo main reponsável pela inicialização do sistema no java
    public static void main(String[] args) {

        // prefixo de argumento de execução
        final String PrefixoDeExecucao = "-";
        
        // prefixo de argumento de informação
        final String PrefixoDeInformacao = "--";
        
        // extensão valida para arquivo OliLang
        final String ExtensaoValida = ".ol";
        
        // caminho do arquivo executado, por padrão index.ExtensãoVálida
        String Caminho = "index" + ExtensaoValida;

        // para cada argumento contido em args
        for (String Argumento : args) {
            
            // verifica o prefixo
            // prefixo de informação : help, version ...
            if (Argumento.startsWith(PrefixoDeInformacao)) {
                
                // remove o prefixo e transforma o texto em lowercase
                Argumento = Argumento.replaceFirst(PrefixoDeInformacao, "");
                Argumento.toLowerCase();
            }

            // prefixo de execucao : configurações
            else if (Argumento.startsWith(PrefixoDeExecucao)) {

                // remove o prefixo
                Argumento = Argumento.replaceFirst(PrefixoDeExecucao, "");
                Argumento.toLowerCase();

                // -config-file
                if (bool(Argumento.compareTo("config-file"), true)) {

                    // novo arquivo de configuração .config.ol
                    File NovoArquivoDeConfig  = new File(".config.ol");
                    
                    // verifica se o arquivo existe
                    if (!(NovoArquivoDeConfig.exists())) {

                        // se não existir tenta criar
                        try {

                            // cria o arquivo
                            NovoArquivoDeConfig.createNewFile();
                        }
                        
                        // caso de uma exception
                        catch (IOException e) {
                            System.out.println(e.toString());
                        }
                    }


                    // tenta criar o processo
                    try {

                        // cria uma instância de processo
                        Process p = new ProcessBuilder("sh", "src/sh/config.sh").start();
                        p.waitFor();
                    } 
                    
                    // caso de alguma das duas exceptions
                    catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            // sem nenhum prefixo caminho do arquivo
            else {

                // o caminho nada mais é que o argumento
                // se o arquivo for de uma extensão valida
                if (Argumento.endsWith(ExtensaoValida)) Caminho = Argumento;
            }

            
        }

        // instância que vai armazenar o arquivo que será lido
        Arquivo ArquivoLido = new Arquivo(Caminho);

        // tenta efetuar a leitura do arquivo
        ArquivoLido.Ler();
    }
}