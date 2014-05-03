package com.example.mr_fit_v1.ws.remote;

import com.example.mr_fit_v1.session.Session;
import com.example.mr_fit_v1.util.NetworkManager;
import com.example.mr_fit_v1.util.Packet;

abstract class BaseRemoteDatabaseManager {
	protected Session session;
	
	public BaseRemoteDatabaseManager() {
		session = Session.getInstance();
	}
	
	protected boolean sendPacket(Packet packet) {
		return NetworkManager.sendPacket(packet);
	}
	
	protected Packet receivePacket() {
		return NetworkManager.receivePacket();
	}
}
