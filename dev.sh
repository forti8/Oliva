#!/bin/bash
set -e

rm -rf bin
mkdir -p bin

# resto do compilador
javac -cp bin -d bin src/compilador/libs/memo/*java
javac -cp bin -d bin src/compilador/*/*.java
javac -cp bin -d bin src/utils/*/*.java
javac -cp bin -d bin src/index.java
java -cp bin -Djava.library.path=./src/libs/ index "$@"