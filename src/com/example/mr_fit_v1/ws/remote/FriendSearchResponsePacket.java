package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

import com.example.mr_fit_v1.entities.Friend;

public class FriendSearchResponsePacket extends FriendDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<Friend> list;
	
	public FriendSearchResponsePacket(ArrayList<Friend> list) {
		super(FriendDataPacket.SEARCH_RESPONSE);
		this.list = list;
	}
	
	public ArrayList<Friend> getList() {
		return list;
	}
	
	public void setList(ArrayList<Friend> list) {
		this.list = list;
	}
}
