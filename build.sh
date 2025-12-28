#!/bin/bash
set -e

rm -rf bin
mkdir -p bin

# rest of the compiler
javac -cp bin -d bin src/compiler/libs/memo/*java
javac -cp bin -d bin src/compiler/*/*.java
javac -cp bin -d bin src/utils/*/*.java
javac -cp bin -d bin src/index.java
java -cp bin -Djava.library.path=./src/libs/ index "$@"