// importando  os pacotes necessarios para a leitura do arquivo
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

// classe de um arquivo
public class Arquivo {

    // atributo privado do caminho do arquivo
    private String caminho;

    // construtor do arquivo
    public Arquivo (String caminho) {
        super();
        this.caminho = caminho;
    }

    // membro estático de leitura
    public int Ler () {

        // define uma instância para um file reader
        // tenta o file reader
        try (FileReader FR = new FileReader(this.caminho)) {

            // define uma instância para um buffer reader
            BufferedReader BR = new BufferedReader(FR);
    
            // tenta o buffer read
            try (BR) {
    
                // armazena a linha atual
                String Linha;
    
                // enquanto a linha não é nula
                while ((Linha = BR.readLine()) != null) {
        
                    // retorna a linha (provisório)
                    System.out.println(Linha);
                }
        
                // fecha o arquivo
                BR.close();
            }
    
            // caso de uma exception
            catch (IOException e) {
                System.out.println(e.toString());
                return 1;
            }
        }

        // exception do arquivo não encrontrado
        catch (FileNotFoundException e) {
            System.out.println("File not found exception");
            return 1;
        }
        
        // IOException
        catch (IOException e) {
            System.out.println(e.toString());
            return 1;
        }

        // retorna 0 para sucesso
        // e retorna 1 para falha
        return 0;
    }
}