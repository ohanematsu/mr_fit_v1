package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

import com.example.mr_fit_v1.entities.Friend;

public class ServerDataManager implements ManageFriendData, ManageUserData {

	private static ServerDataManager instance = null;
	
	private UserDataManager userDataManager;
	private FriendDataManager friendDataManager;
	
	private ServerDataManager() {
		userDataManager = new UserDataManager();
		friendDataManager = new FriendDataManager();
	}
	
	public ServerDataManager getInstance() {
		if (instance == null) {
			instance = new ServerDataManager();
		}
		return instance;
	}
	
	@Override
	public boolean register(String account, String password, String name, int avatarId, String gender) {
		return userDataManager.register(account, password, name, avatarId, gender);
	}

	@Override
	public boolean login(String account, String password) {
		return userDataManager.login(account, password);
	}

	@Override
	public boolean logout(String account) {
		return userDataManager.logout(account);
	}

	@Override
	public boolean updateEmail(String account) {
		return userDataManager.updateEmail(account);
	}

	@Override
	public boolean updatePassword(String oldPassword, String newPassword) {
		return userDataManager.updatePassword(oldPassword, newPassword);
	}

	@Override
	public boolean updateName(String name) {
		return userDataManager.updateName(name);
	}

	@Override
	public boolean updateAvatarImage(int newAvatarId) {
		return userDataManager.updateAvatarImage(newAvatarId);
	}

	@Override
	public boolean updateStatus(int lastKnownStatus) {
		return userDataManager.updateStatus(lastKnownStatus);
	}

	@Override
	public boolean searchFriend(String name) {
		return friendDataManager.searchFriend(name);
	}

	@Override
	public boolean requestFriendList(int userID) {
		return friendDataManager.requestFriendList(userID);
	}

	@Override
	public ArrayList<Friend> receiveFriendList() {
		return friendDataManager.receiveFriendList();
	}

	@Override
	public ArrayList<Friend> receiveFriendSearchResult() {
		return friendDataManager.receiveFriendSearchResult();
	}
}
