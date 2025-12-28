#include "../../include/Memory.h"

JNIEXPORT jint JNICALL Java_compiler_libs_memo_Memory_nativeReadInt (JNIEnv *env, jclass c, jlong loc) {
    int* ptr = (int*)loc;
    return (jint) *ptr;
}