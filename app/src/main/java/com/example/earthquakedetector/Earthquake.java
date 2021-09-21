package com.example.earthquakedetector;

public class Earthquake {


    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String location, String date, long timeMilliseconds,String url)
    {

        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mTimeMilliseconds = timeMilliseconds;
        mUrl = url;
    }

    public Earthquake(double magnitude, String location, long time,String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeMilliseconds = time;
        mUrl = url;
    }

    public double getmMagnitude()
    {
        return mMagnitude;
    }
    public  String getmLocation()
    {
        return mLocation;
    }
    public  String getmDate()
    {
        return mDate;
    }

    public long getmTimeMilliseconds()
    {
        return mTimeMilliseconds;
    }
    public String getmUrl()
    {
        return mUrl;
    }
}
