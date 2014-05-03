package com.example.mr_fit_v1.ws.remote;

public class FriendListRequestPacket extends FriendDataPacket {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int userID;
	
	public FriendListRequestPacket(int userID) {
		super(FriendDataPacket.REQUEST_FRIEND_LIST);
		this.userID = userID;
	}
	
	public int getUserId() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
