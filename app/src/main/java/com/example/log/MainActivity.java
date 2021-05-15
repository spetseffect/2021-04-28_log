package com.example.log;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//import static com.example.log.R.*;

public class MainActivity extends AppCompatActivity {
    private LinearLayout lred, lorange, lgreen;
    private Button b1;
    private boolean start_stop = false;
    private long a=System.currentTimeMillis();
    private long b;

    protected void Logger(String tag){
        b=System.currentTimeMillis();
        Log.d(tag+": ",(b-a)+"ms");
        a=b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lred = findViewById(R.id.redLayout);
        lorange = findViewById(R.id.orangeLayout);
        lgreen = findViewById(R.id.greenLayout);
        b1 = findViewById(R.id.button1);
    }

    @SuppressLint("ResourceAsColor")
    public void Start(View view) {
        if (!start_stop) {
            Logger("btnStart");
            b1.setText(R.string.text_b1_stop);
            start_stop = true;
            new Thread(new Runnable() {
                final int default_grey = getResources().getColor(R.color.default_grey);
                final int red = getResources().getColor(R.color.red);
                final int orange = getResources().getColor(R.color.orange);
                final int green = getResources().getColor(R.color.green);
                @Override
                public void run() {
                    int counter = 0;
                    while (start_stop) {
                        try {
                            switch (counter) {
                                case 0:
                                    lred.setBackgroundColor(red);
                                    lorange.setBackgroundColor(default_grey);
                                    break;
                                case 3:
                                    lorange.setBackgroundColor(orange);
                                    break;
                                case 4:
                                    lred.setBackgroundColor(default_grey);
                                    lorange.setBackgroundColor(default_grey);
                                    lgreen.setBackgroundColor(green);
                                    break;
                                case 8: case 10: case 12:
                                    lgreen.setBackgroundColor(green);
                                    break;
                                case 7: case 9: case 11:
                                    lgreen.setBackgroundColor(default_grey);
                                    break;
                                case 13:
                                    lgreen.setBackgroundColor(default_grey);
                                    lorange.setBackgroundColor(orange);
                                    counter = -1;
                                    break;
                            }
                            Thread.sleep(1000);
                            counter++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            Logger("btnStop");
            start_stop = false;
            b1.setText(R.string.text_b1_start);
            lred.setBackgroundColor(R.color.default_grey);
            lorange.setBackgroundColor(R.color.default_grey);
            lgreen.setBackgroundColor(R.color.default_grey);
        }
    }

    @Override
    protected void onStart() {
        Logger("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Logger("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logger("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger("onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Logger("onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Logger("onDestroy");
        super.onDestroy();
        start_stop = false;
    }
}