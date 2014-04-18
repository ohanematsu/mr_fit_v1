package com.example.mr_fit_v1.entities;

public class DayCurrentStatistics extends BasicActivityStatistics {
	protected int timeElapasedSinceLastExercise;
	protected ExerciseSettings exerciseSettings;
	
	public DayCurrentStatistics() {
		super();
	}
	
	public DayCurrentStatistics(int curExerciseTime, int curBurnedTime, 
								int timeElapasedSinceLastExercise,
								ExerciseSettings exerciseSettings) {
		super(curExerciseTime, curBurnedTime);
		this.timeElapasedSinceLastExercise = timeElapasedSinceLastExercise;
		this.exerciseSettings = exerciseSettings;
	}
	
	public int getTimeElapasedSinceLastExercise() {
		return timeElapasedSinceLastExercise;
	}
	
	public void setTimeElapasedSinceLastExercise(int timeElapasedSinceLastExercise) {
		this.timeElapasedSinceLastExercise = timeElapasedSinceLastExercise;
	}
	
	public int getRemindTimeInterval() {
		return exerciseSettings.getRemindTimeInterval();
	}
	
	public int getDayTotalExerciseGoal() {
		return exerciseSettings.getDayTotalExerciseGoal();
	}
	
	public int getDayBurnedCaloriesGoal() {
		return exerciseSettings.getDayBurnedCaloriesGoal();
	}
}
