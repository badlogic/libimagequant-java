package com.badlogicgames.libimagequant;

public enum LiqError {
	OK(0),
	QUALITY_TOO_LOW(99),
	VALUE_OUT_OF_RANGE(100),
	OUT_OF_MEMORY(101),
	ABORTED(102),
	BITMAP_NOT_AVAILABLE(103),
	BUFFER_TO_SMALL(104),
	INVALID_POINTER(105),
	UNSUPPORTED(106);

	private final long code;

	LiqError(long code) {
		this.code = code;
	}

	static LiqError fromCode(long code) {
		for(LiqError value: LiqError.values()) {
			if (value.code == code) return value;
		}
		return null;
	}
}