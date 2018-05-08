
package com.badlogicgames.libimagequant;

public class LiqHistogramEntry {
	public byte r, g, b, a;
	public int count;

	public LiqHistogramEntry (byte r, byte g, byte b, byte a, int count) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.count = count;
	}
}
