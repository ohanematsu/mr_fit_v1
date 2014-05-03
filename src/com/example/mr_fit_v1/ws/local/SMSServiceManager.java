package com.example.mr_fit_v1.ws.local;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSServiceManager {

	private static Context appContext = null;
	private static boolean initilized = false;
	private static SmsManager smsMgr = null;
	
	public static void init(Context context) {
		// Please use application conext to initialize it
		appContext = context;
		smsMgr = SmsManager.getDefault();
		initilized = true;
	}
	
	public boolean isInitilized() {
		return initilized;
	}
	
	public static void sendSmsMessage(String address, String message) {
		// Send out SMS and show toast message
		try {
			smsMgr.sendTextMessage(address, null, message, null, null);
			Toast.makeText(appContext, "Reminder Sent", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(appContext, "Failed to send reminder", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}
