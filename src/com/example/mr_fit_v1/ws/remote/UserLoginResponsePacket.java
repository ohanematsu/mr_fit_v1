package com.example.mr_fit_v1.ws.remote;

public class UserLoginResponsePacket extends UserDataPacket {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean permit = false;

	public UserLoginResponsePacket(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void setPermit(boolean permit){
		this.permit = permit;
	}
	public boolean getPermit(){
		return permit;
	}

}
