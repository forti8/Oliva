#include "../../include/Memory.h"

JNIEXPORT jint JNICALL Java_compilador_libs_memo_Memory_nativeWriteFloat (JNIEnv *env, jobject obj, jlong loc, jfloat val) {
    float* ptr = (float*)loc; 
    *ptr= (float)val;

    return 0;
}