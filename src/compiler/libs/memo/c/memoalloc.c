#include "../include/Memory.h"
#include <stdlib.h> 

JNIEXPORT jlong JNICALL Java_compilador_libs_memo_Memory_nativeAlloc (JNIEnv *env, jobject obj, jint tamanho) {
    void*  ptr = malloc((size_t) tamanho);

    if (ptr == NULL) {
        return 0;
    }

    return (jlong) ptr;
}