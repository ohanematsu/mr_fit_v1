package com.example.mr_fit_v1.dblayout;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StatisticsDatabaseManager {
	private static SQLiteDatabase database = null;
	private static StatisticsDatabaseOpenHelper databaseHelper = null;
	
	public static void open(Context context) throws SQLException {
		if (databaseHelper == null) {
			databaseHelper = new StatisticsDatabaseOpenHelper(context);
		}
		
		if (database == null) {
			database = databaseHelper.getWritableDatabase();
		}
		
		databaseHelper.onCreate(database);
	}
	
	public static void close() {
		database.close();
	}
}
