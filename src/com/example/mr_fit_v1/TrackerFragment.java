package com.example.mr_fit_v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.example.mr_fit_v1.entities.CurrentActivityStatistics;
import com.example.mr_fit_v1.entities.Report;
import com.example.mr_fit_v1.ws.local.ExerciseActivityManager;
import com.example.mr_fit_v1.ws.local.LocationReceiver;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Toast;

public class TrackerFragment extends Fragment {
	public static final String TRACKER_FRAGMENT_STATISTICS = 
		"com.example.mr_fit_v1.TrackerFragment.statistics";
	public static final String TRACKER_FRAGMENT_PATH = 
			"com.example.mr_fit_v1.TrackerFragment.path";
	
	private static final String LOGTAG = "TrackerFragment";
	
	private CurrentActivityStatistics currentStatistics;
	private ArrayList<Location> path;
	private Location lastLocation;
	private ExerciseActivityManager activityManager;
	
	private Timer timer;
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			currentStatistics.update(Calendar.getInstance());
		}
	};
	
	private LocationReceiver locationReceiver = new LocationReceiver() {
		@Override
		protected void onLocationReceived(Context context, Location location) {
			Log.i(LOGTAG, "Get location from " + location.getProvider() + ": " +
		          location.getLatitude() + ", " + location.getLongitude());
			path.add(location);
			float distance = lastLocation.distanceTo(location);
			lastLocation = location;
			if (isVisible()) {
				updateUI(distance);
			}
		}
		
		@Override	
		protected void onProviderEnableChanged(boolean enabled) {
			String text = "Provider " + (enabled ? "Enabled" : "disabled");
			Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                 Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating fragment...");
		
		// Initilize (not sure about if it is correct to use this fragment)
		View view = inflater.inflate(R.layout.fragment_tracker, container, false);
		// TODO: Initilize some other things on the screen
		
		// Initiate private variables
		currentStatistics = new CurrentActivityStatistics(0, 0, 0.0f, 0.0f, 
			CurrentActivityStatistics.WALK);
		path = new ArrayList<Location>();
		activityManager.startLocationUpdates();
		
		// Get initial location
		Log.i(LOGTAG, "Require initial location...");
		LocationManager locationManager = (LocationManager)getActivity().getApplicationContext()
			.getSystemService(Context.LOCATION_SERVICE);		
		lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		path.add(lastLocation);
		Log.i(LOGTAG, "Initialize location complete...");
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getActivity().registerReceiver(locationReceiver, 
			new IntentFilter(ExerciseActivityManager.ACTION_LOCATION));
		timer = new Timer();
		timer.schedule(timerTask, 1000);
	}
	
	@Override
	public void onStop() {
		getActivity().unregisterReceiver(locationReceiver);
		super.onStop();
	}
	
	private void updateUI(float distance) {
		// Update Model
		Calendar curTime = Calendar.getInstance();
		currentStatistics.update(curTime, distance);
		
		// TODO: Update UI based on Statistics
	}

	private void onClickStop(View view) {
		// Stop receiving location update
		getActivity().unregisterReceiver(locationReceiver);
		activityManager.stopLocationUpdates();
		
		// Stop timer
		if (timer!=null){
			timer.cancel();
			timer = null;
		}
		
		// Switch to ReportActivity
		Intent intent = new Intent(getActivity(), ReportActivity.class);
		intent.putExtra(TRACKER_FRAGMENT_STATISTICS, currentStatistics);
		startActivity(intent);
	}
}
