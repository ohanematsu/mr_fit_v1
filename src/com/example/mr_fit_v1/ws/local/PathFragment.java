package com.example.mr_fit_v1.ws.local;

import java.util.*;

import com.example.mr_fit_v1.R;
import com.example.mr_fit_v1.ReportFragment;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.*;

public class PathFragment extends MapFragment {
	private static final String LOGTAG = "MapFragment";
	
	private ArrayList<Location> path;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGTAG, "Initilizing MapFragment...");
		
		Intent receivedIntent = getActivity().getIntent();
		
		path = (ArrayList<Location>)receivedIntent.
			getSerializableExtra(ReportFragment.REPORT_FRAGMENT_PATH);
		if (path == null) {
			Log.e(LOGTAG, "Parse path fail...");
		}
		Log.i(LOGTAG, "On Create complete...");
	}
	
	@Override
	public void onStart() {
		Log.i(LOGTAG, "Prepare map...");
		// Initialize map
		GoogleMap map = getMap();
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
		Display display = getActivity().getWindowManager().getDefaultDisplay();
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
	}
}
