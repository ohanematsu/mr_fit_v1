package com.example.mr_fit_v1.ws.local;

import android.app.*;
import android.content.*;
import android.location.*;

// This class is used in the fragment which tracks current activity
public class ExerciseActivityManager {	
	@SuppressWarnings("unused")
	private static final String LOGTAG = "ExerciseActivityManager";
	
	public static final String ACTION_LOCATION = "com.example.mr_fit_v1.ws.local.ACTION_LOCATION";
	
	private static ExerciseActivityManager instance;
	private Context appContext;
	private LocationManager locationManager;
	
	private ExerciseActivityManager(Context context) {
		appContext = context;
		locationManager = (LocationManager)appContext.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public static ExerciseActivityManager getInstance(Context context) {
		if (instance == null) {
			instance = new ExerciseActivityManager(context.getApplicationContext());
		}
		return instance;
	}
	
	private PendingIntent getLocationPendingIntent(boolean shouldCreate) {
		Intent broadcastIntent = new Intent(ACTION_LOCATION);
		int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
		return PendingIntent.getBroadcast(appContext, 0, broadcastIntent, flags);
	}
	
	public void startLocationUpdates() {
		// Use network to get provider
		String networkLocationProvider = LocationManager.NETWORK_PROVIDER;
		
		// Register PendingIntent to broadcast location update information
		PendingIntent pi = getLocationPendingIntent(true);
		locationManager.requestLocationUpdates(networkLocationProvider, 0, 0, pi);
	}
	
	public void stopLocationUpdates() {
		PendingIntent locationUpdatePendingIntent = getLocationPendingIntent(false);
		
		// If PendingIntent exists, remove it
		if (locationUpdatePendingIntent != null) {
			locationManager.removeUpdates(locationUpdatePendingIntent);
			locationUpdatePendingIntent.cancel();
		}
	}
	
	public boolean isTracking() {
		return getLocationPendingIntent(false) != null;
	}
}
