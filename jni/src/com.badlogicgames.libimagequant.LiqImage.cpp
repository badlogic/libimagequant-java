#include <com.badlogicgames.libimagequant.LiqImage.h>

//@line:90

	#include <libimagequant.h>
	#include <stdlib.h>
	#include <string.h>

	typedef struct {
		liq_image *image;
		void *data;
	} liq_jni_image;
	 JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1getWidth(JNIEnv* env, jclass clazz, jlong image) {


//@line:101

		return liq_image_get_width((liq_image*)image);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1getHeight(JNIEnv* env, jclass clazz, jlong image) {


//@line:105

		return liq_image_get_height((liq_image*)image);
	

}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1addFixedColor(JNIEnv* env, jclass clazz, jlong image, jbyte r, jbyte g, jbyte b, jbyte a) {


//@line:109

		liq_color color;
		color.r = (unsigned char)r;
		color.g = (unsigned char)g;
		color.b = (unsigned char)b;
		color.a = (unsigned char)a;

		return liq_image_add_fixed_color((liq_image*)image, color);
	

}

static inline jint wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1setImportanceMap
(JNIEnv* env, jclass clazz, jlong image, jbyteArray obj_buffer, jint bufferSize, char* buffer) {

//@line:119

		return liq_image_set_importance_map((liq_image*)image, (unsigned char*)buffer, bufferSize, LIQ_COPY_PIXELS);
	
}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1setImportanceMap(JNIEnv* env, jclass clazz, jlong image, jbyteArray obj_buffer, jint bufferSize) {
	char* buffer = (char*)env->GetPrimitiveArrayCritical(obj_buffer, 0);

	jint JNI_returnValue = wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1setImportanceMap(env, clazz, image, obj_buffer, bufferSize, buffer);

	env->ReleasePrimitiveArrayCritical(obj_buffer, buffer, 0);

	return JNI_returnValue;
}

JNIEXPORT jint JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1setBackgroundImage(JNIEnv* env, jclass clazz, jlong image, jlong background) {


//@line:123

		return (jint)liq_image_set_background((liq_image*)image, (liq_image*)background);
	

}

static inline jlong wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__J_3BIID
(JNIEnv* env, jclass clazz, jlong attribute, jbyteArray obj_bitmap, jint width, jint height, jdouble gamma, char* bitmap) {

//@line:127

		void* bitmapCopy = malloc(width * height * 4);
		if (!bitmapCopy) return 0;
		memcpy(bitmap, bitmapCopy, width * height * 4);

		liq_image* image = liq_image_create_rgba((liq_attr*)attribute, (const void*)bitmapCopy, width, height, gamma);
		if (!image) return 0;

		liq_jni_image* jniImage = (liq_jni_image*)malloc(sizeof(liq_jni_image));
		if (!jniImage) {
			liq_image_destroy(image);
			return 0;
		}
		jniImage->image = image;
		jniImage->data = bitmapCopy;
		return (jlong)jniImage;
	
}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__J_3BIID(JNIEnv* env, jclass clazz, jlong attribute, jbyteArray obj_bitmap, jint width, jint height, jdouble gamma) {
	char* bitmap = (char*)env->GetPrimitiveArrayCritical(obj_bitmap, 0);

	jlong JNI_returnValue = wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__J_3BIID(env, clazz, attribute, obj_bitmap, width, height, gamma, bitmap);

	env->ReleasePrimitiveArrayCritical(obj_bitmap, bitmap, 0);

	return JNI_returnValue;
}

static inline jlong wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__JLjava_nio_ByteBuffer_2IID
(JNIEnv* env, jclass clazz, jlong attribute, jobject obj_bitmap, jint width, jint height, jdouble gamma, char* bitmap) {

//@line:145

		liq_image* image = liq_image_create_rgba((liq_attr*)attribute, (const void*)bitmap, width, height, gamma);
		if (!image) return 0;

		liq_jni_image* jniImage = (liq_jni_image*)malloc(sizeof(liq_jni_image));
		if (!jniImage) {
			liq_image_destroy(image);
			return 0;
		}
		jniImage->image = image;
		jniImage->data = 0;
		return (jlong)jniImage;
	
}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__JLjava_nio_ByteBuffer_2IID(JNIEnv* env, jclass clazz, jlong attribute, jobject obj_bitmap, jint width, jint height, jdouble gamma) {
	char* bitmap = (char*)(obj_bitmap?env->GetDirectBufferAddress(obj_bitmap):0);

	jlong JNI_returnValue = wrapped_Java_com_badlogicgames_libimagequant_LiqImage__1createRgba__JLjava_nio_ByteBuffer_2IID(env, clazz, attribute, obj_bitmap, width, height, gamma, bitmap);


	return JNI_returnValue;
}

JNIEXPORT jobject JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1byteBufferFromPointer(JNIEnv* env, jclass clazz, jlong ptr, jint numBytes) {


//@line:159

		return env->NewDirectByteBuffer((char*)ptr, numBytes);
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1getJniImageLiqImage(JNIEnv* env, jclass clazz, jlong jniImage) {


//@line:163

		return (jlong)((liq_jni_image*)jniImage)->image;
	

}

JNIEXPORT jlong JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1getJniImageData(JNIEnv* env, jclass clazz, jlong jniImage) {


//@line:167

		return (jlong)((liq_jni_image*)jniImage)->data;
	

}

JNIEXPORT void JNICALL Java_com_badlogicgames_libimagequant_LiqImage__1destroy(JNIEnv* env, jclass clazz, jlong image) {


//@line:171

		liq_jni_image* jniImage = (liq_jni_image*)image;
		liq_image_destroy(jniImage->image);
		if (jniImage->data) free(jniImage->data);
		free(jniImage);
	

}

