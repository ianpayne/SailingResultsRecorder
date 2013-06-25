package com.example.sailingresultsrecorder;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

public class Competitor implements Parcelable {
	private String sailNo;
	private String boatClass;
	private ArrayList<String> lapTimes;
	private boolean finished = false;
	
	// could implement a arraylist of lap times, with the last time being the finish time, and the number of laps the size of array.
	
	public Competitor(String sailNo, String boatClass){
		this.sailNo = sailNo;
		this.boatClass = boatClass;
		lapTimes = new ArrayList<String>();
	}
	
	public Competitor(Parcel in){
		this.sailNo = in.readString();
		this.boatClass = in.readString();
		//this.finishTime = in.readString();
		in.readStringList(lapTimes);	
	}
	
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel(Parcel desc, int flags){
		desc.writeString(sailNo);
		desc.writeString(boatClass);
		//desc.writeString(finishTime);
		desc.writeStringList(lapTimes);
	}
	
	public static final Parcelable.Creator<Competitor> CREATOR = new Parcelable.Creator<Competitor>(){
		public Competitor createFromParcel(Parcel in){
			return new Competitor(in);
		}
		public Competitor[] newArray(int size){
			return new Competitor[size];
		}
	};

	public String getSailNo(){
		return sailNo;
	}
	public String getBoatClass(){
		return boatClass;
	}
	public String getFinishTime(){
		if (getNoOfLaps() > 0){
			return this.lapTimes.get(getNoOfLaps() -1);  // think about this
		}
		return "00:00:00";
		
	}
	public int getNoOfLaps(){
		return lapTimes.size();
	}
	public boolean isFinished(){
		return this.finished;
	}

	public void addLapTime(String lapTime) {
		if (!finished)
			this.lapTimes.add(lapTime);
	}
	public void setFinishTime(String finishTime) {
		this.addLapTime(finishTime);
		this.finished = true;
	}
}
