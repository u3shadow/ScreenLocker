
package com.u3.screenlockertest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenLockService extends Service {
	Intent toMainIntent;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();

		toMainIntent = new Intent(ScreenLockService.this, ScreenLockActivity.class);
		toMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(screenReceiver,filter);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(screenReceiver);
	}
	
	private BroadcastReceiver screenReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("android.intent.action.SCREEN_ON") || action.equals("android.intent.action.SCREEN_OFF")) {
				startActivity(toMainIntent);
			}
		}
	};

}
