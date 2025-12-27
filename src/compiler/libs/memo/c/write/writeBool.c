#include "../../include/Memory.h"

JNIEXPORT jint JNICALL Java_compiler_libs_memo_Memory_nativeWriteBoolean (JNIEnv *env, jobject obj, jlong loc, jboolean val) {
    jboolean * ptr = (jboolean*) loc; 
    *ptr= val;

    return 0;
}