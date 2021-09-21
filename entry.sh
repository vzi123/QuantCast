#!/bin/sh

usage() {
  echo "
  Usage: bash entry.sh [OPTIONS]

  -f            input1.csv read the file from /path/to/inputfiles folder which was mounted in docker
  -d            2018-12-09 accepts the date in yyyy-mm-dd format
  -h            [optional] Used for skipping the first line if header is present
  -failfast     [optional] Used for skipping the bad data and proceed with other records in the file
"
exit 0
}

args="$@"

optspec="h?f:d"

while getopts ":f:d:" opt; do

  case "${opt}" in
  f)
    filename=$OPTARG
    ;;
  d)
    date=$OPTARG
    ;;
  esac

done
shift $((OPTIND-1))

if [ -z $filename ]; then
   echo "please provide filename"
   usage
  exit  0

fi


if [ -z $date ]; then
    echo "please provide date"
       usage
      exit  0
fi

java -jar target/QuantCast_Assignment-2.3-SNAPSHOT.jar -f $filename -d $date
