#include "../../include/Memory.h"

JNIEXPORT jboolean JNICALL Java_compiler_libs_memo_Memory_nativeReadBoolean (JNIEnv *env, jclass c, jlong loc) {
    jboolean* ptr = (jboolean*)loc;
    return (jboolean) *ptr;
}