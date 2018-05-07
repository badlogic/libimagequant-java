FROM debian:stretch-slim

RUN apt-get update && apt-get -y install curl gcc g++ gcc-multilib g++-multilib mingw-w64 lib32z1 git

WORKDIR /code/jni
CMD ./build.sh --target=windows-x86 --build=release && \
	./build.sh --target=windows-x86_64 --build=release && \
	./build.sh --target=linux-x86 --build=release && \
	./build.sh --target=linux-x86_64 --build=release