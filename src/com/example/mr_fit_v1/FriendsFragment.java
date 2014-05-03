package com.example.mr_fit_v1;

import java.util.*;

import com.example.mr_fit_v1.entities.Friend;
import com.example.mr_fit_v1.session.Session;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class FriendsFragment extends Fragment {
	private static final String LOGTAG = "FriendFragment";
	
	private ListView listView;
	
	private ArrayList<Friend> friendList;
	
	private Thread friendRetrievingTask = new Thread() {
		@Override
		public void run() {
			// TODO:Send message to server to retrieve friend list
			Log.i(LOGTAG, "Send request to server...");
			
			// Parse received data
			ArrayList<Friend> friendList = new ArrayList<Friend>();
			Log.i(LOGTAG, "Parse friend list complete...");
			
			// Update UI
			updateUI(friendList);
			Log.i(LOGTAG, "Set up UI complete...");
			
			// Update friend list
			updateFriendList(friendList);
			
			super.run();
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LOGTAG, "Creating Fragment view...");
		View view = inflater.inflate(R.layout.fragment_friends, container, false);
		listView = (ListView)view.findViewById(R.id.friendList);
		
		// Parse received data
		friendList = Session.getInstance().getFriendList();
		if (friendList == null) {
			friendList = new ArrayList<Friend>();
		}
		// Hardcode some data for test
		if (friendList.isEmpty()) {
			friendList.add(new Friend(1, "jiawen", 1, Friend.FINISH_EXERCISE_FOR_A_WHILE));
			friendList.add(new Friend(2, "xiaolin", 2, Friend.FINISH_EXERCISE_FOR_A_WHILE));
		}
		Log.i(LOGTAG, "Parse friend list complete...");
					
		// Update UI
		updateUI(friendList);
		Log.i(LOGTAG, "Set up UI complete...");
		
		// Start a thread to get updated friendlist, when done, it will update UI
		//friendRetrievingTask.start();
		
		
		Log.i(LOGTAG, "Create Fragment view complete...");
		return view;
	}
	
	private void updateUI(ArrayList<Friend> friendList) {		
		// Getting adapter
        FriendAdapter adapter = new FriendAdapter(getActivity(), friendList);
        listView.setAdapter(adapter);
        Log.i(LOGTAG, "Setup adapter complete...");
 
        // Click event for single list row
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO: Pop-up dialog to send reminder
				
			}
		});
		
		Log.i(LOGTAG, "Update UI complete...");
	}
	
	private void updateFriendList(ArrayList<Friend> list) {
		Session.getInstance().setFriendList(friendList);
		Log.i(LOGTAG, "Update friendList complete...");
	}
	
	private class FriendAdapter extends BaseAdapter {
		 
	    private Activity activity;
	    private ArrayList<Friend> data;
	    private LayoutInflater inflater;
	 
	    public FriendAdapter(Activity activity, ArrayList<Friend> data) {
	        this.activity = activity;
	        this.data = data;
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	 
	    public int getCount() {
	        return data.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return data.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view = convertView;
	        if (convertView == null) {
	            view = inflater.inflate(R.layout.list_row, null);
	        }
	 
	        Friend friend = data.get(position);
	        
	        // Set friend name on UI
	        TextView name = (TextView)view.findViewById(R.id.textView1);
	        name.setText(friend.getUserName());
	        
	        // Set friend avatar image on UI
	        ImageView image = (ImageView)view.findViewById(R.id.imageView1);
	        if (friend.getStatus() == Friend.JUST_FINISH_EXERCISE) {
	        	image.setImageResource(R.drawable.a1);
	        } else if (friend.getStatus() == Friend.FINISH_EXERCISE_FOR_A_WHILE) {
	        	image.setImageResource(R.drawable.a1);
	        } else {
	        	image.setImageResource(R.drawable.a1);
	        }
	 
	        return view;
	    }
	}
}