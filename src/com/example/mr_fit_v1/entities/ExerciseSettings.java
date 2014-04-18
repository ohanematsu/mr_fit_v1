package com.example.mr_fit_v1.entities;

public class ExerciseSettings {
	private int remindTimeInterval;
	private int dayTotalExerciseGoal;
	private int dayBurnedCaloriesGoal;
	
	public ExerciseSettings(int remindTimeInterval, int dayTotalExerciseGoal,
			                int dayBurnedCaloriesGoal) {
		this.remindTimeInterval = remindTimeInterval;
		this.dayBurnedCaloriesGoal = dayBurnedCaloriesGoal;
		this.dayTotalExerciseGoal = dayTotalExerciseGoal;
	}
	
	public int getRemindTimeInterval() {
		return remindTimeInterval;
	}
	
	public void setRemindTimeInterval(int remindTimeInterval) {
		this.remindTimeInterval = remindTimeInterval;
	}
	
	public int getDayTotalExerciseGoal() {
		return dayTotalExerciseGoal;
	}
	
	public void setDayTotalExerciseGoal(int dayTotalExerciseGoal) {
		this.dayTotalExerciseGoal = dayTotalExerciseGoal;
	}
	
	public int getDayBurnedCaloriesGoal() {
		return dayBurnedCaloriesGoal;
	}
	
	public void setDayBurnedCaloriesGoal(int dayBurnedCaloriesGoal) {
		this.dayBurnedCaloriesGoal = dayBurnedCaloriesGoal;
	}
}
