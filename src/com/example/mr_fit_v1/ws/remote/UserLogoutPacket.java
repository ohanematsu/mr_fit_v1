package com.example.mr_fit_v1.ws.remote;

public class UserLogoutPacket extends UserDataPacket {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104501128984680012L;
	
	private String account;
	
	public UserLogoutPacket(String account) {
		super(UserDataPacket.LOGOUT);
		this.account = account;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
