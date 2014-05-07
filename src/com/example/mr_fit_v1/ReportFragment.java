package com.example.mr_fit_v1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;
import com.example.mr_fit_v1.entities.CurrentActivityStatistics;
import com.example.mr_fit_v1.entities.Report;
import com.example.mr_fit_v1.session.Session;

public class ReportFragment extends Fragment {
	private static final String LOGTAG = "ReportFragment";
	
	public static final String REPORT_FRAGMENT_PATH = "com.example.mr_fit_v1.ReportFragment.REPORT_FRAGMENT_PATH";
	
	private Report report;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGTAG, "Initilizing ReportFragment...");
		
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
		
		report = new Report(statistics, path);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Create fragment view...");
		View view = inflater.inflate(R.layout.fragment_report, parent, false);
		
		// Setup number format
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		df.applyPattern("0.0000");
		
		// Set up UI based on Report
		int hours = 0, minutes = 0, seconds = report.getCurExerciseTime();
		if (seconds > 60) {
			minutes = seconds / 60;
			seconds = seconds - minutes * 60;
		}
		if (minutes > 60) {
			hours = minutes / 60;
			minutes = minutes - hours * 60;
		}
		TextView elapsedTextView = (TextView)view.findViewById(R.id.elapsedTimeTextView);
		elapsedTextView.setText(hours + "h " + minutes + "min " + seconds + "s");
		
		TextView distanceTextView = (TextView)view.findViewById(R.id.distanceTextView);
		distanceTextView.setText(df.format(report.getDistance()) + " meters");
		
		TextView speedTextView = (TextView)view.findViewById(R.id.speedTextView);
		speedTextView.setText(df.format(report.getSpeed()) + " m/s");
		
		TextView burnedCalorieTextView = (TextView)view.findViewById(R.id.calorieTextView);
		burnedCalorieTextView.setText(df.format(report.getCurBurnedCalorie()));
		Log.i(LOGTAG, "Set up UI complete...");
		
		ImageButton mapButton = (ImageButton)view.findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MapActivity.class);
				intent.putExtra(REPORT_FRAGMENT_PATH, report.getPath());
				startActivity(intent);
			}
		});
		
		// Save statistics to database
		@SuppressWarnings("unused")
		ExerciseStatistics statistics = new ExerciseStatistics(
				Session.getInstance().getUserId(), report);
		DatabaseManager.insertStatistics(statistics);
		Log.i(LOGTAG, "Save data to database complete...");
		
		Log.i(LOGTAG, "Create fragment complete...");
		return view;
	}
	
	public void showPath() {
		// TODO: show path on map
	}
}
