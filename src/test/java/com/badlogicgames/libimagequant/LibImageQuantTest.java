package com.badlogicgames.libimagequant;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibImageQuantTest {
	static {
		new SharedLibraryLoader().load("imagequant-java");
	}

	@Test
	public void testLiqAttribute () {
		LiqAttribute attribute = new LiqAttribute();
		assertNotEquals("Creation returned 0 pointer but didn't throw exception", 0, attribute.getPointer());
		LiqAttribute copy = new LiqAttribute(attribute);
		assertNotEquals("Creation returned 0 pointer but didn't throw exception", 0, copy.getPointer());
		attribute.destroy();
		assertEquals("Destroy didn't delete the memory", 0, attribute.getPointer());

		copy.setMaxColors(123);
		assertEquals("Max color setter/getter not working", 123, copy.getMaxColors());
	}
}
