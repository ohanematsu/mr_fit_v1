package com.example.mr_fit_v1.ws.remote;

public class FriendSearchRequestPacket extends FriendDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	
	public FriendSearchRequestPacket(String name) {
		super(FriendDataPacket.SEARCH);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
