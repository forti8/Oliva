#!/bin/bash
set -e

# cria o bin
mkdir -p bin

# coloca as .class no bin
javac -d bin src/*.java

# executa a index
# "$@" passa os argumentos
java -cp bin index "$@"