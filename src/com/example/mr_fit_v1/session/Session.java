package com.example.mr_fit_v1.session;

import com.example.mr_fit_v1.dblayout.DatabaseManager;

import android.content.Context;

public class Session {
	private int userId;
	private static Session instance;
	
	private Session(int userId) {
		this.setUserId(userId);
	}
	
	public static Session initSession(int userId, Context context) {
		if (instance == null) {
			instance = new Session(userId);
			DatabaseManager.open(context);
		}
		
		return instance;
	}
	
	public static Session getInstance() {
		return instance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
