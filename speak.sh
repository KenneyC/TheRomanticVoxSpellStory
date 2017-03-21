#!/bin/bash

word=$1
voice=$2

echo "(voice_$voice)" >> newspeech.txt
echo "(SayText \"$word\")" >> newspeech.txt
echo "(exit)" >> newspeech.txt

festival -b newspeech.txt

rm newspeech.txt