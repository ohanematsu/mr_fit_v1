package com.example.mr_fit_v1.entities;

public class Friend {
	private int userId;
	private String userName;
	private String phoneNum;
	private int status;

	public Friend() {
		
	}
	
	public Friend(int userId, String userName, String phoneNum, int status) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setPhoneNum(phoneNum);
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
