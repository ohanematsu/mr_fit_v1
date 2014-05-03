package com.example.mr_fit_v1.entities;

import java.util.*;

import android.location.Location;

public class Report extends CurrentActivityStatistics {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<Location> path;
	protected Calendar endTime;
	
	public Report() {
		super();
		this.endTime = Calendar.getInstance();
	}
	
	public Report(int curExerciseTime, int curBurnedCalorie, int steps, float distance, 
			      float speed, ArrayList<Location> path, String type) {
		super(curExerciseTime, curBurnedCalorie, distance, speed, type);
		this.path = path;
		this.endTime = Calendar.getInstance();
	}
	
	public Report(CurrentActivityStatistics statistics, ArrayList<Location> path) {
		super(statistics.getCurExerciseTime(), statistics.getCurBurnedCalorie(),
			statistics.getDistance(), statistics.getSpeed(), statistics.getType());
		this.path = path;
		this.endTime = Calendar.getInstance();
	}
	
	public ArrayList<Location> getPath() {
		return path;
	}

	public void setPath(ArrayList<Location> path) {
		this.path = path;
	}
	
	public Calendar getEndTime() {
		return endTime;
	}
}
