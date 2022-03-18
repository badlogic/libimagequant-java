#!/bin/bash
set -e

if [[ -f "/root/.cargo/env" ]]; then
	source /root/.cargo/env
fi
rustup target add x86_64-pc-windows-gnu
rm -rf libimagequant
git clone https://github.com/ImageOptim/libimagequant

pushd libimagequant/imagequant-sys
cargo clean
cargo build --release
cargo build --target x86_64-pc-windows-gnu --release
popd
