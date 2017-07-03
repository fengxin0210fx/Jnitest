//
// Created by EVA on 2017/6/26.
//
#include <string>
#include <jni.h>
#include "../../../../../../as_sdk/ndk-bundle/sources/cxx-stl/gnu-libstdc++/4.9/include/string"

extern "C"
JNIEXPORT jstring JNICALL

Java_com_example_eva_jnitest_NdkJniUtils_getSTRING(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "jnitest";
    return env->NewStringUTF(hello.c_str());
}
