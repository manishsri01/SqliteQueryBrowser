package com.manish.sqlitequerybrowser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author manish.s
 *
 */

public class SQLiteHelper extends SQLiteOpenHelper {

	private static String DB_PATH="/data/data/com.manish.sqlitequerybrowser/databases/";
	private static String DB_NAME = "mydatabase.sqlite";
	private static int VERSION =1;
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public SQLiteHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		myContext = context;
		try {
			createDatabase(); 
	    } 
	    catch (IOException ioe) { 
	       	throw new Error("Unable to create database"); 
	    }
	}

	public void createDatabase() throws IOException {
		boolean dbExist = checkDataBase();

		if (dbExist) {
			System.out.println("DB EXIST");
		}

		else {
			this.getReadableDatabase();
			this.close();
			copyDataBase();
		}
	}

	private void copyDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			System.out.println("Database does't exist yet.");
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;

	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
