#!/bin/bash
docker build -t libimagequant-docker . && docker run --rm -v "$(pwd)/..:/code" -it libimagequant-docker