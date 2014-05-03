package com.example.mr_fit_v1.ws.remote;

public class RegisterResponsePacket extends UserDataPacket {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	public RegisterResponsePacket(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public void setUserId(int id){
		this.userId = id;
	}
	public int getUserId(){
		return this.userId;
	}
	

}
