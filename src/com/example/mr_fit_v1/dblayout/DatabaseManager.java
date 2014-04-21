package com.example.mr_fit_v1.dblayout;

import com.example.mr_fit_v1.dblayout.DatabaseOpenHelper.StatisticsCursor;
import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class DatabaseManager {
	private static final String LOGTAG = "DatabaseManager";
	
	private static SQLiteDatabase database = null;
	private static DatabaseOpenHelper databaseHelper = null;
	
	// Please pass appContext here
	public static void open(Context context) throws SQLException {
		if (databaseHelper == null) {
			databaseHelper = new DatabaseOpenHelper(context);
		}
		
		if (database == null) {
			database = databaseHelper.getWritableDatabase();
		}
		
		databaseHelper.onCreate(database);
	}
	
	public static void close() {
		database.close();
	}
	
	public static boolean insertStudent(ExerciseStatistics statistics) {		
		databaseHelper.insertExerciseStatistics(statistics);
		return true;
	}
	
	public static StatisticsCursor queryDayStatistics(int day, int month, int year) {
		return databaseHelper.queryDayStatistics(day, month, year);
	}
	
	public static StatisticsCursor queryWeekStatistics(int weekOfYear, int year) {
		return databaseHelper.queryWeekStatistics(weekOfYear, year);
	}
	
	public static StatisticsCursor queryMonthStatistics(int month, int year) {
		return databaseHelper.queryMonthStatistics(month, year);
	}
	
	public static boolean checkDatabaseStatus() {
		if (databaseHelper == null) {
			Log.e(LOGTAG, "database helper has not been created");
			return false;
		}
		
		if (database == null) {
			Log.e(LOGTAG, "database has not been created");
			return false;
		}
		
		return true;
	}
}
