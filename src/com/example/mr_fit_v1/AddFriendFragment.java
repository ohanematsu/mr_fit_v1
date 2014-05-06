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
import android.content.DialogInterface;
import android.os.AsyncTask;
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
import com.example.mr_fit_v1.ws.remote.FriendAddRequest;
import com.example.mr_fit_v1.ws.remote.FriendAddResponsePacket;
import com.example.mr_fit_v1.ws.remote.FriendDataPacket;


public class AddFriendFragment extends Fragment {
	private String serverHost = "ec2-54-186-249-133.us-west-2.compute.amazonaws.com";
	
	private static final String LOGTAG = "FriendFragment";
	
	private ListView listView;
	
	private ArrayList<Friend> friendList = null;
	
	
	
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
		
		Log.i(LOGTAG, "Create Fragment view complete...");
		return view;
	}
	
	public void updateUI(ArrayList<Friend> friendList) {		
		// Getting adapter
		if(friendList == null)
			return;
        FriendAdapter adapter = new FriendAdapter(getActivity(), friendList);
        listView.setAdapter(adapter);
        Log.i(LOGTAG, "Setup adapter complete...");
 
        // Click event for single list row
        listView.setOnItemClickListener(listItemClickListener);
		
		Log.i(LOGTAG, "Update UI complete...");
	}
	
	private void sendRequest(Friend selectedFriend) {
		
		int targetuser = selectedFriend.getUserId();
		AddFriendTask aft = new AddFriendTask(serverHost, 18641, targetuser);
		aft.execute();
		
		
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
	public class AddFriendTask extends AsyncTask<Void, Void, Void>{
		String destAddress;
		int dstport;
		int friendId;
		boolean isPermit = false;
		AddFriendTask(String addr, int port, int friendId){
			destAddress = addr;
			dstport = port;
			this.friendId = friendId;
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
			FriendAddRequest fsp = new FriendAddRequest(FriendDataPacket.ADD);
			fsp.setFriendId(friendId);
			fsp.setUserId(Session.getInstance().getUserId());
			
			pkt.setPayload(fsp);
			
			Log.v("host", "here");
			oos.writeObject(pkt);
			//Packet recv = (Packet) ois.readObject();
			//FriendAddResponsePacket rrp = (FriendAddResponsePacket)recv.getPayload();
			//this.isPermit = rrp.isPermit();
			sock.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		protected void onPostExecute(Void result){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
			alertDialogBuilder.setTitle("Send Friend Request");
			alertDialogBuilder.setMessage("Request is sent!" )
					          .setCancelable(true);
					   
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			//getActivity().finish();
			
		}
	}
	
	
	

	
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, final View view, int position,
				long id) {
			final Friend selectedFriend = (Friend)parent.getItemAtPosition(position);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
			alertDialogBuilder.setTitle("Send Friend Request");
			alertDialogBuilder.setMessage("Do you want to send friend request to this user? " + selectedFriend.getUserName())
					          .setCancelable(true)
					          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					        	  public void onClick(DialogInterface dialog,int id) {
					        		  sendRequest(selectedFriend);
					        	  }
					          });
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
	};

}
