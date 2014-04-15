package com.example.mr_fit_v1.dblayout;

import java.util.ArrayList;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FriendDatabaseManager {
	private static SQLiteDatabase database = null;
	private static FriendDatabaseOpenHelper databaseHelper = null;
	
	public static void open(Context context) throws SQLException {
		if (databaseHelper == null) {
			databaseHelper = new FriendDatabaseOpenHelper(context);
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
