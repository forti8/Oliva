// importando  os pacotes necessarios para a leitura do arquivo
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
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

                    // inicia o parsing
                    Separador SepararLinha = new Separador(Linha);
                    SepararLinha.Separar();
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
            System.out.println("File not found exception "+ e.getMessage());
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

    // copiar a instância para o conteudo de outro arquivo
    public int CopiarPara (String Destino) {

        // define uma instância para um file reader
        // tenta o file reader
        try (FileReader FR = new FileReader(this.caminho)) {

            // define uma instância para um buffer reader
            BufferedReader BR = new BufferedReader(FR);
    
            // tenta o buffer read
            try (BR) {
    
                // armazena a linha atual
                String Linha;

                // tenta escrever usando o bufferedWritter
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(Destino))) {
    
                    // enquanto a linha não é nula
                    while ((Linha = BR.readLine()) != null) {
                        bw.write(Linha);
                        bw.newLine();
                    }
                } 
                
                // caso de uma exception
                catch (IOException e) {
                    System.out.println("Error writing file.");
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

    // retorna a primeira posição onde aparece a ocorrencia
    public int ProcurarPor (String procura) {

        // tenta um scanner
        try {

            // instância um scanner
            Scanner scanner = new Scanner(new File(this.caminho));

            // armazena a linha da atual
            int numLinha = 0;

            // enquanto tiver uma proxima linha
            while (scanner.hasNextLine()) {

                // vai para proxima linha
                String linha = scanner.nextLine();
                numLinha++;

                // se a linha tiver o conteudo 
                if (linha.contains(procura)) {
                    scanner.close();
                    return numLinha;
                }
            }

            scanner.close();
            return -1;
        } 
        
        // caso da exception de file n found
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

        return -1;
    }

    // ler uma linha exepecifica do arquivo
    public String LerLinha (int LinhaDesejada) {
        
        // tenta File reader
        try (FileReader FR = new FileReader(this.caminho)) {

            // tenta buffer reader
            try (BufferedReader BR = new BufferedReader(FR)) {

                // armazena a linha
                String linha;
                int linhaAtual = 0;
    
                // funciona enquanto a linha não é nula ou se achar a linha
                while ((linha = BR.readLine()) != null) {
                    
                    // inicia a linha
                    linhaAtual++;
    
                    // verifica se é a linha desejada
                    if (linhaAtual == LinhaDesejada) {
                        return linha;
                    }  
                }
            }

            // exception de input ou output
            catch (IOException e) {
                System.out.println(e.toString());
            }            
        }

        // caso da exception de file n found
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        // exception de input ou output
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return "";
    }
}