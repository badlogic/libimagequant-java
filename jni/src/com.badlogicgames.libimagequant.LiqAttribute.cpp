#include <com.badlogicgames.libimagequant.LiqAttribute.h>

//@line:72

	#include <libimagequant.h>
	 JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setLastIndexTransparent(JNIEnv* env, jclass clazz, jlong attr, jboolean isLast) {


//@line:76

		liq_set_last_index_transparent((liq_attr*)attr, (int)isLast);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setQuality(JNIEnv* env, jclass clazz, jlong attr, jint minQuality, jint maxQuality) {


//@line:80

	return (jint)liq_set_quality((liq_attr*)attr, minQuality, maxQuality);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getMinQuality(JNIEnv* env, jclass clazz, jlong attr) {


//@line:84

		return (jint)liq_get_min_quality((liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getMaxQuality(JNIEnv* env, jclass clazz, jlong attr) {


//@line:88

		return (jint)liq_get_max_quality((liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setMinPosterization(JNIEnv* env, jclass clazz, jlong attr, jint min) {


//@line:92

	return (jint)liq_set_min_posterization((liq_attr*)attr, min);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getMinPosterization(JNIEnv* env, jclass clazz, jlong attr) {


//@line:96

		return (jint)liq_get_min_posterization((liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setMinOpacity(JNIEnv* env, jclass clazz, jlong attr, jint min) {


//@line:100

	return (jint)liq_set_min_opacity((liq_attr*)attr, min);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getMinOpacity(JNIEnv* env, jclass clazz, jlong attr) {


//@line:104

		return (jint)liq_get_min_opacity((liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setSpeed(JNIEnv* env, jclass clazz, jlong attr, jint speed) {


//@line:108

		return (jint)liq_set_speed((liq_attr*)attr, speed);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getSpeed(JNIEnv* env, jclass clazz, jlong attr) {


//@line:112

		return (jint)liq_get_speed((liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1setMaxColors(JNIEnv* env, jclass clazz, jlong attr, jint colors) {


//@line:116

		return (jint)liq_set_max_colors((liq_attr*)attr, colors);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1getMaxColors(JNIEnv* env, jclass clazz, jlong attr) {


//@line:120

		return (jint)liq_get_max_colors((liq_attr*)attr);
	 

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1create(JNIEnv* env, jclass clazz) {


//@line:124

		return (jlong)liq_attr_create();
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1copy(JNIEnv* env, jclass clazz, jlong original) {


//@line:128

		return (jlong)liq_attr_copy((liq_attr*)original);
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1destroy(JNIEnv* env, jclass clazz, jlong attr) {


//@line:132

		liq_attr_destroy((liq_attr*)attr);
	

}

