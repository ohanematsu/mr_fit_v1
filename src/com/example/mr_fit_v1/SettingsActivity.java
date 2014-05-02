package com.example.mr_fit_v1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends Activity {
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	   
	  SettingsFragment prefFragment = new SettingsFragment();
	  FragmentManager fragmentManager = getFragmentManager();
	  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	  fragmentTransaction.replace(android.R.id.content, prefFragment);
	  fragmentTransaction.commit();
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


}
