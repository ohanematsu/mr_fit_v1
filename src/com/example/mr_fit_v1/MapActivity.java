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
	
	private ArrayList<Location> path;
	private MapFragment mapFragment;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		if (savedInstanceState == null) {
			//mapFragment = MapFragment.newInstance();
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PathFragment()).commit();
		}
		
		/*
		Intent receivedIntent = getIntent();
		
		path = (ArrayList<Location>)receivedIntent.
			getSerializableExtra(ReportFragment.REPORT_FRAGMENT_PATH);
		if (path == null) {
			Log.e(LOGTAG, "Parse path fail...");
		}
		Log.i(LOGTAG, "On Create complete...");*/
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

	//@Override
	/*protected void onStart() {
		Log.i(LOGTAG, "Prepare map...");
		// Initialize map
		GoogleMap map = mapFragment.getMap();
		map.setMyLocationEnabled(false);
		Log.i(LOGTAG, "Initialize map complete...");
		
		// Set up an overlay on the map for the path
		// Use a polyline with all of the points
		PolylineOptions line = new PolylineOptions();
		// Also create a LatLngBounds so you can zoom to fit
		LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
		
		// Iterate over the locations
		for (Location loc: path) {
			LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
			line.add(latLng);
			latLngBuilder.include(latLng);
		}
		
		// Add the polyline to the map
		map.addPolyline(line);
		// Make the map zoom to show the track, with some padding
		// Use the size of the current display in pixels as a bounding box
		Display display = getWindowManager().getDefaultDisplay();
		//Point point = null;
		//display.getSize(point);
		// Construct a movement instruction for the map camera
		LatLngBounds latLngBounds = latLngBuilder.build();
		@SuppressWarnings("deprecation")
		CameraUpdate movement = CameraUpdateFactory.newLatLngBounds(latLngBounds, 
			display.getWidth(), display.getHeight(), 15);
		map.moveCamera(movement);
		Log.i(LOGTAG, "Draw path complete...");
		
		// TODO: Setup UI
		Log.i(LOGTAG, "Set up UI complete...");
		
		super.onStart();
	}*/
	

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
