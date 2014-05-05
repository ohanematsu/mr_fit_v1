package com.example.mr_fit_v1;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
import com.example.mr_fit_v1.dblayout.DatabaseOpenHelper.StatisticsCursor;
import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;
import com.example.mr_fit_v1.entities.DayCurrentStatistics;
import com.example.mr_fit_v1.session.Session;

public class SecondFragment extends Fragment {
private static final String LOGTAG = "SecondFragment";
	
	private DayCurrentStatistics statistics;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating Fragment view...");
		View view = inflater.inflate(R.layout.fragment_second, container, false);
		
		// Calculate how much time has passed since last exercise
		Calendar curTime = Calendar.getInstance();
		long diff = curTime.getTimeInMillis() - 
			Session.getInstance().getLastExerciseTime().getTimeInMillis(); // in milliseconds
		long diffMin = diff / (60 * 1000);
		Log.i(LOGTAG, "Calculate time difference complete...");
		
		// Retrieve data from database
		StatisticsCursor cursor = DatabaseManager.queryDayStatistics(curTime.get(Calendar.DATE),
			curTime.get(Calendar.MONTH),curTime.get(Calendar.YEAR));
		Log.i(LOGTAG, "Retrieve today's data success...");
		
		// Setup UI Model
		statistics = new DayCurrentStatistics(0, 0, (int)diffMin, 
			Session.getInstance().getSettings().getExerciseSettings());
		if (cursor.getCount() != 0) {
			ExerciseStatistics record = cursor.getExerciseStatistics();
			while (record != null) {
				statistics.setCurBurnedCalorie(statistics.getCurBurnedCalorie() + record.getBurnedCalorie());
				statistics.setCurExerciseTime(statistics.getCurExerciseTime() + record.getExerciseTime());
				record = cursor.getExerciseStatistics();
			}
		}
		Log.i(LOGTAG, "Set up UI model complete...");
		
		// TODO: Setup UI
		TextView sinceLastExerciseTime = (TextView)view.findViewById(R.id.elapsed);
		sinceLastExerciseTime.setText(String.valueOf(statistics.getTimeElapasedSinceLastExercise()));
				
		TextView exerciseTime = (TextView)view.findViewById(R.id.textView3);
		exerciseTime.setText(String.valueOf(statistics.getCurExerciseTime()));
		
		TextView burnedCalorie = (TextView)view.findViewById(R.id.textView4);
		burnedCalorie.setText(String.valueOf(statistics.getCurBurnedCalorie()));
		Log.i(LOGTAG, "Set up UI complete...");
		
		Log.i(LOGTAG, "Create Fragment view complete...");
		return view;
	}

}
