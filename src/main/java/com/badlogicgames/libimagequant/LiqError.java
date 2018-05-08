
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

	private static LiqError[] values = LiqError.values();
	private final int code;

	LiqError (int code) {
		this.code = code;
	}

	public int getCode () {
		return code;
	}

	public static void onError (String message, int code) {
		if (code != 0) throw new RuntimeException(message + ": " + LiqError.fromCode(code));
	}

	private static LiqError fromCode (int code) {
		for (LiqError value : values) {
			if (value.code == code) return value;
		}
		throw new IllegalArgumentException("Unknown LiqError code " + code);
	}
}
