
package com.badlogicgames.libimagequant;

public class LiqHistogram extends NativeObject {
	private LiqAttribute attribute;

	/** Creates a new {@link LiqHistogram} for the given {@link LiqAttribute}. Throws {@link IllegalStateException} in case the
	 * native memory couldn't be allocated. */
	public LiqHistogram (LiqAttribute attribute) {
		super(_create(attribute.getPointer()));
		this.attribute = attribute;
	}

	/** @return the {@link LiqAttribute} this histogram was created from */
	public LiqAttribute getAttribute () {
		return attribute;
	}

	/** Adds the {@link LiqImage} to the histogram. */
	public void addImage (LiqImage image) {
		int code = _addImage(getPointer(), attribute.getPointer(), image.getPointer());
		LiqError.onError("Couldn't add image: ", code);
	}

	/** Adds the colors with their respective counts to this histogram. */
	public void addColors (LiqHistogramEntry[] entries, double gamma) {
		for (LiqHistogramEntry entry: entries) {
			int code = _addColor(getPointer(), attribute.getPointer(), entry.r, entry.g, entry.b, entry.a, entry.count, gamma);
			LiqError.onError("Couldn't add colors: ", code);
		}
	}

	public LiqResult quantize() {
		long[] pointer = new long[1];
		int code = _quantize(getPointer(), attribute.getPointer(), pointer);
		LiqError.onError("Couldn't quantize histogram", code);
		return new LiqResult(pointer[0]);
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	 */

	private static native int _quantize (long hist, long attr, long[] pointer); /*
		liq_result *result = 0;
		int code = liq_histogram_quantize((liq_histogram*)hist, (liq_attr*)attr, &result);
		pointer[0] = (jlong)result;
		return code;
	*/

	private static native int _addColor (long hist, long attr, byte r, byte g, byte b, byte a, int count, double gamma); /*
		liq_histogram_entry entry;
		entry.color.r = (unsigned char)r;
		entry.color.g = (unsigned char)g;
		entry.color.b = (unsigned char)b;
		entry.color.a = (unsigned char)a;
		entry.count = count;
		return (jint)liq_histogram_add_colors((liq_histogram*)hist, (liq_attr*)attr, &entry, 1, gamma);
	*/

	private static native long _create (long attr); /*
		return (jlong)liq_histogram_create((const liq_attr*)attr);
	*/

	private static native int _addImage (long hist, long attr, long image); /*
		return (jint)liq_histogram_add_image((liq_histogram*)hist, (liq_attr*)attr, (liq_image*)image);
	*/

	// private static native int _addColors(long histogram, long attr, long[] entries, double gamma);

	private static native void _destroy (long histogram); /*
		liq_histogram_destroy((liq_histogram*)histogram);
	*/

	@Override
	protected void _nativeDestroy (long object) {
		_destroy(object);
	}
}
