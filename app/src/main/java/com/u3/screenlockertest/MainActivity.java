package com.u3.screenlockertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by U3 on 2015/7/11.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = new Intent(this,ScreenLockService.class);
        startService(mIntent);//启动服务
    }
}
