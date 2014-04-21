package com.example.mr_fit_v1.entities;

public class DetailedActivityStatistics extends BasicActivityStatistics {
	protected float distance;
	
	public DetailedActivityStatistics() {
		super();
	}
	
	public DetailedActivityStatistics(int curExerciseTime, int curBurnedCalorie, float distance) {
		super(curExerciseTime, curBurnedCalorie);
		this.distance = distance;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
}
