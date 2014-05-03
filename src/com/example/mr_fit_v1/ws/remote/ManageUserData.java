package com.example.mr_fit_v1.ws.remote;

interface ManageUserData {
	public boolean register(String account, String password, String name, int avatarId, String gender);
	public boolean login(String account, String password);
	public boolean logout(String account);
	public boolean updateEmail(String account);
	public boolean updatePassword(String oldPassword, String newPassword);
	public boolean updateName(String name);
	public boolean updateAvatarImage(int newAvatarId);
	public boolean updateStatus(int lastKnownStatus);
}
