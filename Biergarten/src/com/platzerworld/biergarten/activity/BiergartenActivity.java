package com.platzerworld.biergarten.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.platzerworld.biergarten.R;
import com.platzerworld.biergarten.R.drawable;
import com.platzerworld.biergarten.R.id;
import com.platzerworld.biergarten.R.layout;
import com.platzerworld.biergarten.R.menu;

public class BiergartenActivity extends BaseActivity {
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biergarten);
		
		map = ((MapFragment) super.getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
				.title("Hamburg"));
		Marker kiel = map.addMarker(new MarkerOptions()
				.position(KIEL)
				.title("Kiel")
				.snippet("Kiel is cool")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));

		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.biergarten, menu);
		return true;
	}

}
