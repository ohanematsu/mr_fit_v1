package com.example.mr_fit_v1.entities;

public class Settings {
	private String account;
	private boolean receiveFriendReminder;
	private int avatarId;
	private ExerciseSettings exerciseSettings;
	
	public Settings() {
		
	}
	
	public Settings(String account, boolean receiveFriendReminder, int avatarId,
			        ExerciseSettings exerciseSettings) {
		this.account = account;
		this.receiveFriendReminder = receiveFriendReminder;
		this.avatarId = avatarId;
		this.exerciseSettings = exerciseSettings;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public boolean isReceiveFriendReminder() {
		return receiveFriendReminder;
	}
	
	public void setReceiveFriendReminder(boolean receiveFriendReminder) {
		this.receiveFriendReminder = receiveFriendReminder;
	}
	
	public int getAvatarId() {
		return avatarId;
	}
	
	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
	
	public ExerciseSettings getExerciseSettings() {
		return exerciseSettings;
	}
	
	public void setExerciseSettings(ExerciseSettings exerciseSettings) {
		this.exerciseSettings = exerciseSettings;
	}
	
	public int getRemindTimeInterval() {
		return exerciseSettings.getRemindTimeInterval();
	}
	
	public void setRemindTimeInterval(int remindTimeInterval) {
		exerciseSettings.setRemindTimeInterval(remindTimeInterval);
	}
	
	public int getDayTotalExerciseGoal() {
		return exerciseSettings.getDayTotalExerciseGoal();
	}
	
	public void setDayTotalExerciseGoal(int dayTotalExerciseGoal) {
		exerciseSettings.setDayTotalExerciseGoal(dayTotalExerciseGoal);
	}
	
	public int getDayBurnedCaloriesGoal() {
		return exerciseSettings.getDayBurnedCaloriesGoal();
	}
	
	public void setDayBurnedCaloriesGoal(int dayBurnedCaloriesGoal) {
		exerciseSettings.setDayBurnedCaloriesGoal(dayBurnedCaloriesGoal);
	}
}
