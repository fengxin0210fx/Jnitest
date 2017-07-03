#include <jni.h>
#include <string>

//
// Created by EVA on 2017/6/26.
//
extern "C"
    JNIEXPORT jstring JNICALL

    Java_com_example_eva_jnitest_NdkJniUtils_getSTRING(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = ".jhlhjghgfses.";
        return env->NewStringUTF(hello.c_str());
}
