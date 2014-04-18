package com.example.mr_fit_v1.entities;

public class CurrentActivityStatistics extends DetailedActivityStatistics {
	protected float speed;

	public CurrentActivityStatistics(int curExerciseTime, int curBurnedTime,
            						 int steps, float distance, float speed) {
		super(curExerciseTime, curBurnedTime, steps, distance);
		this.speed = speed;
	}
	
	public CurrentActivityStatistics() {
		super();
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
