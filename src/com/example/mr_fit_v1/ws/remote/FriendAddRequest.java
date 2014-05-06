package com.example.mr_fit_v1.ws.remote;

public class FriendAddRequest extends FriendDataPacket {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int friendId;

	public FriendAddRequest(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
