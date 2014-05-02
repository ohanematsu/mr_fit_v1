package com.example.mr_fit_v1.session;

import java.util.Calendar;
import java.util.Properties;
import java.io.*;

import com.example.mr_fit_v1.dblayout.DatabaseManager;
import com.example.mr_fit_v1.entities.ExerciseSettings;
import com.example.mr_fit_v1.entities.Settings;

import android.content.Context;
import android.util.Log;

public class Session {
	
	private static final String LOGTAG = "Session";
	
	private final String CONFIG_FILE                      = "Config.txt";
	private final int DEFAULT_REMIND_TIME_INTERVAL        = 1440;         // miuntes
	private final int DEFAULT_DAY_TOTAL_EXERCISE_GOAL     = 90;           // miuntes
	private final int DEFAULT_DAY_BURNED_CALORIES_GOAL    = 2000; 		  
	private final boolean DEFAULT_RECEIVE_FRIEND_REMINDER = true;
	private final int DEFAULT_AVARAT_ID                   = 1;			

	private static Session instance;
	
	private int userId;
	private Settings settings;
	private Calendar lastExerciseTime;
	
	private Session(int userId, String account) {
		this.userId = userId;
		initSettings(account);
		setLastExerciseTime(Calendar.getInstance());
		Log.i(LOGTAG, "Initialize session succcess");
	}
	
	private void initSettings(String account) {
		Properties props= new Properties();
		try {
			// Read file
			FileInputStream in = new FileInputStream(CONFIG_FILE);
			props.load(in);
			Log.i(LOGTAG, "Load config file succcess");
			
			// Init Exercise Settings
			int remindTimeInterval = Integer.parseInt(props.getProperty("remindTimeInterval"));
			int dayTotalExerciseGoal = Integer.parseInt(props.getProperty("dayTotalExerciseGoal"));
	        int dayBurnedCaloriesGoal = Integer.parseInt(props.getProperty("dayBurnedCaloriesGoal"));		
			ExerciseSettings exerciseSettings = new ExerciseSettings(remindTimeInterval, 
				dayTotalExerciseGoal, dayBurnedCaloriesGoal);
			Log.i(LOGTAG, "Load exercise settings succcess");
			
			// Init Setting
			boolean receiveFriendReminder = Boolean.parseBoolean(props.getProperty("receiveFriendReminder"));
			int avatarId = Integer.parseInt(props.getProperty("avatarId"));
			settings = new Settings(account, receiveFriendReminder, avatarId, exerciseSettings);
			Log.i(LOGTAG, "Load settings succcess");
		} catch (IOException e) {
			// Cannot find config file, use default settings
			// Load default Exercise Settings
			ExerciseSettings exerciseSettings = new ExerciseSettings(DEFAULT_REMIND_TIME_INTERVAL, 
				DEFAULT_DAY_TOTAL_EXERCISE_GOAL, DEFAULT_DAY_BURNED_CALORIES_GOAL);
			Log.i(LOGTAG, "Load exercise settings succcess, using default value");
						
			// Init Setting
			settings = new Settings(account, DEFAULT_RECEIVE_FRIEND_REMINDER, 
				DEFAULT_AVARAT_ID, exerciseSettings);
			Log.i(LOGTAG, "Load settings succcess, using default value");
		}
	}
	
	public static Session initSession(int userId, String account, Context context) {
		if (instance == null) {
			instance = new Session(userId, account);
			DatabaseManager.open(context);
		}
		
		return instance;
	}
	
	public static Session getInstance() {
		return instance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Settings getSettings() {
		return settings;
	}

	public Calendar getLastExerciseTime() {
		return lastExerciseTime;
	}

	public void setLastExerciseTime(Calendar lastExerciseTime) {
		this.lastExerciseTime = lastExerciseTime;
	}
}
