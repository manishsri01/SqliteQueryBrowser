package com.manish.sqlitequerybrowser;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * 
 * @author manish.s
 *
 */

public class SQLiteConnector {
	
	private SQLiteDatabase database;
	private SQLiteHelper sqlHelper;
	private Cursor cursor;
	
	private static final String TABLE_RECORD = "student";
	
	public SQLiteConnector(Context context) {
		sqlHelper = new SQLiteHelper(context);
	}
	
	// Getting All records
			public List<String> getAllRecord() {
				List<String> studentList = new ArrayList<String>();
				String selectQuery = "SELECT  * FROM " + TABLE_RECORD;

				database = sqlHelper.getReadableDatabase();
				cursor = database.rawQuery(selectQuery, null);
				if (cursor.moveToFirst()) {
					do {
						studentList.add(cursor.getString(1));
						
					} while (cursor.moveToNext());
				}
				database.close();
				return studentList;
			}
	
}
