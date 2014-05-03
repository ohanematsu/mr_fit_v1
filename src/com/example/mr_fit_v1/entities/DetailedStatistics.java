package com.example.mr_fit_v1.entities;

public class DetailedStatistics extends DetailedActivityStatistics {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String timePeriod;
	protected String feedback;
	
	public DetailedStatistics() {
		super();
	}
	
	public DetailedStatistics(int curExerciseTime, int curBurnedCalorie, float distance, 
							  String timePeriod) {
		super(curExerciseTime, curBurnedCalorie, distance);
		this.timePeriod = timePeriod;
	}
	
	public String getTimePeriod() {
		return timePeriod;
	}
	
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	public String getFeedback() {
		return feedback;
	}
	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
