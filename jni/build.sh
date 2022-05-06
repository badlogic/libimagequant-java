#!/bin/bash
set -e

BUILD=debug
BUILD_DIR="target"

CC=gcc
CC_FLAGS="-c -Wall -std=c99 -m64"

CXX=g++
CXX_FLAGS="-c -Wall -m64"

HEADERS="-Isrc -Ijni-headers -Ilibimagequant/imagequant-sys/"
CXX_SOURCES=`ls -1 src/*.cpp`

STRIP=strip

LINKER_FLAGS="-shared -m64"
LIBRARIES="-L$BUILD_DIR -lm"

OUTPUT_DIR="../src/main/resources/"
OUTPUT_PREFIX="lib"
OUTPUT_NAME="imagequant-java"
OUTPUT_SUFFIX=".so"

function usage {
  cat <<EOF
Usage: $SELF [options]
Options:
  --build=[release|debug] Specifies the build type. If not set both release
                          and debug versions of the libraries will be built.
  --target=...            Specifies the target to build for. Supported targets
                          are macosx, macosx-aarch64, linux-x86_64, and windows-x86_64.
                          If not set the current host OS determines the targets.
  --help                  Displays this information and exits.
EOF
  exit $1
}

+() { :;} 2> /dev/null; trace() { (PS4=; set -x; "$@";{ set +x; } 2> /dev/null); "$@";}

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--target') TARGET="$VALUE" ;;
    '--build') BUILD="$VALUE" ;;
    '--help')
      usage 0
      ;;
    *)
      echo "Unrecognized option or syntax error in option '$1'"
      usage 1
      ;;
  esac
  shift
done

if [ "x$TARGET" = 'x' ]; then
    echo "Please specify a target with --target=<target>: macosx, inux-x86_64, windows-x86_64"
    exit 1
fi

if [ "x$TARGET" != 'x' ]; then
    OS=${TARGET%%-*}
    ARCH=${TARGET#*-}

    if [ "$ARCH" = "aarch64" ]; then     
      OUTPUT_NAME="$OUTPUT_NAME""arm64"    
    else        
      OUTPUT_NAME="$OUTPUT_NAME""64"
    fi    

    if [ "$OS" = "windows" ]; then
      CC="x86_64-w64-mingw32-gcc"
      CC_FLAGS="$CC_FLAGS -Wno-attributes"
      CXX="x86_64-w64-mingw32-g++"
      CXX_FLAGS="$CXX_FLAGS -Wno-attributes"
      STRIP="x86_64-w64-mingw32-strip"
      LINKER_FLAGS="-Wl,--kill-at -static-libgcc -static-libstdc++ -Llibimagequant/target/x86_64-pc-windows-gnu/release/ -limagequant_sys -lbcrypt -luserenv -lws2_32 $LINKER_FLAGS"
      JNI_MD="win32"
      OUTPUT_PREFIX=""
      OUTPUT_SUFFIX=".dll"        
    fi

    if [ "$OS" = "linux" ]; then
      CC_FLAGS="$CC_FLAGS -fPIC"
      CXX_FLAGS="$CXX_FLAGS -fPIC"
      LINKER_FLAGS="-Llibimagequant/target/x86_64-unknown-linux-gnu/release/ -limagequant_sys -lpthread -ldl $LINKER_FLAGS"
      echo "int main() {}" > main.c
      CXX_SOURCES="$CXX_SOURCES main.c"
      JNI_MD="linux"
    fi

    if [ "$OS" = "macosx" ]; then
      STRIP="strip -S -x "
      JNI_MD="mac"        
      OUTPUT_SUFFIX=".dylib"

      if [ "$ARCH" == "aarch64" ]; then
        CC_FLAGS="-c -Wall -std=c99 -fPIC -mmacosx-version-min=10.7"
        CXX_FLAGS="-c -Wall -fPIC -mmacosx-version-min=10.7"
        LINKER_FLAGS="$LINKER_FLAGS -arch arm64 -Llibimagequant/target/aarch64-apple-darwin/release -limagequant_sys"        
      else
        CC_FLAGS="$CC_FLAGS -fPIC -arch x86_64 -mmacosx-version-min=10.7"
        CXX_FLAGS="$CXX_FLAGS -fPIC -arch x86_64 -mmacosx-version-min=10.7 -stdlib=libc++"
        LINKER_FLAGS=" $LINKER_FLAGS -arch x86_64 -Llibimagequant/target/x86_64-apple-darwin/release -limagequant_sys"
      fi      
    fi    

    if [ "$BUILD" = "debug" ]; then
        CXX_FLAGS="$CXX_FLAGS -g"
    else
        CC_FLAGS="$CC_FLAGS -O3"
        CXX_FLAGS="$CXX_FLAGS -O3"
    fi
fi

HEADERS="$HEADERS -Ijni-headers/${JNI_MD}"

rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR
mkdir -p $OUTPUT_DIR

echo "--- Compiling for $OS, arch $ARCH, build type $BUILD"

echo "------ Compiling C sources"
#for f in $CC_SOURCES; do
#    OBJECT_FILE=$BUILD_DIR/`basename $f .c`
#    trace $CC $CC_FLAGS $HEADERS "$f" -o $OBJECT_FILE.o
#done
echo

echo "------ Compiling C++ sources"
for f in $CXX_SOURCES; do
    OBJECT_FILE=$BUILD_DIR/`basename $f .cpp`
    trace $CXX $CXX_FLAGS $HEADERS "$f" -o $OBJECT_FILE.o
done
echo

echo "--- Linking & stripping"
LINKER=$CXX
OBJ_FILES=`ls -1 $BUILD_DIR/*.o`
OUTPUT_FILE="$OUTPUT_DIR$OUTPUT_PREFIX$OUTPUT_NAME$OUTPUT_SUFFIX"
trace $LINKER $OBJ_FILES $LIBRARIES $LINKER_FLAGS -o "$OUTPUT_FILE"
trace $STRIP "$OUTPUT_FILE"
echo

#trace rm -rf $BUILD_DIR