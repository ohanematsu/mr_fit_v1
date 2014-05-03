package com.example.mr_fit_v1.ws.remote;

public class UserUpdatePacket extends UserDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object updateInfo;
	
	public UserUpdatePacket(int type, Object updateInfo) {
		super(type);
		this.setUpdateInfo(updateInfo);
	}

	public Object getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(Object updateInfo) {
		this.updateInfo = updateInfo;
	}
}
