package com.example.mr_fit_v1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnCreateContextMenuListener;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;
import com.example.mr_fit_v1.entities.Report;
import com.example.mr_fit_v1.session.Session;

public class ReportFragment extends Fragment {
	private Report report;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onDestroy() {
		ExerciseStatistics statistics = new ExerciseStatistics(
			Session.getInstance().getUserId(), report);
		DatabaseManager.insertStatistics(statistics);
		super.onDestroy();
	}
}
