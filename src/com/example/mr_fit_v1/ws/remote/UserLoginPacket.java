package com.example.mr_fit_v1.ws.remote;

public class UserLoginPacket extends UserDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7028211144811464819L;
	
	private String account;
	private String password;
	
	// Used for login
	public  UserLoginPacket(String account, String password) {
		super(UserDataPacket.LOGIN);
		this.account = account;
		this.password = password;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
