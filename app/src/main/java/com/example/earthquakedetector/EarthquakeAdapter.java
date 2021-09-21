package com.example.earthquakedetector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    private static final String LOCATION_SEPARATOR = " of ";   //for split String


    String primaryLocation;   //for split String
    String locationOffset;   //for split String


    public class SimpleDataFormat {

        long timeInMilliseconds = 1454124312220L;
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

    }




    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View earthItemView = convertView;
        if(earthItemView == null)
        {
            earthItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake jack = getItem(position);



        String originalLocation = jack.getmLocation();   //for split String

        if (originalLocation.contains(LOCATION_SEPARATOR)) {   //for split String
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);   //for split String
            locationOffset = parts[0] + LOCATION_SEPARATOR;   //for split String
            primaryLocation = parts[1];   //for split String
        } else {   //for split String
            locationOffset = getContext().getString(R.string.near_the);   //for split String
            primaryLocation = originalLocation;   //for split String
        }   //for split String







        TextView magnitudeTextView = (TextView)earthItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(jack.getmMagnitude());  //declared method bellow
        // Display the magnitude of the current earthquake in that TextView
        magnitudeTextView.setText(formattedMagnitude);  //declared method bellow



        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(jack.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);












        TextView locationTextView = (TextView)earthItemView.findViewById(R.id.primary_location);
        locationTextView.setText(jack.getmLocation());




        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(jack.getmTimeMilliseconds());


        TextView dateText = (TextView)earthItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);  //declared method bellow
        dateText.setText(formattedDate);  //declared method bellow


        TextView timeTextView = (TextView)earthItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);  //declared method bellow
        timeTextView.setText(formattedTime);  //declared method bellow



        //for split String
        TextView primaryLocationView = (TextView)earthItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView)earthItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);


        return earthItemView;


    }


//    The above code includes two helper methods, formatDate() and formatTime(), that we created to accept
//    a Date object and return an appropriately formatted date string using SimpleDateFormat.



    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }




    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }







}
