# libimagequant-java
libimagequant-java is a JNI wrapper around the C API for [libimagequant](https://pngquant.org/lib/), enabling you to quantize 32-bit RGBA images.

libimagequant-java is kept intentionally simple, only operating on `byte[]` arrays and direct `ByteBuffer` instances.

libimagequant-java the required native shared library and automatically loads the library compatible with your operating system. No messing around with `-Djava.library.path`. libimagequant-java supports Windows 32- & 64-bit, Linux 32- & 64-bit as well as macOS out of the box.

## Installation
libimagequant-java is published to Maven Central. You can include it in your `pom.xml` as a dependency as follows:

```xml
<dependency>
	<groupId>com.badlogicgames</groupId>
	<artifactId>libimagequant-java</artifactId>
	<version>1.0</version>
</dependency>
```

To include it in your Gradle project, ensure your `build.gradle` file adds the `mavenCentral()` repository. Then add the dependency:

```groovy
compile "com.badlogicgames:libimagequant-java:1.0"
```

libimagequant-java is also build on every new commit by [Jenkins](https://libgdx.badlogicgames.com/jenkins/job/libimagequant-java/) and published as a [SNAPSHOT release](https://oss.sonatype.org/content/repositories/snapshots/com/badlogicgames/libimagequant-java/) to SonaType.

## Usage
The below code loads a 32-bit RGBA PNG file, quantizes it to 8-bit and then writes it back out to a new file.

```java
new SharedLibraryLoader().load("imagequant-java");

// Read the input image.
BufferedImage input = ImageIO.read(LibImageQuantTest.class.getResourceAsStream("/input.png"));
byte[] pixels = ((DataBufferByte)input.getRaster().getDataBuffer()).getData();

// ABGR -> RGBA.
for (int i = 0; i < pixels.length; i += 4) {
	byte a = pixels[i];
	byte b = pixels[i + 1];
	byte g = pixels[i + 2];
	byte r = pixels[i + 3];
	pixels[i] = r;
	pixels[i + 1] = g;
	pixels[i + 2] = b;
	pixels[i + 3] = a;
}

// Setup libimagequant and quantize the image.
LiqAttribute attribute = new LiqAttribute();
LiqImage image = new LiqImage(attribute, pixels, input.getWidth(), input.getHeight(), 0);
LiqResult result = image.quantize();

// Based on the quantization result, generate an 8-bit indexed image and retrieve its palette.
byte[] quantizedPixels = new byte[input.getWidth() * input.getHeight()];
image.remap(result, quantizedPixels);
LiqPalette palette = result.getPalette();

// The resulting 8-bit indexed image and palette could be written out to an indexed PNG or GIF, but instead we convert it
// back to 32-bit RGBA.
BufferedImage convertedImage = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
byte[] convertedPixels = ((DataBufferByte)convertedImage.getRaster().getDataBuffer()).getData();
int size = input.getWidth() * input.getHeight();
for (int i = 0, j = 0; i < size; i++, j += 4) {
	int index = quantizedPixels[i] & 0xff; // Java's byte is signed
	int color = palette.getColor(index);
	convertedPixels[j] = LiqPalette.getA(color);
	convertedPixels[j + 1] = LiqPalette.getB(color);
	convertedPixels[j + 2] = LiqPalette.getG(color);
	convertedPixels[j + 3] = LiqPalette.getR(color);
}

ImageIO.write(convertedImage, "png", new File("output.png"));

// Good practice to immediately destroy the native resources but not necessary. If the GC cleans up the Java side object the
// native side will be destroyed as well.
result.destroy();
image.destroy();
attribute.destroy();
```

Refer to the [libimagequant documentation](https://pngquant.org/lib/) for more information.

## Working from source
libimagequant-java includes the upstream libimagequant repository as a submodule in `jni/libimagequant`. Make sure to clone this repository recursively to also check out the submodule:

```
git clone --recursive https://github.com/badlogic/libimagequant-java
```

libimagequant-java is a plain old Maven project and can be imported into any Maven aware IDE. Before importing, run

```
mvn clean compile
```

from a terminal. This will download the latest native shared libraries from [Jenkins](https://libgdx.badlogicgames.com/ci/libimagequant-java/binaries/) so you do not have to build them yourself. After the above Maven invocation, the native shared libraries will be located in `src/main/resources`.

### Building the native libraries
To build the native shared libraries yourself, you can use the included `jni/build-docker.sh` script for Windows 32-bit, Windows 64-bit, Linux 32-bit and Linux 64-bit. Install [Docker CE](https://www.docker.com/community-edition), then in your terminal:

```bash
cd jni/
./build-docker.sh
```

This will build a Docker image with all required toolchains for Windows and Linux, compile the libimagequant-java shared libraries and put them in `src/main/resources`.

Finally, if you want to build a shared library for your specific host, use the `jni/build.sh` script:

```bash
/build.sh --target=<target-os-arch> --build=<release|debug>
```

Valid values for the `--target` parameter to pass to `build.sh` are `macosx`, `windows-x86`, `windows-x86_64`, `linux-64` and `linux-x86_64`.

Valid values for the `--build` parameter are `release` and `debug`.

To build locally, all required toolchains must be installed; Xcode with command line tools on macOS, Mingw64 on Windows (or Linux for cross-compilation) and GCC on Linux.

### Modifying the C++ JNI code
libimagequant-java uses [gdx-jnigen](https://github.com/libgdx/libgdx/wiki/jnigen) to generate C++ code embedded in the Java files. When you modify the embedded C++ code, you have to re-generate the C++ files in `jni/src/` by running the CodeGenerator class.

## License
AGPL 3, See [LICENSE](https://github.com/badlogic/libimagequant-java/blob/master/LICENSE). For alternative licensing options contact the libimagequant [author](https://kornel.ski/contact).
