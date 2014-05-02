package com.example.mr_fit_v1;

import java.util.Calendar;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
import com.example.mr_fit_v1.dblayout.DatabaseOpenHelper.StatisticsCursor;
import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;
import com.example.mr_fit_v1.entities.DetailedStatistics;
import com.example.mr_fit_v1.session.Session;
import com.example.mr_fit_v1.util.FeedbackGenerator;

import android.R.integer;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatisticsFragment extends Fragment {
	
	private static final String LOGTAG = "StatisticsFragment";
	
	private static final int DAY = 0;
	private static final int WEEK = 1;
	private static final int MONTH = 2;
	
	private DetailedStatistics statistics;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating fragment view...");
		View view = inflater.inflate(R.layout.fragment_statistic, container, false);
		
		// Judge statistics type (Day, Week or Month)
		Bundle bundle = getArguments();
		int type = bundle.getInt(StatisticActivity.QUERY_TYPE);
		Log.i(LOGTAG, "Query Statistics type = " + type);
		
		// Retrieve data from database
		StatisticsCursor cursor = null;
		String timePeriod = "";
		if (type == DAY) {
			Log.i(LOGTAG, "Retrieve day data...");
			int day = bundle.getInt(StatisticActivity.DAY);
			int month = bundle.getInt(StatisticActivity.MONTH);
			int year = bundle.getInt(StatisticActivity.YEAR);
			cursor = DatabaseManager.queryDayStatistics(day, month, year);
		} else if (type == WEEK) {
			Log.i(LOGTAG, "Retrieve week data...");
			int weekOfYear = bundle.getInt(StatisticActivity.WEEK);
			int year = bundle.getInt(StatisticActivity.YEAR);
			cursor = DatabaseManager.queryWeekStatistics(weekOfYear, year);
		} else {
			Log.i(LOGTAG, "Retrieve month data...");
			int month = bundle.getInt(StatisticActivity.MONTH);
			int year = bundle.getInt(StatisticActivity.YEAR);
			cursor = DatabaseManager.queryMonthStatistics(month, year);
		}
		Log.i(LOGTAG, "Retrieve data complete...");
		
		// Fill model
		if (cursor.getCount() == 0) {
			Log.i(LOGTAG, "No data fulfill the requirement...");
			statistics = new DetailedStatistics(0, 0, 0.0f, timePeriod);
			statistics.setFeedback(FeedbackGenerator.EXTREMELY_BAD);
		} else {
			Log.i(LOGTAG, "Filling UI model...");
			statistics = new DetailedStatistics();
			statistics.setTimePeriod(timePeriod);
			ExerciseStatistics record = cursor.getExerciseStatistics();
			while (record != null) {
				statistics.setCurBurnedCalorie(statistics.getCurBurnedCalorie() + record.getBurnedCalorie());
				statistics.setCurExerciseTime(statistics.getCurExerciseTime() + record.getExerciseTime());
				statistics.setDistance(statistics.getDistance() + record.getDistance());
			}
			
			int burnedCalorieGoal = 0;
			if (type == DAY) {
				burnedCalorieGoal = Session.getInstance().getSettings().getDayBurnedCaloriesGoal();
			} else if (type == WEEK) {
				burnedCalorieGoal = 7 * Session.getInstance().getSettings().getDayBurnedCaloriesGoal();
			} else {
				burnedCalorieGoal = 30 * Session.getInstance().getSettings().getDayBurnedCaloriesGoal();
			}
			
			statistics.setFeedback(FeedbackGenerator.getFeedback(
				burnedCalorieGoal, statistics.getCurBurnedCalorie()));
		}
		
		// TODO: Setup UI
		Log.i(LOGTAG, "Set up UI complete...");
		
		Log.i(LOGTAG, "Creating fragment view complete...");
		return view;
	}
}
