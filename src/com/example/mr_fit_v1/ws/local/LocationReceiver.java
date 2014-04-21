package com.example.mr_fit_v1.ws.local;

import android.content.*;
import android.location.*;
import android.util.Log;

// This class is used in the fragment which tracks current activity. 
// It should be a inner class of that fragment
// TODO: Will do it later
public class LocationReceiver extends BroadcastReceiver {
	private static final String LOGTAG = "LocationReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Location location = (Location)intent
			.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
		// If the PendingIntent is sent due to the update of location, process the new location
		if (location != null) {
			onLocationReceived(context, location);
			return ;
		}
		
		// If the PendingIntent is sent due to the change of status of location provider
		if (intent.hasExtra(LocationManager.KEY_PROVIDER_ENABLED)) {
			boolean enabled = intent.getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED, false);
			onProviderEnableChanged(enabled);
		}
	}
	
	
	// This method will be overrided for specific usage
	protected void onLocationReceived(Context context, Location location) {
		Log.i(LOGTAG, "Get location from " + location.getProvider() + ": " +
	          location.getLatitude() + ", " + location.getLongitude());
	}
	
	// This method will be overrided for specific usage
	protected void onProviderEnableChanged(boolean enabled) {
		Log.i(LOGTAG, "Provider " + (enabled ? "Enabled" : "disabled"));
	}
}
