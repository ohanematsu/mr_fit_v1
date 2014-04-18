package com.example.mr_fit_v1.entities;

import java.util.Date;

public class DetailedStatistics extends DetailedActivityStatistics {
	protected Date date;
	protected String feedback;
	
	public DetailedStatistics() {
		super();
	}
	
	public DetailedStatistics(int curExerciseTime, int curBurnedTime, int steps,
            				  float distance, Date date, String feedback) {
		super(curExerciseTime, curBurnedTime, steps, distance);
		this.date = date;
		this.feedback = feedback;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getFeedback() {
		return feedback;
	}
	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
