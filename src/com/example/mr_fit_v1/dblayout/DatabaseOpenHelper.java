package com.example.mr_fit_v1.dblayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mr_fit_v1.dblayout.model.ExerciseStatistics;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	private static final String LOGTAG = "FriendDatabaseOpenHelper";
	
	private static final String DATABASE_NAME = "Friends";
	private static final int DATABASE_VERSION = 1;
	
	public static final String STATISTICS_TABLE_NAME                  = "exercise_statistics";
	public static final String STATISTICS_TABLE_COLUMN_EXERSIZE_ID    = "exercise_statistics_id";
	public static final String STATISTICS_TABLE_COLUMN_USERID         = "userid";
	public static final String STATISTICS_TABLE_COLUMN_DAY            = "day";
	public static final String STATISTICS_TABLE_COLUMN_WEEK           = "week";
	public static final String STATISTICS_TABLE_COLUMN_MONTH          = "month";
	public static final String STATISTICS_TABLE_COLUMN_YEAR           = "year";
	public static final String STATISTICS_TABLE_COLUMN_START_TIME     = "start_time";
	public static final String STATISTICS_TABLE_COLUMN_END_TIME       = "end_time";
	public static final String STATISTICS_TABLE_COLUMN_EXERCISE_TIME  = "exercise_time";
	public static final String STATISTICS_TABLE_COLUMN_TYPE           = "type";
	public static final String STATISTICS_TABLE_COLUMN_DISTANCE       = "distance";
	public static final String STATISTICS_TABLE_COLUMN_SPEED          = "speed";
	public static final String STATISTICS_TABLE_COLUMN_BURNED_CALORIE = "burned_calorie";
	
	private static final String STATISTICS_TABLE_CREATE_STATEMENT = 
		"CREATE TABLE " + STATISTICS_TABLE_NAME    + " ("                     +
			STATISTICS_TABLE_COLUMN_EXERSIZE_ID    + " INTEGER PRIMARY KEY, " + 
			STATISTICS_TABLE_COLUMN_USERID         + " INTEGER, "             + 
			STATISTICS_TABLE_COLUMN_DAY            + " INTEGER, "             + 
			STATISTICS_TABLE_COLUMN_WEEK           + " INTEGER, "             + 
			STATISTICS_TABLE_COLUMN_MONTH          + " INTEGER, "             + 
			STATISTICS_TABLE_COLUMN_YEAR           + " INTEGER, "             +
			STATISTICS_TABLE_COLUMN_START_TIME     + " TEXT, "                +
			STATISTICS_TABLE_COLUMN_END_TIME       + " TEXT, "                +
			STATISTICS_TABLE_COLUMN_EXERCISE_TIME  + " INTEGER, "             +
			STATISTICS_TABLE_COLUMN_TYPE           + " TEXT, "                +
			STATISTICS_TABLE_COLUMN_DISTANCE       + " REAL, "                +
			STATISTICS_TABLE_COLUMN_SPEED          + " REAL, "                + 
			STATISTICS_TABLE_COLUMN_BURNED_CALORIE + " INTEGER"               + ")";
	
	// Query for checking if a table already exsits.
	// To use this query, only need to append table name
	private static final String CHECK_TABLE_EXIST_STATEMENT = 
		"SELECT count(*) FROM sqlite_master WHERE type='table' AND name=";
	
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (!isStatisticsTableExisting()) {
			db.execSQL(STATISTICS_TABLE_CREATE_STATEMENT);
			Log.i(LOGTAG, "Table created");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + STATISTICS_TABLE_NAME);
		onCreate(db);
		Log.i(LOGTAG, "Database Upgraded");
	}
	
	public void insertExerciseStatistics(ExerciseStatistics statistics) {
		ContentValues row = new ContentValues();	
		row.put(STATISTICS_TABLE_COLUMN_USERID, statistics.getUserId());
		row.put(STATISTICS_TABLE_COLUMN_DAY, statistics.getDay());
		row.put(STATISTICS_TABLE_COLUMN_WEEK, statistics.getWeek());
		row.put(STATISTICS_TABLE_COLUMN_MONTH, statistics.getMonth());
		row.put(STATISTICS_TABLE_COLUMN_YEAR, statistics.getYear());
		row.put(STATISTICS_TABLE_COLUMN_START_TIME, statistics.getStartTime());
		row.put(STATISTICS_TABLE_COLUMN_END_TIME, statistics.getEndTime());
		row.put(STATISTICS_TABLE_COLUMN_EXERCISE_TIME, statistics.getExerciseTime());
		row.put(STATISTICS_TABLE_COLUMN_TYPE, statistics.getType());
		row.put(STATISTICS_TABLE_COLUMN_DISTANCE, statistics.getDistance());
		row.put(STATISTICS_TABLE_COLUMN_SPEED, statistics.getSpeed());
		row.put(STATISTICS_TABLE_COLUMN_BURNED_CALORIE, statistics.getBurnedCalorie());	
		
		getWritableDatabase().insert(STATISTICS_TABLE_NAME, null, row);
		Log.i(LOGTAG, "A new exercise statistics inserted");
	}
	
	public StatisticsCursor queryDayStatistics(int day, int month, int year) {
		String queryStatement = "SELECT * FROM " + STATISTICS_TABLE_NAME + 
			" WHERE day = " + day + " AND month = " + month + " AND year = " + year;
		Log.i(LOGTAG, "Query DAY Statement: " + queryStatement);
		return queryStatistics(queryStatement);
	}
	
	public StatisticsCursor queryWeekStatistics(int weekOfYear, int year) {
		String queryStatement = "SELECT * FROM " + STATISTICS_TABLE_NAME + 
			" WHERE week = " + weekOfYear + " AND year = " + year;
		Log.i(LOGTAG, "Query WEEK Statement: " + queryStatement);
		return queryStatistics(queryStatement);
	}
	
	public StatisticsCursor queryMonthStatistics(int month, int year) {
		String queryStatement = "SELECT * FROM " + STATISTICS_TABLE_NAME + 
			" WHERE month = " + month + " AND year = " + year;
		Log.i(LOGTAG, "Query MONTH Statement: " + queryStatement);
		return queryStatistics(queryStatement);
	}
	
	public StatisticsCursor queryStatistics(String queryStatement) {
		Cursor wrapped = getWritableDatabase().rawQuery(queryStatement, null);
		return new StatisticsCursor(wrapped);
	}
	
	private boolean isStatisticsTableExisting() {
        String query = CHECK_TABLE_EXIST_STATEMENT + "'" + STATISTICS_TABLE_NAME + "'";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        if(cursor.moveToNext()) {
        	int count = cursor.getInt(0);
            if (count > 0) {
            	return true;
            }
        }
        return false;
	}
	
	public static class StatisticsCursor extends CursorWrapper{

		public StatisticsCursor(Cursor cursor) {
			super(cursor);
			moveToFirst();
		}
		
		public ExerciseStatistics getExerciseStatistics() {
			if (isBeforeFirst() || isAfterLast()) {
				return null;
			}
			
			int userId = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_USERID));
			int day = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_DAY));
			int week = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_WEEK));
			int month = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_MONTH));
			int year = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_YEAR));
			String startTime = getString(getColumnIndex(STATISTICS_TABLE_COLUMN_START_TIME));
			String endTime = getString(getColumnIndex(STATISTICS_TABLE_COLUMN_END_TIME));
			int exertiseTime = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_EXERCISE_TIME));
			String type = getString(getColumnIndex(STATISTICS_TABLE_COLUMN_TYPE));
			float distance = getFloat(getColumnIndex(STATISTICS_TABLE_COLUMN_DISTANCE));
			float speed = getFloat(getColumnIndex(STATISTICS_TABLE_COLUMN_SPEED));
			int burnedCalorie = getInt(getColumnIndex(STATISTICS_TABLE_COLUMN_BURNED_CALORIE));		
			
			moveToNext();
			
			return new ExerciseStatistics(userId, day, week, month, year, startTime, endTime, 
				exertiseTime, type, distance, speed, burnedCalorie);
		}
	}
}
