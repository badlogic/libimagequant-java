#include <com.badlogicgames.libimagequant.LiqResult.h>

//@line:44

	#include <libimagequant.h>
	 JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getRemappingQuality(JNIEnv* env, jclass clazz, jlong result) {


//@line:48

		return liq_get_remapping_quality((const liq_result*)result);
	

}

JNIEXPORT jdouble JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getRemappingError(JNIEnv* env, jclass clazz, jlong result) {


//@line:52

		return liq_get_remapping_error((const liq_result*)result);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getQuantizationQuality(JNIEnv* env, jclass clazz, jlong result) {


//@line:56

		return liq_get_quantization_quality((const liq_result*)result);
	

}

JNIEXPORT jdouble JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getQuantizationError(JNIEnv* env, jclass clazz, jlong result) {


//@line:60

		return liq_get_quantization_error((const liq_result*)result);
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getPalette(JNIEnv* env, jclass clazz, jlong result) {


//@line:64

		return (jlong)liq_get_palette((liq_result*)result);
	

}

JNIEXPORT jdouble JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1getOutputGamma(JNIEnv* env, jclass clazz, jlong result) {


//@line:68

		return liq_get_output_gamma((liq_result*)result);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1setOutputGamma(JNIEnv* env, jclass clazz, jlong result, jdouble gamma) {


//@line:72

		return liq_set_output_gamma((liq_result*)result, gamma);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1setDitheringLevel(JNIEnv* env, jclass clazz, jlong result, jfloat ditherLevel) {


//@line:76

		return liq_set_dithering_level((liq_result*)result, ditherLevel);
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqResult__1destroy(JNIEnv* env, jclass clazz, jlong object) {


//@line:80

		liq_result_destroy((liq_result*)object);
	

}

