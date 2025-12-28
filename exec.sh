#!/bin/bash
set -e

# execute javac
java -cp bin -Djava.library.path=./src/libs/ index "$@"