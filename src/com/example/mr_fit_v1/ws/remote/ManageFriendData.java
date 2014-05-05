package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

import com.example.mr_fit_v1.entities.Friend;

interface ManageFriendData {
	public boolean searchFriend(String name);
	public boolean requestFriendList(int userID);
	public ArrayList<Friend> receiveFriendList();
	public ArrayList<Friend> receiveFriendSearchResult();
}
