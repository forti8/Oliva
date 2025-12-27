#include "../include/Memory.h"
#include <stdlib.h>

JNIEXPORT jint JNICALL Java_compilador_libs_memo_Memory_nativeFree (JNIEnv *env, jobject obj, jlong loc) {
    free((void*)loc);
    return (jint) 1;
}