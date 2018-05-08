package com.badlogicgames.libimagequant;

public class LiqAttribute {
	//@off
	/*JNI
	#include <libimagequant.h>
	 */
	// @on

	private long ptr;

	public LiqAttribute () {
		this.ptr = _create();
		if (ptr == 0) throw new IllegalStateException("Couldn't create LiqAttribute");
	}

	public LiqAttribute (LiqAttribute original) {
		this.ptr = _copy(original.ptr);
		if (ptr == 0) throw new IllegalStateException("Couldn't create LiqAttribute");
	}

	public void destroy () {
		if (ptr != 0) {
			_destroy(ptr);
			ptr = 0;
		}
	}

	@Override
	protected void finalize () throws Throwable {
		destroy();
	}

	// @off
	private static native long _create (); /*
		return (jlong)liq_attr_create();
	*/

	private static native long _copy (long original); /*
		return (jlong)liq_attr_copy((liq_attr*)original);
	*/

	private static native void _destroy (long attr); /*
		liq_attr_destroy((liq_attr*)attr);
	*/
}
