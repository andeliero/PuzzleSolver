#!/bin/bash

if [[ -z $1  || -z $2 || -z $3 ]]; then
    echo "[ERRORE] parametri assenti, chiusura applicazione.";
    exit;
else
    make Client
	echo "[ ] file di input: "$1;
	echo "[ ] file di output: "$2;
	echo "[ ] server di riferimento: "$3;
	java -cp bin/Client PuzzleSolverClient $1 $2 $3
fi