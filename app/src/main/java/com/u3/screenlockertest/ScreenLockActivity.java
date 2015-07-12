package com.u3.screenlockertest;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ScreenLockActivity extends Activity {
   private Button unlockButton;
   private SharedPreferences sharedPreferences;
    private Editor editor;
    private boolean isFront;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//解除系统锁屏
        setContentView(R.layout.main_layout);
        setView();//设置关闭按钮
        sharedPreferences = getSharedPreferences("homeChoice", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setView()
    {
        unlockButton = (Button)findViewById(R.id.bt_open);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenLockActivity.this.finish();
            }
        });
    }
    //屏蔽back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //屏蔽menu
    @Override
    public void onWindowFocusChanged(boolean pHasWindowFocus) {
        super.onWindowFocusChanged(pHasWindowFocus);
        if (!pHasWindowFocus) {
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        }
    }
        @Override
    protected void onStart() {
            isFront = true;
            editor.putBoolean("IsLocked",isFront);
            editor.commit();
            super.onStart();

    }
    @Override
    protected void onStop() {
        isFront = false;
        editor.putBoolean("IsLocked",isFront);
        editor.commit();
        super.onStop();
    }



}
