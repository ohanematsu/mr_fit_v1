package com.example.mr_fit_v1;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;
import com.example.mr_fit_v1.entities.CurrentActivityStatistics;
import com.example.mr_fit_v1.entities.Report;
import com.example.mr_fit_v1.session.Session;

public class ReportFragment extends Fragment {
	private static final String LOGTAG = "ReportFragment";
	private Report report;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGTAG, "Initilizing ReportFragment...");
		/*
		Intent receivedIntent = getActivity().getIntent();
		
		CurrentActivityStatistics statistics = (CurrentActivityStatistics)receivedIntent.
			getSerializableExtra(TrackingFragment.TRACKER_FRAGMENT_STATISTICS);
		if (statistics == null) {
			Log.e(LOGTAG, "Parse statistics fail...");
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Location> path = (ArrayList<Location>)receivedIntent.
			getSerializableExtra(TrackingFragment.TRACKER_FRAGMENT_PATH);
		if (path == null) {
			Log.e(LOGTAG, "Parse path fail...");
		}
		
		report = new Report(statistics, path);*/
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Create fragment view...");
		View view = inflater.inflate(R.layout.fragment_report, parent, false);
		
		/*
		// Set up UI based on Report
		TextView elapsedTextView = (TextView)view.findViewById(R.id.elapsedTimeTextView);
		elapsedTextView.setText(String.valueOf(report.getCurExerciseTime()));
		
		TextView distanceTextView = (TextView)view.findViewById(R.id.distanceTextView);
		distanceTextView.setText(String.valueOf(report.getDistance()));
		
		TextView speedTextView = (TextView)view.findViewById(R.id.speedTextView);
		speedTextView.setText(String.valueOf(report.getSpeed()));
		
		TextView burnedCalorieTextView = (TextView)view.findViewById(R.id.burnedCalorieTextView);
		burnedCalorieTextView.setText(String.valueOf(report.getCurBurnedCalorie()));
		Log.i(LOGTAG, "Set up UI complete...");
		
		// Save statistics to database
		@SuppressWarnings("unused")
		ExerciseStatistics statistics = new ExerciseStatistics(
				Session.getInstance().getUserId(), report);
		//DatabaseManager.insertStatistics(statistics);
		Log.i(LOGTAG, "Save data to database complete...");
		
		Log.i(LOGTAG, "Create fragment complete...");*/
		return view;
	}
	
	public void showPath() {
		// TODO: show path on map
	}
}
