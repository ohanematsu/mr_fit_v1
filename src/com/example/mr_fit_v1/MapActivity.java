package com.example.mr_fit_v1;

import java.util.ArrayList;

import com.example.mr_fit_v1.ws.local.PathFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MapActivity extends Activity {

	private static final String LOGTAG = "MapActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		if (savedInstanceState == null) {
			//mapFragment = MapFragment.newInstance();
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PathFragment()).commit();
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
			View rootView = inflater.inflate(R.layout.fragment_map, container,
					false);
			return rootView;
		}
	}
	
	

}
