#!/bin/bash
set -e

# cria o bin
mkdir -p bin
cd bin
mkdir -p configs compilador
cd ..

# coloca as .class no bin
javac -d bin src/*.java

# javac -d bin/configs src/configs/*.java
# javac -d bin/compilador src/compilador/*.java

# executa a index
# "$@" passa os argumentos
java -cp bin index "$@"