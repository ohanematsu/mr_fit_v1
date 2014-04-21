package com.example.mr_fit_v1;

import java.util.ArrayList;

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
	private static final String LOGTAG = "TrackerFragment";
	
	private CurrentActivityStatistics currentStatistics;
	private ArrayList<Location> path;
	private Location lastLocation;
	private ExerciseActivityManager activityManager;
	
	private LocationReceiver locationReceiver = new LocationReceiver() {
		@Override
		protected void onLocationReceived(Context context, Location location) {
			Log.i(LOGTAG, "Get location from " + location.getProvider() + ": " +
		          location.getLatitude() + ", " + location.getLongitude());
			lastLocation = location;
			path.add(location);
			if (isVisible()) {
				updateUI();
			}
		}
		
		@Override	
		protected void onProviderEnableChanged(boolean enabled) {
			String text = "Provider " + (enabled ? "Enabled" : "disabled");
			Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
		}
	};
	
	/* TODO: Complete this function
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                 Bundle savedInstanceState) {
		
		// Initiate private variables
		currentStatistics = new CurrentActivityStatistics(0, 0, 0, 0);
		path = new ArrayList<Location>();
		activityManager.startLocationUpdates();
	}*/
	
	@Override
	public void onStart() {
		super.onStart();
		getActivity().registerReceiver(locationReceiver, 
			new IntentFilter(ExerciseActivityManager.ACTION_LOCATION));
	}
	
	/*
	@Override
	public void onStop() {
		//getActivity().unregisterReceiver(locationReceiver);
		super.onStop();
	}*/
	
	private void updateUI() {
		// TODO: 根据当前
	}

	private View onClickStop(View view) {
		getActivity().unregisterReceiver(locationReceiver);
		
		// 切换至ReportActivity
		
		return view;
	}
}
