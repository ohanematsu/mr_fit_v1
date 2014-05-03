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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mr_fit_v1.session.Session;
import com.example.mr_fit_v1.util.Packet;
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

	@Override
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void SignInRequest(View view) throws UnknownHostException, IOException, ClassNotFoundException {
		
		EditText text1 = (EditText) findViewById(R.id.editText1);
		String userId = text1.getText().toString();
		EditText text2 = (EditText) findViewById(R.id.editText2);
		String password = text2.getText().toString();
		Packet pkt = new Packet();
		pkt.setType(pkt.USER_DATA);
		UserLoginPacket ulp = new UserLoginPacket(userId, password);
		ulp.setType(ulp.LOGIN);
		pkt.setPayload(ulp);
		Socket sock = new Socket(serverHost, 18641);
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		InputStream is = sock.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		oos.writeObject(pkt);
		Packet recv = (Packet) ois.readObject();
		UserLoginResponsePacket ulrp = (UserLoginResponsePacket)recv.getPayload();
		if(ulrp.getPermit() == true){
			Intent intent = new Intent(this, SecondActivity.class);
			Session.initSession(10000, "zengjw1990@gmail.com", getApplicationContext());
			startActivity(intent);
		}
	}
	public void SignUpRequest(View view) {
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
		return;
	}
}
