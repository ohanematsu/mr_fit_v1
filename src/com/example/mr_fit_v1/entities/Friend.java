package com.example.mr_fit_v1.entities;

public class Friend {
	
	public static final int JUST_FINISH_EXERCISE = 0;
	public static final int FINISH_EXERCISE_FOR_A_WHILE = 1;
	public static final int NEED_EXERCISE_NOW = 2;
	
	private int userId;
	private String userName;
	private int avatarId;
	private int status;

	public Friend() {
		
	}
	
	public Friend(int userId, String userName, int avatarId, int status) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setAvatarId(avatarId);
		this.setStatus(status);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
