#!/bin/bash
set -e

if [[ -f "/root/.cargo/env" ]]; then
	source /root/.cargo/env
fi
rustup target add x86_64-unknown-linux-gnu
rustup target add x86_64-pc-windows-gnu
rustup target add aarch64-apple-darwin
rustup target add x86_64-apple-darwin
rm -rf libimagequant
git clone https://github.com/ImageOptim/libimagequant

pushd libimagequant/imagequant-sys
cargo clean
cargo build --target x86_64-unknown-linux-gnu --release
cargo build --target x86_64-pc-windows-gnu --release
cargo build --target x86_64-apple-darwin --release
cargo build --target aarch64-apple-darwin --release
popd
