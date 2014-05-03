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
import com.example.mr_fit_v1.ws.remote.RegisterPacket;
import com.example.mr_fit_v1.ws.remote.RegisterResponsePacket;

public class SignUpActivity extends Activity {
	private String serverHost = "ec2-54-186-249-133.us-west-2.compute.amazonaws.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
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
			View rootView = inflater.inflate(R.layout.fragment_sign_up,
					container, false);
			return rootView;
		}
	}
	
	public void SignUp(View view) throws UnknownHostException, IOException, ClassNotFoundException{
		EditText etEmail = (EditText)findViewById(R.id.etEmail);
		EditText etUserName = (EditText)findViewById(R.id.etUserName);
		EditText etPass = (EditText)findViewById(R.id.etPass);
		String email = etEmail.toString();
		String userName = etUserName.toString();
		String password = etPass.toString();
		Packet pkt = new Packet();
		pkt.setType(pkt.USER_DATA);
		RegisterPacket rp = new RegisterPacket(email, password, userName, 1, "default");
		rp.setType(rp.REGISTER);
		Socket sock = new Socket(serverHost, 18641);
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		InputStream is = sock.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		oos.writeObject(pkt);
		Packet recv = (Packet) ois.readObject();
		RegisterResponsePacket rrp = (RegisterResponsePacket)recv.getPayload();
		int userid =  rrp.getUserId();
		Session.initSession(10000, "zengjw1990@gmail.com", getApplicationContext());
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
		
	}

}
