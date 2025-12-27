#include "../../include/Memory.h"

JNIEXPORT jfloat JNICALL Java_compilador_libs_memo_Memory_nativeReadFloat (JNIEnv *env, jclass c, jlong loc) {
    float* ptr = (float*)loc;
    return (jfloat) *ptr;
}