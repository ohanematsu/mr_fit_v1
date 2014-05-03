package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

import com.example.mr_fit_v1.R.layout;
import com.example.mr_fit_v1.entities.Friend;

public class FriendListResponsePacket extends FriendDataPacket {
	protected ArrayList<Friend> friendList;
	
	public FriendListResponsePacket(ArrayList<Friend> friendList) {
		super(FriendDataPacket.REQUEST_FRIEND_LIST_REPOSNE);
		this.friendList = friendList;
	}
	
	public ArrayList<Friend> getFriendList() {
		return friendList;
	}
	
	public void setFriendList(ArrayList<Friend> friendList) {
		this.friendList = friendList;
	}
}
