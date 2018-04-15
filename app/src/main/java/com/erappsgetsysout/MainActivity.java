package com.erappsgetsysout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.InputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView ;
    ProcessBuilder processBuilder;
    String Holder = "";
    String Response = "";
    String[] DATA1 = {"/system/bin/cat", "/proc/cpuinfo"};
    String[] DATA2 = {"/system/bin/ls", "/system/bin/"};
    InputStream inputStream;
    Process process ;
    byte[] byteArry ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        byteArry = new byte[1024];

        // first command
        try{
            processBuilder = new ProcessBuilder(DATA1);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArry) != -1){
                Response = Holder + new String(byteArry);
            }
            inputStream.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }

        // second command
        try{
            processBuilder = new ProcessBuilder(DATA2);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArry) != -1){
                Response = Holder + Response + Holder + new String(byteArry);
            }
            inputStream.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }

        textView.setText(Response);


    }
}
