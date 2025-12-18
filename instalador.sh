#!/bin/bash
# Faz o script parar imediatamente se algum comando falhar
set -e

# Cria a pasta bin se não existir (onde ficarão os .class)
mkdir -p bin

# Compila todos os arquivos Java de src/ e joga os .class em bin/
javac -d bin src/*.java

# Cria o JAR executável
# - c = create
# - f = file
# - e = entry point (classe com main)
# Index é a classe principal
jar cfe oliva.jar Index -C bin .

# Move o JAR para um local padrão do sistema
sudo mv oliva.jar /opt/oliva.jar

# Copia o launcher (script oliva) para o PATH do sistema
sudo cp oliva /usr/local/bin/oliva

# Dá permissão de execução ao comando oliva
sudo chmod +x /usr/local/bin/oliva