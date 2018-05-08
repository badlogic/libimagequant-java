#include <com.badlogicgames.libimagequant.LiqPalette.h>

//@line:39

	#include <libimagequant.h>
	 JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqPalette__1getCount(JNIEnv* env, jclass clazz, jlong palette) {


//@line:43

		return ((liq_palette*)palette)->count;
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqPalette__1getColor(JNIEnv* env, jclass clazz, jlong palette, jint index) {


//@line:47

		liq_color *color = &((liq_palette*)palette)->entries[index];
		return (color->r << 24) | (color->g << 16) | (color->b << 8) | color->a;
	

}

