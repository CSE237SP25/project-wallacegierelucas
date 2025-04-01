#!/bin/bash 

javac bankapp/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed. Please check your code for errors."
    exit 1
fi


java bankapp.Main
