#!/bin/bash
set -e

./build-imagequant.sh
./build.sh --target=macosx
./build.sh --target=macosx-aarch64
./build-docker.sh
