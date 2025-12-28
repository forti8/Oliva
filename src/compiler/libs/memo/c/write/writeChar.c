#include "../../include/Memory.h"

JNIEXPORT jint JNICALL Java_compiler_libs_memo_Memory_nativeWriteChar (JNIEnv *env, jobject obj, jlong loc, jchar val) {
    char* ptr = (char*)loc; 
    *ptr= (char)val;

    return 0;
}