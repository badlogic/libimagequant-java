package com.badlogicgames.libimagequant;

public class LiqPalette {
	private final long ptr;

	LiqPalette (long ptr) {
		if (ptr == 0) throw new IllegalArgumentException("Couldn't create " + this.getClass().getSimpleName() + " wrapper around point with value 0");
		this.ptr = ptr;
	}

	/** @return the number colors in the palette **/
	public int getCount () {
		return _getCount(ptr);
	}

	/** @return the 32-bit RGBA color (R in the most significant bits) from the palette at the given index. **/
	public int getColor (int index) {
		if (index < 0 || index >= getCount()) throw new IndexOutOfBoundsException("Index must be > 0 and < " + getCount());
		return _getColor(ptr, index);
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	 */

	private static native int _getCount(long palette); /*
		return ((liq_palette*)palette)->count;
	*/

	private static native int _getColor(long palette, int index); /*
		liq_color *color = &((liq_palette*)palette)->entries[index];
		return (color->r << 24) | (color->g << 16) | (color->b << 8) | color->a;
	*/
}
