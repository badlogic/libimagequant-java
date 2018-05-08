package com.badlogicgames.libimagequant;

public enum LiqOwnership {
	OWN_ROWS(4),
	OWN_PIXELS(8),
	COPY_PIXELS(16);

	private final long code;

	LiqOwnership(long code) {
		this.code = code;
	}

	static LiqOwnership fromCode(long code) {
		for(LiqOwnership value: LiqOwnership.values()) {
			if (value.code == code) return value;
		}
		return null;
	}
}
