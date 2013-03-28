package com.platzerworld.biergarten.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BiergartenFinderData {

	private static final String TAG = BiergartenFinderData.class.getSimpleName();

	// Table and column declaration

	public static final int VERSION = 1;
	public static final String DATABASE = "friendfinder.db";
	public static final String TABLE = "geocontact";

	public static final String C_ID = "_id";
	public static final String C_CREATED_AT = "created_at";

	public static final String C_USERNAME = "username";
	public static final String C_REALNAME = "realname";
	public static final String C_PHONE = "phone";

	public static final String C_NOTE = "note";
	public static final String C_LONGITUDE = "longitude";
	public static final String C_LATITUDE = "latitude";
	public static final String C_ALTITUDE = "altitude";
	public static final String C_TIMESTAMP = "timestamp";

	public static final String GET_ALL_ORDER_BY = C_CREATED_AT + " DESC";

	private static final String[] DB_TIMESTAMP_COLUMNS = { C_TIMESTAMP };

	// DB Helper implementation
	public class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG, "create database");
			db.execSQL("create table " + TABLE + " (" + C_ID
					+ " TEXT primary_key, " + C_CREATED_AT + " INTEGER, "
					+ C_USERNAME + " TEXT, " + C_REALNAME + " TEXT, " + C_PHONE
					+ " TEXT, " + C_NOTE + " TEXT, " + C_LONGITUDE + " FLOAT, "
					+ C_LATITUDE + " FLOAT, " + C_ALTITUDE + " FLOAT, "
					+ C_TIMESTAMP + " FLOAT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion
					+ "to version " + newVersion
					+ ", which will destroy all old data!");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE);
			onCreate(db);
		}
	}

	private DbHelper dbHelper;
	
	public BiergartenFinderData(Context context) {
		this.dbHelper = new DbHelper(context);
		Log.i(TAG, "data initialized");
	}

	public DbHelper getDbHelper() {
		return dbHelper; 
	} 
	
	public void close() {
		this.dbHelper.close();
	}

	public long insertOrUpdate(ContentValues values) {

		// check if geo contact already exists
		String lookupId = values.getAsString(C_ID);
		Log.d(TAG, "insertOrUpdate for " + lookupId);

		float currentTimestamp = values.getAsFloat(C_TIMESTAMP);
		float lastTimestamp = getLastPoiVisitationUpdateTimestamp(lookupId);

		// something changed
		if (currentTimestamp != lastTimestamp) {

			SQLiteDatabase db = this.dbHelper.getWritableDatabase();

			// there is already an entry, UPDATE needed
			try {
				if (lastTimestamp != -1f) {
					Log.d(TAG, "UPDATE for " + lookupId + "(old timestamp = "
							+ lastTimestamp + " new timestamp = "
							+ currentTimestamp + ")");
					String whereClause = C_ID + "=?";
					String[] whereArgs = { lookupId };

					// return the number of updated rows (should be 1)
					return db.updateWithOnConflict(TABLE, values, whereClause,
							whereArgs, SQLiteDatabase.CONFLICT_REPLACE);
				} else {
					Log.d(TAG, "INSERT for " + lookupId + "(timestamp = "
							+ currentTimestamp + ")");

					// set created to "now"
					values.put(C_CREATED_AT, System.currentTimeMillis());

					// return number of inserted rows (should be one if there is
					// no error, 0 else)
					return db.insertWithOnConflict(TABLE, null, values,
							SQLiteDatabase.CONFLICT_IGNORE) != -1 ? 1 : 0;
				}
			} finally {
				db.close();
			}
		}
		Log.d(TAG, "neither INSERT nor UPDATE ");
		return 0;
	}

	public float getLastPoiVisitationUpdateTimestamp(String id) {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		try {
			String whereClause = C_ID + "=?";
			String[] whereArgs = { id };
			Cursor cursor = db.query(TABLE, DB_TIMESTAMP_COLUMNS, whereClause,
					whereArgs, null, null, null);
			try {
				return cursor.moveToNext() ? cursor.getFloat(0) : -1;
			} finally {
				cursor.close();
			}
		} finally {
			db.close();
		}
	}

}
