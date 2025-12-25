## Pastas
#### /SRC
Contém todos os códigos necessários para que o sistema funcione, em outras palavras ela serve como uma pasta raiz.
##### Sub-diretórios
- `/sh/` guarda arquivos bash/shell que são chamados pelo código para criar estruturas no código (pastas, arquivos e etc).
- `/models/` armazena arquivos modelos que serão usados para criar um novo arquivo, como exemplo o arquivo config.txt será **"copiado"** como o arquivo .config.ol.
- `/compilador/` os arquivos que são responsaveis pela compilação da linguagem serão armazenados aqui;

#### /CONFIGS
Contém arquivos de configuração da linguagem, a pasta configs é criada automaticamente ao executar o Argumento de Execução `-config-file`, o mesmo comando adiciona automaticamente a pasta `/configs/` um arquivo `.config.ol`.

#### /TEST
Arquivos `.ol` para teste de desenvolvimento são guardados aqui, está pasta não é responsável por nenhuma função expecífica apenas é uma convensão para manter o ambiente de desenvolvimento mais limpo

#### /DOCS
Este diretório guarda toda a documentação da linguagem

#### /BIN
Este diretório é criado a partir da primeira execução da linguagem e apenas é respondavel por armazenar arquivo `.class` para que o **Java** execute o programa 