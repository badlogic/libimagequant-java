#!/bin/bash
docker build --platform linux/amd64 -t libimagequant-docker . && docker run --platform linux/amd64 --rm -v "$(pwd)/..:/code" -it libimagequant-docker