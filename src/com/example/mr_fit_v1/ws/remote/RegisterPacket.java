package com.example.mr_fit_v1.ws.remote;

public class RegisterPacket extends UserDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2277282238229468560L;
		
	private String account;
	private String password;
	private String name;
	private int avatarId;
	private String gender;
	private int status;
	
	// Used for registration
	public RegisterPacket(String account, String password, String name, int avatarId, String gender) {
		super(UserDataPacket.REGISTER);
		this.account = account;
		this.password = password;
		this.name = name;
		this.avatarId = avatarId;
		this.gender = gender;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
