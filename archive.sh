#!/bin/bash

display_help() {
	echo "Command used to archive multiple files"
	echo "Usage ./archive.sh archive_name location1 location2 ..."
	echo "-h or --help -> display this message"
}

display_error() {
	echo "Error: Expected at least one parameter"
        echo "./archive.sh --help for more information"
        exit
}

if [ -z $1 ]
then
	display_error
fi

if [ $1 = '-h' -o $1 = '--help' ]
then
	display_help
	exit
fi

if [ -z $2 ]
then
	display_error
fi

zip -r "Archives/$1" "${@:2}"
