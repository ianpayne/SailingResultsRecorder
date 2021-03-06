package com.example.sailingresultsrecorder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CompetitorAdapter extends ArrayAdapter<Competitor> {
	private Context context;
	int layoutResourceId;
	Competitor competitor;
	Competitor competitors[];
	private LayoutInflater inflater;
	
	public CompetitorAdapter(Context context, int layoutResourceId, Competitor[] competitors){
		super(context, layoutResourceId, competitors);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.competitors = competitors;	
		this.inflater = ((Activity)context).getLayoutInflater();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		Holder holder;
		competitor = this.getItem(position);
		
		if (row == null || row.getTag() == null) {
			//LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new Holder();
			holder.sailNo = (TextView) row.findViewById(R.id.txtSailNo);
			holder.boatClass = (TextView) row.findViewById(R.id.txtClass);
			holder.finishTime = (TextView) row.findViewById(R.id.txtTime);
			
			holder.btnLap = (Button) row.findViewById(R.id.btnLap);
			holder.btnLap.setOnClickListener(new onLapClickListener(competitor));

			
			holder.btnFinish = (Button) row.findViewById(R.id.btnFinish);
			holder.btnFinish.setOnClickListener(new onFinishClickListener(competitor));
			Log.v("IDP", "row is null");
		}
		else {
			holder = (Holder)row.getTag();
			Log.v("IDP", "row is NOT null");
		}
		
		if (holder != null){
			holder.sailNo.setText(competitor.getSailNo());
			holder.boatClass.setText(competitor.getBoatClass());
			holder.finishTime.setText(competitor.getFinishTime());
			Log.v("IDP", "holder is NOT null" + competitor.getFinishTime());
		}
		else {
			Log.v("IDP", "holder is null");
		}


		
		return row;
	}
	
	private static class Holder {
		TextView sailNo;
		TextView boatClass;
		TextView finishTime;
		Button btnLap;
		Button btnFinish;
	}
	
    
	private class onLapClickListener implements OnClickListener {
		private Competitor competitor;
		
		public onLapClickListener(Competitor competitor){
			this.competitor = competitor;
		}
		
		@Override
		public void onClick(View v){
			//String lap = java.text.DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().getTime());
			String lap = getCurrentTimeStamp();
			//competitor.setFinishTime(lap);
			competitor.addLapTime(lap);
			Log.v("IDP", "Lap clicked, " + competitor.getSailNo() + ", Lap Time " + competitor.getFinishTime() + ", No of Laps" + competitor.getNoOfLaps());
			
			MainActivity act = (MainActivity) context;
			act.updateListView();
		}
	}
	
	private class onFinishClickListener implements OnClickListener {
		private Competitor competitor;
		
		public onFinishClickListener(Competitor competitor){
			this.competitor = competitor;
		}
		
		@Override
		public void onClick(View v){
			//String finish = java.text.DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().getTime());
			String finish = getCurrentTimeStamp();
			competitor.setFinishTime(finish);
			//competitor.addLapTime(finish);
			Log.v("IDP", "Finish clicked, " + competitor.getSailNo() + ", " + competitor.getFinishTime() + ", No of Laps" + competitor.getNoOfLaps());
			
			MainActivity act = (MainActivity) context;			
			act.updateListView();
		}
	}
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
}
