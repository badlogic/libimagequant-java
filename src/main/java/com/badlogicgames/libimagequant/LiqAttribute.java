
package com.badlogicgames.libimagequant;

public class LiqAttribute extends NativeObject {
	/** Creates a new {@link LiqAttribute}. Throws an {@link IllegalStateException} in case the native memory couldn't be
	 * allocated. */
	public LiqAttribute () {
		super(_create());
	}

	/** Creates a new {@link LiqAttribute} based on the other attribute. Throws an {@link IllegalStateException} in case the native
	 * memory couldn't be allocated. */
	public LiqAttribute (LiqAttribute original) {
		super(_copy(original.getPointer()));
	}

	/** @param colors 2-256. */
	public void setMaxColors (int colors) {
		int code = _setMaxColors(getPointer(), colors);
		LiqError.onError("Couldn't set max colors", code);
	}

	public int getMaxColors () {
		return _getMaxColors(getPointer());
	}

	/** @param speed 1-10 where 1 is the higest quality but only marginally better than 3, default is 3, and 10 has typically 5%
	 *           lower quality than 3 but is 8 times faster. */
	public void setSpeed (int speed) {
		int code = _setSpeed(getPointer(), speed);
		LiqError.onError("Couldn't set speed", code);
	}

	public int getSpeed () {
		return _getSpeed(getPointer());
	}

	/** @param min 0-255. */
	public void setMinOpacity (int min) {
		int code = _setMinOpacity(getPointer(), min);
		LiqError.onError("Couldn't set min. opacity", code);
	}

	public int getMinOpacity () {
		return _getMinOpacity(getPointer());
	}

	/** @param min 0-4 where 0 if full quality, 2 is for VGA or 16-bit RGB565, and 4 is for RGB444. */
	public void setMinPosterization (int min) {
		int code = _setMinPosterization(getPointer(), min);
		LiqError.onError("Couldn't set min. posterization", code);
	}

	public int getMinPosterization () {
		return _getMinPosterization(getPointer());
	}

	/** @param minQuality 0-100.
	 * @param maxQuality 0-100. */
	public void setQuality (int minQuality, int maxQuality) {
		int code = _setQuality(getPointer(), minQuality, maxQuality);
		LiqError.onError("Couldn't set quality", code);
	}

	public int getMinQuality () {
		return _getMinQuality(getPointer());
	}

	public int getMaxQuality () {
		return _getMinQuality(getPointer());
	}

	public void setLastIndexTransparent (boolean isLast) {
		_setLastIndexTransparent(getPointer(), isLast);
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	 */

	private static native void _setLastIndexTransparent (long attr, boolean isLast); /*
		liq_set_last_index_transparent((liq_attr*)attr, (int)isLast);
	*/

	private static native int _setQuality (long attr, int minQuality, int maxQuality); /*
	return (jint)liq_set_quality((liq_attr*)attr, minQuality, maxQuality);
	*/

	private static native int _getMinQuality (long attr); /*
		return (jint)liq_get_min_quality((liq_attr*)attr);
	*/

	private static native int _getMaxQuality (long attr); /*
		return (jint)liq_get_max_quality((liq_attr*)attr);
	*/

	private static native int _setMinPosterization (long attr, int min); /*
	return (jint)liq_set_min_posterization((liq_attr*)attr, min);
	*/

	private static native int _getMinPosterization (long attr); /*
		return (jint)liq_get_min_posterization((liq_attr*)attr);
	*/

	private static native int _setMinOpacity (long attr, int min); /*
	return (jint)liq_set_min_opacity((liq_attr*)attr, min);
	*/

	private static native int _getMinOpacity (long attr); /*
		return (jint)liq_get_min_opacity((liq_attr*)attr);
	*/

	private static native int _setSpeed (long attr, int speed); /*
		return (jint)liq_set_speed((liq_attr*)attr, speed);
	*/

	private static native int _getSpeed (long attr); /*
		return (jint)liq_get_speed((liq_attr*)attr);
	*/

	private static native int _setMaxColors (long attr, int colors); /*
		return (jint)liq_set_max_colors((liq_attr*)attr, colors);
	*/

	private static native int _getMaxColors (long attr); /*
		return (jint)liq_get_max_colors((liq_attr*)attr);
	 */

	private static native long _create (); /*
		return (jlong)liq_attr_create();
	*/

	private static native long _copy (long original); /*
		return (jlong)liq_attr_copy((liq_attr*)original);
	*/

	private static native void _destroy (long attr); /*
		liq_attr_destroy((liq_attr*)attr);
	*/

	@Override
	protected void _nativeDestroy (long object) {
		_destroy(object);
	}
}
