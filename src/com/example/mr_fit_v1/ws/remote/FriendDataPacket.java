package com.example.mr_fit_v1.ws.remote;

import java.io.Serializable;


public abstract class FriendDataPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104149797081468881L;
	
	public static int SEARCH = 0;
	public static int REQUEST_FRIEND_LIST = 1;
	public static int SEARCH_RESPONSE = 2;
	public static int REQUEST_FRIEND_LIST_REPOSNE = 3;
	
	protected int type;
	
	public FriendDataPacket(int type) {
		this.setType(type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
