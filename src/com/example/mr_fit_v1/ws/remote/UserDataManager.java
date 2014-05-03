package com.example.mr_fit_v1.ws.remote;

import com.example.mr_fit_v1.util.Packet;

class UserDataManager extends BaseRemoteDatabaseManager implements ManageUserData {
	
	public UserDataManager() {
		super();
	}
	
	@Override
	public boolean register(String account, String password, String name, int avatarId, String gender) {
		RegisterPacket registerPacket = new RegisterPacket(account, password, name, avatarId, gender);
		return sendUserDataPacket(registerPacket);
	}

	@Override
	public boolean login(String account, String password) {
		UserLoginPacket loginPacket = new UserLoginPacket(account, password);
		return sendUserDataPacket(loginPacket);
	}

	@Override
	public boolean logout(String account) {
		UserLogoutPacket logoutPacket = new UserLogoutPacket(account);
		return sendUserDataPacket(logoutPacket);
	}

	@Override
	public boolean updateEmail(String account) {
		UserUpdatePacket packet = new UserUpdatePacket(UserDataPacket.UPDATE_EMAIL, account);
		return sendUserDataPacket(packet);
	}

	@Override
	public boolean updatePassword(String oldPassword, String newPassword) {
		UserUpdatePacket packet = new UserUpdatePacket(UserDataPacket.UPDATE_PASSWORD, newPassword);
		return sendUserDataPacket(packet);
	}

	@Override
	public boolean updateName(String name) {
		UserUpdatePacket packet = new UserUpdatePacket(UserDataPacket.UPDATE_NAME, name);
		return sendUserDataPacket(packet);
	}

	@Override
	public boolean updateAvatarImage(int newAvatarId) {
		UserUpdatePacket packet = new UserUpdatePacket(UserDataPacket.UPDATE_AVATAR_IMAGE, 
			Integer.valueOf(newAvatarId));
		return sendUserDataPacket(packet);
	}

	@Override
	public boolean updateStatus(int lastKnownStatus) {
		UserUpdatePacket packet = new UserUpdatePacket(UserDataPacket.UPDATE_STATUS, 
				Integer.valueOf(lastKnownStatus));
		return sendUserDataPacket(packet);
	}
	
	private boolean sendUserDataPacket(UserDataPacket payload) {
		Packet packet = new Packet(session.getUserId(), Packet.USER_DATA, payload);
		return sendPacket(packet);
	}
	
}
