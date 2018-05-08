
package com.badlogicgames.libimagequant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.DataBufferByte;

import javax.imageio.ImageIO;

public class LibImageQuantTest {
	public static void main (String[] args) throws IOException {
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
	}
}
