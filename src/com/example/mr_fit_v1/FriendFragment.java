package com.example.mr_fit_v1;

import java.util.ArrayList;

import com.example.mr_fit_v1.entities.Friend;
import com.example.mr_fit_v1.session.Session;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FriendFragment extends Fragment {
	private static final String LOGTAG = "FriendFragment";
	
	private ArrayList<Friend> friendList;
	
	private Thread friendRetrievingTask = new Thread() {
		@Override
		public void run() {
			// Send message to server to retrieve friend list
			Log.i(LOGTAG, "Send request to server...");
			
			// Parse received data
			ArrayList<Friend> friendList = new ArrayList<Friend>();
			
			Log.i(LOGTAG, "Parse friend list complete...");
			
			// Update UI
			updateUI(friendList);
			
			// Update friend list
			Session.getInstance().setFriendList(friendList);
			
			super.run();
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		friendList = Session.getInstance().getFriendList();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating Fragment view...");
		View view = inflater.inflate(R.layout.fragment_statistic, container, false);
		
		// Set up UI based on friends list
		updateUI(friendList);
		
		// Start a thread to get updated friendlist, when done, it will update UI
		friendRetrievingTask.start();
		
		
		Log.i(LOGTAG, "Create Fragment view complete...");
		return view;
	}
	
	private void updateUI(ArrayList<Friend> friendList) {
		Log.i(LOGTAG, "Initiate UI complete...");
	}
}
