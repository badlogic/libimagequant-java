#!/bin/bash
set -e

source /root/.cargo/env
rustup target add x86_64-pc-windows-gnu
rm -rf libimagequant
git clone https://github.com/ImageOptim/libimagequant

pushd libimagequant/imagequant-sys
cargo clean
cargo build --release
cargo build --target x86_64-pc-windows-gnu --release
popd
