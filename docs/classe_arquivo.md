#### Arquivo
A classe Arquivo é definida em `src/Arquivo.java` e ela possui o unico propósito de Ler, escrever ... em geral seu propósito é manipular arquivo `.ol`, `.config.ol` e `.txt`

##### Atributos
`(construtor) String caminho` - ele é definido como o **Argumento de Caminho** (revise o arquivo ***arguments.md***) 
d

##### Métodos
`void Ler()`- Este método inicia a leitura do arquivo, não exige nenhum parâmetro em sua requisição
`void CopiarPara( String Destino )`- Este metodo permite ao arquivo copiar seu próprio conteudo e em seguida insiro-lo em outro arquivo, o destino da copia é definido como `String Destino`
`int ProcurarPor( String Procura )`- Este metodo procura por uma ocorrência, quando achada a ocorrência em seu arquivo o sistema retorna a linha onde a ocorrência foi encontrada e em caso de erro ou caso não seja encontrada o retorno da função é `-1`
`String lerLinha ( int Linha )` - Este metodo recebe um numero inteiro que é a posição (linha) que quer ser lida, então, ele acessa o arquivo e encontra a mesma, seu retorno sera a linha completa ou em caso de erro `String vázia`