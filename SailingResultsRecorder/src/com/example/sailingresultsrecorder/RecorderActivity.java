package com.example.sailingresultsrecorder;

import com.example.sailingresultsrecorder.R;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ListView;

public class RecorderActivity extends HeaderFooterActivity {
	private CompetitorAdapter adapter;
	private Competitor[] competitors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		
		ViewGroup vg = (ViewGroup) findViewById(R.id.listdata);
		ViewGroup.inflate(RecorderActivity.this, R.layout.listview, vg);
		
		Competitor c1 = new Competitor("166550", "Laser");
		Competitor c2 = new Competitor("4327", "Laser 4000");
		
		competitors = new Competitor[]{
			new Competitor("166550", "Laser"),
			new Competitor("4327", "Laser 4000")
		}; 

		
		adapter = new CompetitorAdapter(this, R.layout.listrow, competitors);
		
		ListView listView = (ListView) findViewById(R.id.listview);
		
		listView.setAdapter(adapter);
		listView.setClickable(true);
		
	}
	
	public void updateListView(){
		adapter.notifyDataSetChanged();
		Log.v("IDP", "updateListView invoked!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
