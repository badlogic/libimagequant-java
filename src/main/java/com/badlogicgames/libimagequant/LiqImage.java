
package com.badlogicgames.libimagequant;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LiqImage extends NativeObject {
	private final LiqAttribute attribute;
	private final ByteBuffer pixels;

	/** Creates a new {@link LiqImage} from the given bitmap. The bitmap is copied to a new native memory area and managed by this
	 * instance. */
	public LiqImage (LiqAttribute attribute, byte[] bitmap, int width, int height, double gamma) {
		super(createRgba(attribute.getPointer(), bitmap, width, height, gamma));
		this.attribute = attribute;
		this.pixels = _byteBufferFromPointer(_getJniImageData(super.getPointer()), width * height * 4);
		this.pixels.order(ByteOrder.nativeOrder());
	}

	/** Creates a new {@link LiqImage} from the given ByteBuffer. The ByteBuffer must be a direct buffer and remain valid until
	 * this LiqImage is destroyed or collected. No copying of the data of the buffer is performed. The position and limit of the
	 * buffer are ignored. */
	public LiqImage (LiqAttribute attribute, ByteBuffer bitmap, int width, int height, double gamma) {
		super(createRgba(attribute.getPointer(), bitmap, width, height, gamma));
		this.attribute = attribute;
		this.pixels = bitmap;
		this.pixels.order(ByteOrder.nativeOrder());
	}

	/** @return the {@link LiqAttribute} this image was created from */
	public LiqAttribute getAttribute () {
		return attribute;
	}

	@Override
	public long getPointer () {
		return _getJniImageLiqImage(super.getPointer());
	}

	/** @return a {@link ByteBuffer} wrapping the native memory area holding the pixel data. */
	public ByteBuffer getPixels () {
		return pixels;
	}

	public void setBackground (LiqImage background) {
		int code = _setBackgroundImage(getPointer(), background.getPointer());
		LiqError.onError("Couldn't set background", code);
	}

	/** Sets the importance map. The pixels in the buffer are copied. */
	public void setImportanceMap (byte[] buffer) {
		int code = _setImportanceMap(getPointer(), buffer, buffer.length);
		LiqError.onError("Couldn't set importance map", code);
	}

	public void addFixedColor (float r, float g, float b, float a) {
		if (r < 0 || r > 1) throw new IllegalArgumentException("R must be >= 0 and <= 1");
		if (g < 0 || g > 1) throw new IllegalArgumentException("G must be >= 0 and <= 1");
		if (b < 0 || b > 1) throw new IllegalArgumentException("B must be >= 0 and <= 1");
		if (a < 0 || a > 1) throw new IllegalArgumentException("A must be >= 0 and <= 1");
		int code = _addFixedColor(getPointer(), (byte)(r / 255), (byte)(g / 255), (byte)(b / 255), (byte)(a / 255));
		LiqError.onError("Couldn't add fixed color", code);
	}

	public void addFixedColor (byte r, byte g, byte b, byte a) {
		int code = _addFixedColor(getPointer(), r, g, b, a);
		LiqError.onError("Couldn't add fixed color", code);
	}

	public int getWidth () {
		return _getWidth(getPointer());
	}

	public int getHeight () {
		return _getHeight(getPointer());
	}

	public LiqResult quantize () {
		long[] pointer = new long[1];
		int code = _quantize(getPointer(), attribute.getPointer(), pointer);
		LiqError.onError("Couldn't quantize image ", code);
		return new LiqResult(pointer[0]);
	}

	public void remap (LiqResult result, byte[] output) {
		int code = _remap(result.getPointer(), getPointer(), output, output.length);
		LiqError.onError("Couldn't remap image", code);
	}

	private static long createRgba (long attribute, byte[] bitmap, int width, int height, double gamma) {
		if (bitmap.length < width * height * 4) throw new IllegalStateException(
			"The buffer must have a capacity >= " + width + " * " + height + " * 4: " + bitmap.length);
		return _createRgba(attribute, bitmap, width, height, gamma);
	}

	private static long createRgba (long attribute, ByteBuffer bitmap, int width, int height, double gamma) {
		if (bitmap.capacity() < width * height * 4) throw new IllegalStateException(
			"The buffer must have a capacity >= " + width + " * " + height + " * 4: " + bitmap.capacity());
		return _createRgba(attribute, bitmap, width, height, gamma);
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	#include <stdlib.h>
	#include <string.h>

	typedef struct {
		liq_image *image;
		void *data;
	} liq_jni_image;
	 */

	private static native int _remap(long result, long image, byte[] buffer, int bufferSize); /*
		return liq_write_remapped_image((liq_result*)result, (liq_image*)image, buffer, bufferSize);
	*/

	private static native int _quantize (long image, long attr, long[] pointer); /*
		liq_result *result = 0;
		int code = liq_image_quantize((liq_image*)image, (liq_attr*)attr, &result);
		pointer[0] = (jlong)result;
		return code;
	*/

	private static native int _getWidth (long image); /*
		return liq_image_get_width((liq_image*)image);
	*/

	private static native int _getHeight (long image); /*
		return liq_image_get_height((liq_image*)image);
	*/

	private static native int _addFixedColor (long image, byte r, byte g, byte b, byte a); /*
		liq_color color;
		color.r = (unsigned char)r;
		color.g = (unsigned char)g;
		color.b = (unsigned char)b;
		color.a = (unsigned char)a;

		return liq_image_add_fixed_color((liq_image*)image, color);
	*/

	private static native int _setImportanceMap (long image, byte[] buffer, int bufferSize); /*
		return liq_image_set_importance_map((liq_image*)image, (unsigned char*)buffer, bufferSize, LIQ_COPY_PIXELS);
	*/

	private static native int _setImportanceMap (long image, ByteBuffer buffer, int bufferSize); /*
		return liq_image_set_importance_map((liq_image*)image, (unsigned char*)buffer, bufferSize, LIQ_OWN_PIXELS);
	*/

	private static native int _setBackgroundImage (long image, long background); /*
		return (jint)liq_image_set_background((liq_image*)image, (liq_image*)background);
	*/

	private static native long _createRgba (long attribute, byte[] bitmap, int width, int height, double gamma); /*
		void* bitmapCopy = malloc(width * height * 4);
		if (!bitmapCopy) return 0;
		memcpy(bitmapCopy, bitmap, width * height * 4);

		liq_image* image = liq_image_create_rgba((liq_attr*)attribute, (const void*)bitmapCopy, width, height, gamma);
		if (!image) {
			free(bitmapCopy);
			return 0;
		}

		liq_jni_image* jniImage = (liq_jni_image*)malloc(sizeof(liq_jni_image));
		if (!jniImage) {
			liq_image_destroy(image);
			return 0;
		}
		jniImage->image = image;
		jniImage->data = bitmapCopy;
		return (jlong)jniImage;
	*/

	private static native long _createRgba (long attribute, ByteBuffer bitmap, int width, int height, double gamma); /*
		liq_image* image = liq_image_create_rgba((liq_attr*)attribute, (const void*)bitmap, width, height, gamma);
		if (!image) return 0;

		liq_jni_image* jniImage = (liq_jni_image*)malloc(sizeof(liq_jni_image));
		if (!jniImage) {
			liq_image_destroy(image);
			return 0;
		}
		jniImage->image = image;
		jniImage->data = 0;
		return (jlong)jniImage;
	*/

	private static native ByteBuffer _byteBufferFromPointer (long ptr, int numBytes); /*
		return env->NewDirectByteBuffer((char*)ptr, numBytes);
	*/

	private static native long _getJniImageLiqImage (long jniImage); /*
		return (jlong)((liq_jni_image*)jniImage)->image;
	*/

	private static native long _getJniImageData (long jniImage); /*
		return (jlong)((liq_jni_image*)jniImage)->data;
	*/

	private static native void _destroy (long image); /*
		liq_jni_image* jniImage = (liq_jni_image*)image;
		liq_image_destroy(jniImage->image);
		if (jniImage->data) free(jniImage->data);
		free(jniImage);
	*/

	@Override
	protected void _nativeDestroy (long object) {
		_destroy(object);
	}
}
