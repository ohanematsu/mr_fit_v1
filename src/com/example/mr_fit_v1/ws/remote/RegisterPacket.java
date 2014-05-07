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
	private String phonenum;
	
	// Used for registration
	public RegisterPacket(String account, String password, String name, int avatarId, String gender, String phoneNum) {
		super(UserDataPacket.REGISTER);
		this.account = account;
		this.password = password;
		this.name = name;
		this.avatarId = avatarId;
		this.gender = gender;
		this.setPhonenum(phoneNum);
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

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}
