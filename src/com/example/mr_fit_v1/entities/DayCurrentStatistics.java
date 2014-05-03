package com.example.mr_fit_v1.entities;

public class DayCurrentStatistics extends BasicActivityStatistics {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int timeElapasedSinceLastExercise;
	protected ExerciseSettings exerciseSettings;
	
	public DayCurrentStatistics() {
		super();
	}
	
	public DayCurrentStatistics(int curExerciseTime, int curBurnedCalorie, 
								int timeElapasedSinceLastExercise,
								ExerciseSettings exerciseSettings) {
		super(curExerciseTime, curBurnedCalorie);
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
