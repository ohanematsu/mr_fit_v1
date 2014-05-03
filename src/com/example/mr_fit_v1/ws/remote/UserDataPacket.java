package com.example.mr_fit_v1.ws.remote;

import java.io.Serializable;


public abstract class UserDataPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2455742824936435544L;	
	
	public static final int REGISTER            = 0;
	public static final int LOGIN               = 1;
	public static final int LOGOUT              = 2;
	public static final int UPDATE_EMAIL        = 3;
	public static final int UPDATE_PASSWORD     = 4;
	public static final int UPDATE_NAME         = 5;
	public static final int UPDATE_AVATAR_IMAGE = 6;
	public static final int UPDATE_STATUS       = 7;
	public static final int RESPONSE            = 8;
	
	protected int type;	
	
	public UserDataPacket(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
