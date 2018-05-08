#include <com.badlogicgames.libimagequant.LiqHistogram.h>

//@line:41

	#include <libimagequant.h>
	 static inline jint wrapped_Java_com_badlogicgames_libimagequant_LiqHistogram__1quantize
(JNIEnv* env, jclass clazz, jlong hist, jlong attr, jlongArray obj_pointer, long long* pointer) {

//@line:45

		liq_result *result = 0;
		int code = liq_histogram_quantize((liq_histogram*)hist, (liq_attr*)attr, &result);
		pointer[0] = (jlong)result;
		return code;
	
}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1quantize(JNIEnv* env, jclass clazz, jlong hist, jlong attr, jlongArray obj_pointer) {
	long long* pointer = (long long*)env->GetPrimitiveArrayCritical(obj_pointer, 0);

	jint JNI_returnValue = wrapped_Java_com_badlogicgames_libimagequant_LiqHistogram__1quantize(env, clazz, hist, attr, obj_pointer, pointer);

	env->ReleasePrimitiveArrayCritical(obj_pointer, pointer, 0);

	return JNI_returnValue;
}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1addColor(JNIEnv* env, jclass clazz, jlong hist, jlong attr, jbyte r, jbyte g, jbyte b, jbyte a, jint count, jdouble gamma) {


//@line:52

		liq_histogram_entry entry;
		entry.color.r = (unsigned char)r;
		entry.color.g = (unsigned char)g;
		entry.color.b = (unsigned char)b;
		entry.color.a = (unsigned char)a;
		entry.count = count;
		return (jint)liq_histogram_add_colors((liq_histogram*)hist, (liq_attr*)attr, &entry, 1, gamma);
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1create(JNIEnv* env, jclass clazz, jlong attr) {


//@line:62

		return (jlong)liq_histogram_create((const liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1addImage(JNIEnv* env, jclass clazz, jlong hist, jlong attr, jlong image) {


//@line:66

		return (jint)liq_histogram_add_image((liq_histogram*)hist, (liq_attr*)attr, (liq_image*)image);
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1destroy(JNIEnv* env, jclass clazz, jlong histogram) {


//@line:72

		liq_histogram_destroy((liq_histogram*)histogram);
	

}

