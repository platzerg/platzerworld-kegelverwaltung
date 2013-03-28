package com.platzerworld.biergarten;

import java.util.Collection;

import android.app.Application;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

import com.platzerworld.biergarten.model.BiergartenFinderData;
import com.platzerworld.biergartenfinderlib.Biergarten;
import com.platzerworld.biergartenfinderlib.BiergartenFinder;
import com.platzerworld.biergartenfinderlib.BiergartenFinderException;
import com.platzerworld.biergartenfinderlib.PointOfInterestVisitation;

public class BiergartenApplication extends Application implements
		OnSharedPreferenceChangeListener {

	private static final String TAG = BiergartenApplication.class
			.getSimpleName();

	private static final String PREF_USERNAME_KEY = "username";
	private static final String PREF_REALNAME_KEY = "realname";
	private static final String PREF_PHONE_KEY = "phone";

	private boolean serviceRunning;

	public boolean isServiceRunning() {
		return serviceRunning;
	}

	public void setServiceRunning(boolean serviceRunning) {
		this.serviceRunning = serviceRunning;
	}

	BiergartenFinderData biergartenFinderData;

	public BiergartenFinderData getBiergartenFinderData() {
		return biergartenFinderData;
	}

	SharedPreferences preferences;
	BiergartenFinder biergartenFinder;

	@Override
	public void onCreate() {
		super.onCreate();
		
		this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
		this.preferences.registerOnSharedPreferenceChangeListener(this);
		this.biergartenFinderData = new BiergartenFinderData(this); 
		
		Log.i(TAG, "onCreate");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminate");
	}
	
	public SharedPreferences getPreferences() {
		return this.preferences; 
	}

	public synchronized BiergartenFinder getBiergartenFinder() {
		if (biergartenFinder == null) {
			// get reference to friend finder service
			String username = preferences.getString(PREF_USERNAME_KEY, "");
			String phone = preferences.getString(PREF_PHONE_KEY, "");
			String realname = preferences.getString(PREF_REALNAME_KEY, "");
			Biergarten contact = new Biergarten(username, realname, phone);
			biergartenFinder = new BiergartenFinder(contact);
		}
		return biergartenFinder;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// force reload of preferences
		biergartenFinder = null;
		Log.i(TAG, "onSharedPreferenceChanged");
	}

	public synchronized long updateFriendFinderData() {
		
		Log.d(TAG, "fetch current data and store in local DB");

		biergartenFinder = this.getBiergartenFinder();
		if (biergartenFinder == null) {
			Log.d(TAG, "no connection available"); 
			return 0; 
		}
		
		long count = 0;
		
		try {
			Collection<PointOfInterestVisitation> poiVisitations =  biergartenFinder.lookupFriendsCurrentPointOfInterestVisitation();
		
		  ContentValues values = new ContentValues(); 
		  for (PointOfInterestVisitation poiVisitation: poiVisitations) {
			
			values.put(BiergartenFinderData.C_ID, poiVisitation.getContact().getUsername());
			
			values.put(BiergartenFinderData.C_USERNAME, poiVisitation.getContact().getUsername());
			values.put(BiergartenFinderData.C_REALNAME, poiVisitation.getContact().getName());
			values.put(BiergartenFinderData.C_PHONE,poiVisitation.getContact().getPhone());
			values.put(BiergartenFinderData.C_NOTE, poiVisitation.getNote());
			values.put(BiergartenFinderData.C_LONGITUDE, poiVisitation.getPointOfInterest().getLongitude());
			values.put(BiergartenFinderData.C_LATITUDE, poiVisitation.getPointOfInterest().getLatitude());
			values.put(BiergartenFinderData.C_ALTITUDE, poiVisitation.getPointOfInterest().getAltitude());
			values.put(BiergartenFinderData.C_TIMESTAMP, poiVisitation.getTimestamp());
            
			count = count + this.getBiergartenFinderData().insertOrUpdate(values);
		  }	
		} catch (BiergartenFinderException ex) {
		  Log.d(TAG, "no connection available"); 
		  return 0; 
		}	
		return count;    
	  }
}
