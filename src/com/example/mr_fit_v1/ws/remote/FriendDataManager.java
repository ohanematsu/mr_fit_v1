package com.example.mr_fit_v1.ws.remote;

import java.util.ArrayList;

import com.example.mr_fit_v1.entities.Friend;
import com.example.mr_fit_v1.util.Packet;

class FriendDataManager extends BaseRemoteDatabaseManager implements ManageFriendData {

	public FriendDataManager() {
		super();
	}

	@Override
	public boolean requestFriendList(int userID) {
		FriendListRequestPacket packet = new FriendListRequestPacket(userID);
		return sendFriendDataPacket(packet);
	}

	@Override
	public boolean searchFriend(String name) {
		FriendSearchRequestPacket packet = new FriendSearchRequestPacket(name);
		return sendFriendDataPacket(packet);
	}

	@Override
	public ArrayList<Friend> receiveFriendList() {
		FriendDataPacket packet = receiveFriendDataPacket();
		if (packet.type == FriendDataPacket.REQUEST_FRIEND_LIST_REPOSNE) {
			FriendListResponsePacket newPacket = (FriendListResponsePacket)packet;
			return newPacket.getFriendList();
		}
		return null;
	}

	@Override
	public ArrayList<Friend> receiveFriendSearchResult() {
		FriendDataPacket packet = receiveFriendDataPacket();
		if (packet.type == FriendDataPacket.SEARCH_RESPONSE) {
			FriendSearchResponsePacket newPacket = (FriendSearchResponsePacket)packet;
			return newPacket.getList();
		}
		return null;
	}

	private boolean sendFriendDataPacket(FriendDataPacket payload) {
		Packet packet = new Packet(session.getUserId(), Packet.FRIEND_DATA, payload);
		return sendPacket(packet);
	}
	
	private FriendDataPacket receiveFriendDataPacket() {
		Packet packet = receivePacket();
		if (packet.getType() == Packet.FRIEND_DATA) {
			return (FriendDataPacket)packet.getPayload();
		} else {
			return null;
		}
	}
}
