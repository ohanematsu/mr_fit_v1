package com.example.mr_fit_v1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FriendsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FriendsFragment()).commit();
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
			View rootView = inflater.inflate(
					R.layout.fragment_friends, container, false);
			return rootView;
		}
	}
	
	public void sendRequest(View view){
		Log.v("button", "from friend to search");
		EditText et = (EditText)findViewById(R.id.etAddFriend);
		String search = et.getText().toString();
		Intent intent = new Intent(this, SearchFriendActivity.class);	
		startActivity(intent);
		Log.v("after activity started", "here");
		//MySearchFriendTask at = new MySearchFriendTask(serverHost, 18641, search);
		//at.execute();
	}
	
		
	

}
