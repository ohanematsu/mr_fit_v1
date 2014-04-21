package com.example.mr_fit_v1.entities;

import java.util.*;

import com.example.mr_fit_v1.util.CalorieCalculator;

public class CurrentActivityStatistics extends DetailedActivityStatistics {
	protected float speed;
	protected Calendar startTime;
	private Calendar lastTime;
	
	public CurrentActivityStatistics(int curExerciseTime, int curBurnedCalorie,
            						 float distance, float speed) {
		super(curExerciseTime, curBurnedCalorie, distance);
		this.speed = speed;
		this.startTime = Calendar.getInstance();
		this.lastTime = this.startTime;
	}
	
	public CurrentActivityStatistics() {
		super();
		this.startTime = Calendar.getInstance();
		this.lastTime = this.startTime;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Calendar getStartTime() {
		return startTime;
	}
	
	// If timer timeout, call this method to update
	final public void update(Calendar curTime) {
		update(curTime, 0.0f);
	}
	
	// If location changes, call this method to update
	final public void update(Calendar curTime, float newDistance) {
		// Update distance
		this.distance += newDistance;
		
		// Update curExerciseTime
		int duration = (int) (curTime.getTimeInMillis() - lastTime.getTimeInMillis()) / 1000;
		curExerciseTime += duration;
		lastTime = curTime;
		
		// Update average speed
		speed = distance / curExerciseTime;
				
		// Update curBurnedCalorie
		curBurnedCalorie += CalorieCalculator.calculateCalorie(newDistance, duration);
	}
}
