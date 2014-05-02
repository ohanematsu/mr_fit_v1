package com.example.mr_fit_v1.entities;

import java.io.Serializable;

public class BasicActivityStatistics implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -155599592640101663L;
	
	protected int curExerciseTime;
	protected int curBurnedCalorie;
	
	public BasicActivityStatistics() {
		
	}
	
	public BasicActivityStatistics(int curExerciseTime, int curBurnedCalorie) {
		this.curExerciseTime = curExerciseTime;
		this.curBurnedCalorie = curBurnedCalorie;
	}
	
	public int getCurExerciseTime() {
		return curExerciseTime;
	}
	
	public void setCurExerciseTime(int curExerciseTime) {
		this.curExerciseTime = curExerciseTime;
	}

	public int getCurBurnedCalorie() {
		return curBurnedCalorie;
	}

	public void setCurBurnedCalorie(int curBurnedCalorie) {
		this.curBurnedCalorie = curBurnedCalorie;
	}
}
