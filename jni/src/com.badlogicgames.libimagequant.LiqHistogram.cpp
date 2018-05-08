#include <com.badlogicgames.libimagequant.LiqHistogram.h>

//@line:31

	#include <libimagequant.h>
	 JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1create(JNIEnv* env, jclass clazz, jlong attr) {


//@line:35

		return (jlong)liq_histogram_create((const liq_attr*)attr);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1addImage(JNIEnv* env, jclass clazz, jlong histogram, jlong attr, jlong image) {


//@line:39

		return (jint)liq_histogram_add_image((liq_histogram*)histogram, (liq_attr*)attr, (liq_image*)image);
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqHistogram__1destroy(JNIEnv* env, jclass clazz, jlong histogram) {


//@line:45

		liq_histogram_destroy((liq_histogram*)histogram);
	

}

