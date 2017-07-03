package com.example.eva.jnitest;

/**
 * Created by EVA on 2017/6/26.
 */

public class NdkJniUtils {

    static {
        System.loadLibrary("TEST");
    }
    public native String getSTRING();

}
