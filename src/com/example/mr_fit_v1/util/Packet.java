package com.example.mr_fit_v1.util;

import java.io.Serializable;

public class Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8133032253767932395L;

	public static final int USER_DATA = 0;
	public static final int FRIEND_DATA = 1;
	
	private int userID;
	private int type;
	private Object payload;
	
	public Packet() {
		
	}
	
	public Packet(int userID, int type, Object payload) {
		this.userID = userID;
		this.type = type;
		this.payload = payload;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
