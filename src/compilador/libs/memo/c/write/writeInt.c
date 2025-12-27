#include "../../include/Memory.h"

JNIEXPORT jint JNICALL Java_compilador_libs_memo_Memory_nativeWriteInt (JNIEnv *env, jobject obj, jlong loc, jint val) {
    int* ptr = (int*)loc; 
    *ptr= (int)val;

    return 0;
}