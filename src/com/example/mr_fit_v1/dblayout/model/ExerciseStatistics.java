package com.example.mr_fit_v1.dblayout.model;

import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.util.Log;

public class ExerciseStatistics {
	public static final String WALK = "WALK";
	public static final String RUN = "RUN";
	
	private static final String LOGTAG = "ExerciseStatistics";
	
	private int userId;
	private int day;
	private int week;
	private int month;
	private int year;
	private String startTime;
	private String endTime;
	private String type;
	private float distance;
	private float speed;
	private int burnedCalorie;
	
	public ExerciseStatistics() {
		
	}
	
	public ExerciseStatistics(int userId, int day, int week, int month, int year, 
			                  String startTime, String endTime, String type,
							  float distance, float speed, int burnedCalorie) {
		this.userId = userId;
		this.day = day;
		this.week = week;
		this.month = month;
		this.year = year;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.distance = distance;
		this.speed = speed;
		this.burnedCalorie = burnedCalorie;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getBurnedCalorie() {
		return burnedCalorie;
	}

	public void setBurnedCalorie(int burnedCalorie) {
		this.burnedCalorie = burnedCalorie;
	}
}