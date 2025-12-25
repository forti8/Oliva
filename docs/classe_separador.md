#### Separador
A classe Arquivo é definida em `src/Separador.java`, ela serve para separar de maneira lexica o código analizando palavras e simbolos e as transformando em tokens.

##### Atributos
`(construtor) String LinhaSeparada` - Essa string é a linha que sera separada na execução do metodo `separar()`. 
`List<Token> lista` - É a lista de tokens analisados e fornecidos na linha, ela tem seu valor inicializado como `new ArrayList<>()` mas esse valor é alterado após a execução do metodo `separar()`. 

##### Métodos
`void separar()`- Separa de maneira a lexica a linha fornecida pelo executor, esse metodo não possui retorno  direto pois ele apenas manipula o atributo privado `lista`.
`int tamanhoLista()` - Retorna um numero inteiro que é definido como sendo o `length` do atributo `lista`.
`void getLista()` - Apenas retorna todo o conteudo do atributo `lista`, isso é, apenas serve como getter. 

#### Token
Para que o separador funcione como o esperado, é necessário a definição da `classe Token`.

A classe token possui apenas dois atributos sendo eles `(construtor) String Conteudo` que vai armazenar o conteudo do token, exemplo com a palavra `print`:

**Palavra: print**
O conteudo seria a própria palavra, ele é apenas a `String` o texto bruto

Seu outro atributo é o `(construtor) int Tipo` que vai armazenar o tipo do Token, seguindo o exemplo *anterior*, o tipo da palavra *print* poderia ser `comando` que seria representado por um numero inteiro, exemplo: o número `0`
