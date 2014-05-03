package com.example.mr_fit_v1;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
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
		
		Intent receivedIntent = getActivity().getIntent();
		
		CurrentActivityStatistics statistics = (CurrentActivityStatistics)receivedIntent.
			getSerializableExtra(TrackingFragment.TRACKER_FRAGMENT_STATISTICS);
		if (statistics == null) {
			Log.e(LOGTAG, "Parse statistics fail...");
		}
		
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
		
		// TODO: Set up UI based on Report
		Log.i(LOGTAG, "Set up UI complete...");
		
		// Save statistics to database
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
