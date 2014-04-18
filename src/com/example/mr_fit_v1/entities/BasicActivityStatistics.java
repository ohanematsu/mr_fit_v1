package com.example.mr_fit_v1.entities;

public class BasicActivityStatistics {
	protected int curExerciseTime;
	protected int curBurnedTime;
	
	public BasicActivityStatistics() {
		
	}
	
	public BasicActivityStatistics(int curExerciseTime, int curBurnedTime) {
		this.curExerciseTime = curExerciseTime;
		this.curBurnedTime = curBurnedTime;
	}
	
	public int getCurExerciseTime() {
		return curExerciseTime;
	}
	
	public void setCurExerciseTime(int curExerciseTime) {
		this.curExerciseTime = curExerciseTime;
	}

	public int getCurBurnedTime() {
		return curBurnedTime;
	}

	public void setCurBurnedTime(int curBurnedTime) {
		this.curBurnedTime = curBurnedTime;
	}
}
