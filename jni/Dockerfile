FROM debian:stretch-slim

RUN apt-get update && apt-get -y install curl gcc g++ mingw-w64 git
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y 
WORKDIR /code/jni
CMD ./build-imagequant.sh && \
	./build.sh --target=windows-x86_64 --build=release && \
	./build.sh --target=linux-x86_64 --build=release