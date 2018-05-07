# Compiling libimagequant native shared libraries

## Windows & Linux

> The easiest way to compile the Windows & Linux binaries is to use the Docker image provided in this repository. This way, no toolchains needs to be installed locally, and no VMs need to be setup. See the README.md file in the root of the repository. See blow on how to compile for Windows & Linux with your host toolchains.

To (cross-)compile the static libimagequant library for Windows and Linux on Ubuntu:
1. Install MinGW for your system:
	```
	sudo apt-get update && sudo apt-get install gcc g++ gcc-multilib g++-multilib mingw-w64 lib32z1
	```
2. Invoke the script `build.sh` with the `--target` flag, one of `macosx`, `windows-x86`, `windows-x86_64`, `linux-x86`, `linux-x86_64`, and the `--build` flag set to either `release` or `debug`.

The resulting `.dll` and `.so` files will be located in `src/main/resources` and loaded by `SharedLibraryLoader`.

## macOS
To compiling for macOS:
1. Install Xcode and its command line tools
2. Invoke the script `build.sh` with the `--target` flag, one of `macosx`, `windows-x86`, `windows-x86_64`, `linux-x86`, `linux-x86_64`, and the `--build` flag set to either `release` or `debug`.

The resulting `.dylib` file will be located in `src/main/resources` and loaded by `SharedLibraryLoader`.
