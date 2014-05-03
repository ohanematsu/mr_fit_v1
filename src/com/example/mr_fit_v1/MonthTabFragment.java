package com.example.mr_fit_v1;

import java.util.Calendar;

import com.example.mr_fit_v1.R;
import com.example.mr_fit_v1.entities.DetailedStatistics;
import com.example.mr_fit_v1.util.FeedbackGenerator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 
public class MonthTabFragment extends Fragment {

	private static final String LOGTAG = "MonthTabFragment";
	
	private DetailedStatistics statistics;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating fragment view...");
		View view = inflater.inflate(R.layout.fragment_statistics_tab, container, false);
		
		// Get current time
		Calendar curTime = Calendar.getInstance();
		int month = curTime.get(Calendar.MONTH);
		int year = curTime.get(Calendar.YEAR);
		String timePeriod = "" + month + "//" + year;
		Log.i(LOGTAG, "Time period: " + timePeriod);
		
		// Retrieve data from database
		//StatisticsCursor cursor = DatabaseManager.queryMonthStatistics(month, year);
		Log.i(LOGTAG, "Retrieve data complete...");
		
		// Fill model
		//if (cursor.getCount() == 0) {
			Log.i(LOGTAG, "No data fulfill the requirement...");
			statistics = new DetailedStatistics(0, 0, 0.0f, timePeriod);
			statistics.setFeedback(FeedbackGenerator.EXTREMELY_BAD);
		/*} else {
			Log.i(LOGTAG, "Filling UI model...");
			statistics = new DetailedStatistics();
			statistics.setTimePeriod(timePeriod);
			ExerciseStatistics record = cursor.getExerciseStatistics();
			while (record != null) {
				statistics.setCurBurnedCalorie(statistics.getCurBurnedCalorie() + record.getBurnedCalorie());
				statistics.setCurExerciseTime(statistics.getCurExerciseTime() + record.getExerciseTime());
				statistics.setDistance(statistics.getDistance() + record.getDistance());
			}
			
			int burnedCalorieGoal = Session.getInstance().getSettings().getDayBurnedCaloriesGoal();
			statistics.setFeedback(FeedbackGenerator.getFeedback(
				burnedCalorieGoal, statistics.getCurBurnedCalorie()));
		}*/
		
		// Setup UI
		TextView exerciseTime = (TextView)view.findViewById(R.id.exerciseTimeTextView); 
		exerciseTime.setText(String.valueOf(statistics.getCurExerciseTime()));
		
		TextView distance = (TextView)view.findViewById(R.id.distanceTextView);
		distance.setText(String.valueOf(statistics.getDistance()));
		
		TextView burnedCalorie = (TextView)view.findViewById(R.id.burnedCalorieTextView);
		burnedCalorie.setText(String.valueOf(statistics.getCurBurnedCalorie()));
		Log.i(LOGTAG, "Set up UI complete...");
		
		Log.i(LOGTAG, "Creating fragment view complete...");
		return view;
	}
}
