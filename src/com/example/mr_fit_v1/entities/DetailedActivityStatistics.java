package com.example.mr_fit_v1.entities;

public class DetailedActivityStatistics extends BasicActivityStatistics {
	protected int steps;
	protected float distance;
	
	public DetailedActivityStatistics() {
		super();
	}
	
	public DetailedActivityStatistics(int curExerciseTime, int curBurnedTime,
			                          int steps, float distance) {
		super(curExerciseTime, curBurnedTime);
		this.steps = steps;
		this.distance = distance;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
}
