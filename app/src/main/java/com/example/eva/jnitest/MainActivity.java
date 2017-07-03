package com.example.eva.jnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    SerialPortUtil mSerialPortUtil;

     String tag="MainActivity";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("NAT");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.send_et_port);
        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.test_open_port);
//        NdkJniUtils jni = new NdkJniUtils();
//
//        tv.setText(jni.getSTRING());

//        tv.setText(stringFromJNI());
      editText.setText("fsdfsfsdf");

    }

    public void play(View view) {
     switch (view.getId()){
         case R.id.test_open_port:
             Toast.makeText(this,"open",Toast.LENGTH_SHORT).show();
             mSerialPortUtil= SerialPortUtil.getInstance();
             mSerialPortUtil.setOnDataReceiveListener(new SerialPortUtil.OnDataReceiveListener() {
                 @Override
                 public void onDataReceive(final byte[] buffer, int size) {
                     Log.d(tag,new String(buffer));
                     Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();


                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             editText.setText(new String(buffer));
                         }
                     });



                 }
             });
             break;
         case R.id.test_close_port:

             Toast.makeText(this,"close",Toast.LENGTH_SHORT).show();
             mSerialPortUtil.closeSerialPort();

             break;
         case R.id.send_text_port:

             Toast.makeText(this,"text",Toast.LENGTH_SHORT).show();
             mSerialPortUtil.sendBuffer(editText.getText().toString().getBytes());
             mSerialPortUtil.sendCmds("sdffaaaaaaaasf");
             break;

     }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}
