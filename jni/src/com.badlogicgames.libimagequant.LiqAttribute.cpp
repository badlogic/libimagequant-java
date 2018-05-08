#include <com.badlogicgames.libimagequant.LiqAttribute.h>

//@line:5

	#include <libimagequant.h>
	 JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1create(JNIEnv* env, jclass clazz) {


//@line:35

		return (jlong)liq_attr_create();
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1copy(JNIEnv* env, jclass clazz, jlong original) {


//@line:39

		return (jlong)liq_attr_copy((liq_attr*)original);
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqAttribute__1destroy(JNIEnv* env, jclass clazz, jlong attr) {


//@line:43

		liq_attr_destroy((liq_attr*)attr);
	

}

