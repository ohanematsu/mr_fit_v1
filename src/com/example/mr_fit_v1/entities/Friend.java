package com.example.mr_fit_v1.entities;

public class Friend {
	private int userId;
	private String userName;
	private String avatarId;
	private int status;

	public Friend() {
		
	}
	
	public Friend(int userId, String userName, String avatarId, int status) {
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

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
