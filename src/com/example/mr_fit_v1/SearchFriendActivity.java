package com.example.mr_fit_v1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mr_fit_v1.FriendsActivity.MySearchFriendTask;
import com.example.mr_fit_v1.entities.Friend;
import com.example.mr_fit_v1.util.Packet;
import com.example.mr_fit_v1.ws.remote.FriendDataPacket;
import com.example.mr_fit_v1.ws.remote.FriendSearchRequestPacket;
import com.example.mr_fit_v1.ws.remote.FriendSearchResponsePacket;

public class SearchFriendActivity extends Activity {
	private String serverHost = "ec2-54-186-249-133.us-west-2.compute.amazonaws.com";
	private AddFriendFragment frag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_friend);

		
		Intent myIntent = getIntent(); // gets the previously created intent
		@SuppressWarnings("unchecked")
		ArrayList<Friend> friends = (ArrayList<Friend>) myIntent.getSerializableExtra("friends");
		AddFriendFragment af = new AddFriendFragment();
		this.frag = af;
		//af.updateUI(friends);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.container, af);
		ft.commit();
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent intent = null;
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		switch(id){
		case R.id.item1:
			intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.item2:
			intent = new Intent(this, TrackerActivity.class);
			startActivity(intent);
			break;
		case R.id.item3:
			intent = new Intent(this, TabsFragmentActivity.class);
			startActivity(intent);
			break;
		case R.id.item4:
			intent = new Intent(this, FriendsActivity.class);
			startActivity(intent);
			break;
		case R.id.item5:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}



	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_search_friend,
					container, false);
			return rootView;
		}
	}

	public void sendRequest(View view){
		EditText et = (EditText)findViewById(R.id.etAddFriend);
		String search = et.getText().toString();
		MySearchFriendTask at = new MySearchFriendTask(serverHost, 18641, search);
		at.execute();
	}
	
	public class MySearchFriendTask extends AsyncTask<Void, Void, Void>{
		String destAddress;
		int dstport;
		ArrayList<Friend> friends; 
		String search;
		MySearchFriendTask(String addr, int port, String str){
			destAddress = addr;
			dstport = port;
			this.search = str;
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
			FriendSearchRequestPacket fsp = new FriendSearchRequestPacket(search);
			fsp.setType(FriendDataPacket.SEARCH);
			pkt.setPayload(fsp);
			
			Log.v("host", "here");
			oos.writeObject(pkt);
			Packet recv = (Packet) ois.readObject();
			FriendSearchResponsePacket rrp = (FriendSearchResponsePacket)recv.getPayload();
			ArrayList<Friend> friendlist = rrp.getList();
			this.friends = friendlist;
			Log.v("friends", String.valueOf(friends.size()));
			}catch (Exception e) {
				
			}
			return null;
			
		}
		protected void onPostExecute(Void result){
			frag.updateUI(friends);
		}
	}

}
