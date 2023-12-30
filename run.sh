#!/bin/bash

BASEDIR=$(dirname $0)
cd $BASEDIR

if [ ! -d $BASEDIR/logs ]; then
  mkdir $BASEDIR/logs
fi

mysqld_multi start 2

DATETIME=$(date +%F_%H-%M-%S)

mkdir $BASEDIR/logs/$DATETIME

$BASEDIR/gradlew clean >> $BASEDIR/logs/$DATETIME/build.log
$BASEDIR/gradlew build >> $BASEDIR/logs/$DATETIME/build.log

java -Dspring.profiles.active=prod -jar -Xms128m -Xmx128m $BASEDIR/backend/build/libs/backend-0.1.0-SNAPSHOT.jar >> $BASEDIR/logs/$DATETIME/backend.log &

cd $BASEDIR/frontend
yarn prod >> $BASEDIR/logs/$DATETIME/frontend.log
