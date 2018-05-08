package com.badlogicgames.libimagequant;

import org.junit.Test;

public class LibImageQuantTest {
	static {
		new SharedLibraryLoader().load("imagequant-java");
	}

	@Test
	public void testLiqAttribute () {
		LiqAttribute attribute = new LiqAttribute();
		attribute.destroy();
	}
}
