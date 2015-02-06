#!/bin/bash

if [[ -z $1 ]]; then
    echo "[ERRORE] parametri assenti, chiusura applicazione.";
    exit;
else
    make Server
	echo "[ ] server di riferimento: "$1;
	echo "[ ] Ucisione processo rmiregistry "
	killall rmiregistry
	cd bin/Server; rmiregistry&  java PuzzleSolverServer $1
	killall rmiregistry
fi