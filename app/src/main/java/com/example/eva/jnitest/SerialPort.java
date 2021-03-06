package com.example.eva.jnitest;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by EVA on 2017/6/26.
 */

public class SerialPort {
    static {
        System.loadLibrary("Port");
    }
    private static final String TAG = "SerialPort";

    /*
    * Do not remove or rename the field mFd: it is used by native method close();
    */
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    public SerialPort(File device, int baudrate) throws SecurityException, IOException {

/* Check access permission */
        if (!device.canRead() || !device.canWrite()) {


            try {
/* Missing read/write permission, trying to chmod the file */
                Process su;
                su = Runtime.getRuntime().exec("su");
                String cmd = "chmod 777 " + device.getAbsolutePath() + "\n"
                        + "exit\n";
/*String cmd = "chmod 777 /dev/s3c_serial0" + "\n"
+ "exit\n";*/
                su.getOutputStream().write(cmd.getBytes());
                if ((su.waitFor() != 0) || !device.canRead()
                        || !device.canWrite()) {
                    throw new SecurityException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.toString();
                throw new SecurityException();
            }
        }


        try {
            mFd = open(device.getAbsolutePath(), baudrate);
        }catch (Exception e){
            e.toString();
        }

        if (mFd == null) {
            Log.e(TAG, "native open returns null");
//            device.toString();
            throw new IOException();
        }
        mFileInputStream = new FileInputStream(mFd);
        mFileOutputStream = new FileOutputStream(mFd);
    }

    // Getters and setters
    public InputStream getInputStream() {
        return mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return mFileOutputStream;
    }

    // JNI
    private native static FileDescriptor open(String path, int baudrate);
    public native void close();

}
