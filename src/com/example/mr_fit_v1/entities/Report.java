package com.example.mr_fit_v1.entities;

import java.util.*;

import android.location.Location;

public class Report extends CurrentActivityStatistics {
	protected ArrayList<Location> path;

	public Report() {
		super();
	}
	
	public Report(int curExerciseTime, int curBurnedTime, int steps, 
			      float distance, float speed, ArrayList<Location> path) {
		super(curExerciseTime, curBurnedTime, steps, distance, speed);
		this.path = path;
	}
	
	public ArrayList<Location> getPath() {
		return path;
	}

	public void setPath(ArrayList<Location> path) {
		this.path = path;
	}
	

}
