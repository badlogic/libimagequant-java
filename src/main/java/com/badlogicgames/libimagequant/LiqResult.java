package com.badlogicgames.libimagequant;

public class LiqResult extends NativeObject {

	LiqResult (long ptr) {
		super(ptr);
	}

	public void setDitheringLevel (float ditherLevel) {
		int code = _setDitheringLevel(getPointer(), ditherLevel);
		LiqError.onError("Couldn't set dithering level", code);
	}

	public void setOutputGamma (double gamma) {
		int code = _setOutputGamma(getPointer(), gamma);
		LiqError.onError("Couldn't set output gamma", code);
	}

	public double getOutputGamma () {
		return _getOutputGamma(getPointer());
	}

	public LiqPalette getPalette () {
		return new LiqPalette(_getPalette(getPointer()));
	}

	public double getQuantizationError () {
		return _getQuantizationError(getPointer());
	}

	public int getQuantizationQuality () {
		return _getQuantizationQuality(getPointer());
	}

	public double getRemappingError () {
		return _getRemappingError(getPointer());
	}

	public int getRemappingQuality () {
		return _getRemappingQuality(getPointer());
	}

	//@off
	/*JNI
	#include <libimagequant.h>
	 */

	private static native int _getRemappingQuality (long result); /*
		return liq_get_remapping_quality((const liq_result*)result);
	*/

	private static native double _getRemappingError (long result); /*
		return liq_get_remapping_error((const liq_result*)result);
	*/

	private static native int _getQuantizationQuality (long result); /*
		return liq_get_quantization_quality((const liq_result*)result);
	*/

	private static native double _getQuantizationError (long result); /*
		return liq_get_quantization_error((const liq_result*)result);
	*/

	private static native long _getPalette (long result); /*
		return (jlong)liq_get_palette((liq_result*)result);
	*/

	private static native double _getOutputGamma (long result); /*
		return liq_get_output_gamma((liq_result*)result);
	*/

	private static native int _setOutputGamma (long result, double gamma); /*
		return liq_set_output_gamma((liq_result*)result, gamma);
	*/

	private static native int _setDitheringLevel (long result, float ditherLevel); /*
		return liq_set_dithering_level((liq_result*)result, ditherLevel);
	*/

	private static native void _destroy (long object); /*
		liq_result_destroy((liq_result*)object);
	*/

	@Override
	protected void _nativeDestroy (long object) {
		_destroy(object);
	}
}
