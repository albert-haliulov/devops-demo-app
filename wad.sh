#!/bin/bash
BASEDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

docker stop devops-demo-app || true

docker run -d --rm \
    --name devops-demo-app \
    -p 9080:9080 \
    -v $BASEDIR/src/main/liberty/config/:/config/ \
    -v $BASEDIR/wad-dropins/:/config/dropins/ \
    open-liberty:latest

java -jar $BASEDIR/wad.jar $BASEDIR/wad-dropins/