FROM debian:stretch-slim

RUN apt-get update && apt-get -y install curl gcc g++ mingw-w64 git
WORKDIR /code/jni
CMD	./build.sh --target=windows-x86_64 --build=release && \
	./build.sh --target=linux-x86_64 --build=release