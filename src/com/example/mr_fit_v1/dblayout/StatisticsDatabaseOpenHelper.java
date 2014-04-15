package com.example.mr_fit_v1.dblayout;

import android.content.Context;
import android.database.sqlite.*;

public class StatisticsDatabaseOpenHelper extends SQLiteOpenHelper{
	private static final String LOGTAG = "StatisticsDatabaseOpenHelper";
	
	private static final String DATABASE_NAME = "Statistics";
	private static final int DATABASE_VERSION = 1;
	
	/*
	private static final String TABLE_NAME = "students";
	public static final String COLUMN_STUDENT_ID = "student_id";
	public static final String COLUMN_QUIZ_1 = "quiz1";
	public static final String COLUMN_QUIZ_2 = "quiz2";
	public static final String COLUMN_QUIZ_3 = "quiz3";
	public static final String COLUMN_QUIZ_4 = "quiz4";
	public static final String COLUMN_QUIZ_5 = "quiz5";
	
	private static final String TABLE_CREATE_STATEMENT = 
		"CREATE TABLE " + TABLE_NAME + " (" +
	    COLUMN_STUDENT_ID + " INTEGER, " + 
	    COLUMN_QUIZ_1 + " INTEGER, " + 
	    COLUMN_QUIZ_2 + " INTEGER, " + 
	    COLUMN_QUIZ_3 + " INTEGER, " + 
	    COLUMN_QUIZ_4 + " INTEGER, " + 
	    COLUMN_QUIZ_5 + " INTEGER " + ")";*/
		
	//private static final String DATABASE_QUERY_ALL_STUDENT_STATEMENT = 
	//	"select * from students";
	
	
	public StatisticsDatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		//db.execSQL(TABLE_CREATE_STATEMENT);
		//Log.i(LOGTAG, "Table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		//onCreate(db);
	}
}
