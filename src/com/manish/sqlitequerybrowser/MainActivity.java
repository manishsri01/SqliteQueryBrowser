package com.manish.sqlitequerybrowser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * 
 * @author manish.s
 *
 */

public class MainActivity extends Activity {
	ListView listStudents;
	SQLiteConnector sqlConnect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listStudents = (ListView) findViewById(R.id.listView1);
		sqlConnect = new SQLiteConnector(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, sqlConnect.getAllRecord());
		listStudents.setAdapter(adapter);

	}

}
