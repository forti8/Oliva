// importando  os pacotes necessarios para a leitura do arquivo
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public int Ler () throws IOException {

        // define uma instância para um file reader
        FileReader FR = new FileReader(this.caminho);

        // define uma instância para um buffer reader
        BufferedReader BR = new BufferedReader(FR);

        // armazena a linha atual
        String Linha;

        // enquanto a linha não é nula
        while ((Linha = BR.readLine()) != null) {

            // retorna a linha (provisório)
            System.out.println(Linha);
        }

        BR.close();

        // retorna 0 para sucesso
        // e retorna 1 para falha
        return 0;
    }
}