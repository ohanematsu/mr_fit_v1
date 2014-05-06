package com.example.mr_fit_v1;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mr_fit_v1.session.Session;
import com.example.mr_fit_v1.util.Packet;
import com.example.mr_fit_v1.ws.remote.UserDataPacket;
import com.example.mr_fit_v1.ws.remote.UserLoginPacket;
import com.example.mr_fit_v1.ws.remote.UserLoginResponsePacket;

public class MainActivity extends Activity {
	
	private String serverHost = "ec2-54-186-249-133.us-west-2.compute.amazonaws.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void SignInRequest(View view) throws UnknownHostException, IOException, ClassNotFoundException {
		
		MySignUpTask at = new MySignUpTask(serverHost, 18641);
		at.execute();
		
	}
	public void SignUpRequest(View view) {
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
		return;
	}
	
	public class MySignUpTask extends AsyncTask<Void, Void, Void>{
		String destAddress;
		int dstport;
		String response;
		boolean perm = false;
		int id = 0;
		String email = null;
		MySignUpTask(String addr, int port){
			destAddress = addr;
			dstport = port;
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
			EditText text1 = (EditText) findViewById(R.id.editText1);
			String userId = text1.getText().toString();
			email = userId;
			EditText text2 = (EditText) findViewById(R.id.editText2);
			String password = text2.getText().toString();
			Packet pkt = new Packet();
			pkt.setType(Packet.USER_DATA);
			UserLoginPacket ulp = new UserLoginPacket(userId, password);
			ulp.setType(UserDataPacket.LOGIN);
			pkt.setPayload(ulp);
			
			Log.v("host", "here");
			oos.writeObject(pkt);
			Packet recv = (Packet) ois.readObject();
			UserLoginResponsePacket rrp = (UserLoginResponsePacket)recv.getPayload();
			boolean permit =  rrp.getPermit();
			
			perm = permit;
			id = rrp.getId();
			sock.close();
			}catch (Exception e) {
				
			}
			return null;
			
		}
		protected void onPostExecute(Void result){
			
			if(perm == true){
				Session.initSession(id, email, getApplicationContext());
				
				Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
				startActivity(intent);
			}
			else{
				TextView tv = (TextView) findViewById(R.id.error);
				tv.setText("password and username don't match");
			}
			
			
		}
	}
}
