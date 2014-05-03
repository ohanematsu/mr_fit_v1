package com.example.mr_fit_v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.example.mr_fit_v1.entities.CurrentActivityStatistics;
import com.example.mr_fit_v1.entities.Report;
import com.example.mr_fit_v1.session.Session;
import com.example.mr_fit_v1.ws.local.ExerciseActivityManager;
import com.example.mr_fit_v1.ws.local.LocationReceiver;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.*;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class TrackingFragment extends Fragment {
	public static final String TRACKER_FRAGMENT_STATISTICS = 
		"com.example.mr_fit_v1.TrackerFragment.statistics";
	public static final String TRACKER_FRAGMENT_PATH = 
			"com.example.mr_fit_v1.TrackerFragment.path";
	
	private static final String LOGTAG = "TrackerFragment";
	
	private CurrentActivityStatistics currentStatistics;
	private ArrayList<Location> path;
	private Location lastLocation;
	private ExerciseActivityManager activityManager;
	private LocationManager locationManager;
	private Chronometer chronometer;
	
	private View view;
	
	/*
	private Timer timer;
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			currentStatistics.update(Calendar.getInstance());
		}
	};*/
	
	private LocationReceiver locationReceiver = new LocationReceiver() {
		@Override
		protected void onLocationReceived(Context context, Location location) {
			super.onLocationReceived(context, location);
			path.add(location);
			Log.i(LOGTAG, "Add to path list");
			if (lastLocation == null) {
				lastLocation = location;
			}
			Log.i(LOGTAG, "Check last know location complete...");
			float distance = lastLocation.distanceTo(location);
			lastLocation = location;
			if (isVisible()) {
				Log.i(LOGTAG, "Prepare to update ui");
				updateUI(distance);
			}
			Log.i(LOGTAG, "Update ~~~");
		}
		
		@Override	
		protected void onProviderEnableChanged(boolean enabled) {
			super.onProviderEnableChanged(enabled);
			String text = "Provider " + (enabled ? "Enabled" : "disabled");
			Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                 Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating fragment view...");
		view = inflater.inflate(R.layout.fragment_tracking, container, false);
		
		// Initiate private variables
		currentStatistics = new CurrentActivityStatistics(0, 0, 0.0f, 0.0f, 
			CurrentActivityStatistics.WALK);
		path = new ArrayList<Location>();
		activityManager = ExerciseActivityManager.getInstance(getActivity().getApplicationContext());
		
		// Get initial location
		Log.i(LOGTAG, "Require initial location...");
		locationManager = (LocationManager)getActivity().getApplicationContext()
			.getSystemService(Context.LOCATION_SERVICE);		
		lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		path.add(lastLocation);
		Log.i(LOGTAG, "Initialize location complete...");
		
		// Setup UI
		chronometer = (Chronometer)view.findViewById(R.id.chronometer);
		updateUI(0.0f);
		
		Log.i(LOGTAG, "Creating fragment view complete...");
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getActivity().registerReceiver(locationReceiver, 
			new IntentFilter(ExerciseActivityManager.ACTION_LOCATION));
		
		// Start timer so that the UI can update periodically even without any update of location
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				currentStatistics.update(Calendar.getInstance());
			}
		});
		chronometer.start();
						
		// Start tracking update of location
		activityManager.startLocationUpdates();
		Log.i(LOGTAG, "Start tracking activity...");
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
		
		// Update UI based on Statistics
		TextView distanceText = (TextView)view.findViewById(R.id.distanceTextView);
		distanceText.setText(String.valueOf(currentStatistics.getDistance()));
		
		TextView speedText = (TextView)view.findViewById(R.id.speedTextView);
		speedText.setText(String.valueOf(currentStatistics.getSpeed()));
		
		TextView burnedCalorieText = (TextView)view.findViewById(R.id.burnedCalorieTextView);
		burnedCalorieText.setText(String.valueOf(currentStatistics.getCurBurnedCalorie()));
	}
	
	public void onClickStop(View view) {
		Log.i(LOGTAG, "User clicks stop button");
		
		// Stop timer
		chronometer.stop();
		Log.i(LOGTAG, "Disable timer...");
		
		// Stop receiving location update
		getActivity().unregisterReceiver(locationReceiver);
		if (activityManager.isTracking()) {
			activityManager.stopLocationUpdates();
		}
		Log.i(LOGTAG, "stop receiving update of location...");
		
		// Update the last location and last exercise time
		Location stopLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		float distance = lastLocation.distanceTo(stopLocation);
		Calendar curTime = Calendar.getInstance();
		currentStatistics.update(curTime, distance);
		Session.getInstance().setLastExerciseTime(curTime);
		
		// Switch to ReportActivity
		Intent intent = new Intent(getActivity(), ReportActivity.class);
		intent.putExtra(TRACKER_FRAGMENT_STATISTICS, currentStatistics);
		startActivity(intent);
	}
}
