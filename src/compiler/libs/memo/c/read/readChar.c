#include "../../include/Memory.h"

JNIEXPORT jchar JNICALL Java_compiler_libs_memo_Memory_nativeReadChar (JNIEnv *env, jclass c, jlong loc) {
    char* ptr = (char*)loc;
    return (jchar) *ptr;
}