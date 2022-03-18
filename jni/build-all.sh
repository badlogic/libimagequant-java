#!/bin/bash
set -e

./build-imagequant.sh
./build.sh --target=macosx
./build-docker.sh
