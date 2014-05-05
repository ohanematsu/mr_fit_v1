package com.example.mr_fit_v1.ws.remote;

public class FriendAddResponsePacket extends FriendDataPacket {

	private boolean permit;
	public FriendAddResponsePacket(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
