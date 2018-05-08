
package com.badlogicgames.libimagequant;

public class LiqHistogram extends NativeObject {
	private LiqAttribute attribute;

	/**
	 * Creates a new {@link LiqHistogram} for the given {@link LiqAttribute}. Throws
	 * {@link IllegalStateException} in case the native memory couldn't be allocated.
	 */
	public LiqHistogram (LiqAttribute attribute) {
		super(_create(attribute.getPointer()));
		this.attribute = attribute;
	}

	/**
	 * Adds the {@link LiqImage} to the histogram.
	 */
	public LiqError addImage(LiqImage image) {
		return LiqError.fromCode(_addImage(getPointer(), attribute.getPointer(), image.getPointer()));
	}

	/**
	 * @return the {@link LiqAttribute} this histogram was created from
	 */
	public LiqAttribute getAttribute() {
		return attribute;
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	 */

	private static native long _create(long attr); /*
		return (jlong)liq_histogram_create((const liq_attr*)attr);
	*/

	private static native int _addImage(long histogram, long attr, long image); /*
		return (jint)liq_histogram_add_image((liq_histogram*)histogram, (liq_attr*)attr, (liq_image*)image);
	*/

	// private static native int _addColors(long histogram, long attr, long[] entries, double gamma);

	private static native void _destroy(long histogram); /*
		liq_histogram_destroy((liq_histogram*)histogram);
	*/

	@Override
	protected void _nativeDestroy (long object) {
		_destroy(object);
	}
}
