#!/usr/bin/env bash

name="jnp-3-game-service"

docker image rm "$name" -f
docker build -t "$name" .
docker run -it -p 8080:8080 "$name"
