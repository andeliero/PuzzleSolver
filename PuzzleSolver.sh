#!/bin/bash

if [[ -z $1  || -z $2 ]]; then
    echo "[ERRORE] parametri assenti, chiusura applicazione.";
    exit;
else
    make
	echo "file di input: "$1;
	echo "file di output: "$2;
	java -cp bin PuzzleSolver $1 $2
fi
