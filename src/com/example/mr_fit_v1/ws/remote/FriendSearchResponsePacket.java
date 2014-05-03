package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

public class FriendSearchResponsePacket extends FriendDataPacket {
	protected ArrayList<String> list;
	
	public FriendSearchResponsePacket(ArrayList<String> list) {
		super(FriendDataPacket.SEARCH_RESPONSE);
		this.list = list;
	}
	
	public ArrayList<String> getList() {
		return list;
	}
	
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
}
