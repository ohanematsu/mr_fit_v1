package com.example.mr_fit_v1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr_fit_v1.entities.Friend;
import com.example.mr_fit_v1.session.Session;

import com.example.mr_fit_v1.util.Packet;
import com.example.mr_fit_v1.ws.remote.FriendDataPacket;
import com.example.mr_fit_v1.ws.remote.FriendListRequestPacket;
import com.example.mr_fit_v1.ws.remote.FriendListResponsePacket;
import com.example.mr_fit_v1.ws.local.SMSServiceManager;


public class FriendsFragment extends Fragment {
	private String serverHost = "ec2-54-186-249-133.us-west-2.compute.amazonaws.com";
	
	private static final String LOGTAG = "FriendFragment";
	
	private ListView listView;
	
	private ArrayList<Friend> friendList;
	
	
	
	
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
		
		// Hardcode some data for test

		Log.i(LOGTAG, "Parse friend list complete...");
					
		// Update UI
		
		Log.i(LOGTAG, "Set up UI complete...");
		
		// Start a thread to get updated friendlist, when done, it will update UI
		//friendRetrievingTask.start();
		int id = Session.getInstance().getUserId();
		MyListFriendTask mlft = new MyListFriendTask(serverHost, 18641, id);
		mlft.execute();

		Log.i(LOGTAG, "Create Fragment view complete...");
		return view;
	}
	
	private void updateUI(ArrayList<Friend> friendList) {		
		// Getting adapter
		if(friendList == null)
			return;
		Log.v("friend list in updateUI", String.valueOf(friendList.size()));
        FriendAdapter adapter = new FriendAdapter(getActivity(), friendList);
        listView.setAdapter(adapter);
        Log.i(LOGTAG, "Setup adapter complete...");
 
        // Click event for single list row
        listView.setOnItemClickListener(listItemClickListener);
		
		Log.i(LOGTAG, "Update UI complete...");
	}
	
	private void sendReminder(Friend selectedFriend) {
		String message = "A reminder from your friend " + selectedFriend.getUserName() + ":\n" + 
			"Hi My dear friend, I know you are very busy in these days. However, for your fitness, please don't forget to do exercise lol";
		SMSServiceManager.sendSmsMessage(selectedFriend.getPhoneNum(), message);
	}
	
	private void updateFriendList(ArrayList<Friend> list) {
		Session.getInstance().setFriendList(friendList);
		Log.i(LOGTAG, "Update friendList complete...");
	}
	
	private class FriendAdapter extends BaseAdapter {
		 
	    @SuppressWarnings("unused")
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
	        	image.setImageResource(R.drawable.avatar1);
	        } else if (friend.getStatus() == Friend.FINISH_EXERCISE_FOR_A_WHILE) {
	        	image.setImageResource(R.drawable.avatar1);
	        } else {
	        	image.setImageResource(R.drawable.avatar2);
	        }
	 
	        return view;
	    }
	}
	public class MyListFriendTask extends AsyncTask<Void, Void, Void>{
		String destAddress;
		int dstport;
		int userId;
		ArrayList<Friend> friends; 
		MyListFriendTask(String addr, int port, int userId){
			destAddress = addr;
			dstport = port;
			this.userId = userId;
		}
		
		@SuppressWarnings("resource")
		protected Void doInBackground(Void... params){
			Socket sock;
			try {
				sock = new Socket(destAddress, dstport);
			
			OutputStream os = sock.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Packet pkt = new Packet();
			pkt.setType(Packet.FRIEND_DATA);
			FriendListRequestPacket ulp = new FriendListRequestPacket(userId);
			ulp.setType(FriendDataPacket.REQUEST_FRIEND_LIST);
			pkt.setPayload(ulp);
			
			Log.v("host", "here");
			oos.writeObject(pkt);
			Packet recv = (Packet) ois.readObject();
			FriendListResponsePacket rrp = (FriendListResponsePacket)recv.getPayload();
			ArrayList<Friend> friendlist = rrp.getFriendList();
			Log.v("size", String.valueOf(friendlist.size()));
			this.friends = friendlist;
			}catch (Exception e) {
				
			}
			return null;
			
		}
		protected void onPostExecute(Void result){
			Log.v("after size", String.valueOf(this.friends.size()));
			updateUI(this.friends);
			
		}
	}

	

	
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, final View view, int position,
				long id) {
			final Friend selectedFriend = (Friend)parent.getItemAtPosition(position);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
			alertDialogBuilder.setTitle("Send reminder");
			alertDialogBuilder.setMessage("Do you want to send reminder to your friend " + selectedFriend.getUserName())
					          .setCancelable(true)
					          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					        	  public void onClick(DialogInterface dialog,int id) {
					        		  sendReminder(selectedFriend);
					        	  }
					          });
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
	};

}
