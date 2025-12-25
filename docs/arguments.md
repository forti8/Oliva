#### Os argumentos da OLiLang são divididos em três tipos
    - argumentos de informação
    - argumentos de execução
    - argumento de caminho

#### Argumentos de Informação [?opcional]
São todos argumentos que tem como retorno uma **informação interna ou sobre a linguagem**

#### Argumentos de Execução [?opcional]
São responsáveis pelas **configurações** da linguagem, permitem que o usuario  planeje como será o funcionamento da linguagem

#### Argumento de caminho [*Obrigatório]
Esse argumento é obrigatório para a execução bem sucedida do sistema sua unica função é prover o caminho até o arquivo `.oli`

##### caminhos possíveis para o arguemento
- caminho fixo
- caminho relativo
- nome do arquivo (se estiver na pasta em que o terminal está executando)

### Argumentos de Informação

##### Help
`--help` retorna a lista de comandos, além de retornar os comandos com paramêtros

##### Version
`--version` retorna a versão atual da linguagem
 