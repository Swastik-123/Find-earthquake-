


//Study loader from lesson 3(Networking) see video(17 to 25)




package com.example.earthquakedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {


    private static final String LOG_TAG = MainActivity.class.getName();


    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";



    /** First we need to specify an ID for our loader. This is only really relevant if we were using multiple loaders
     *in the same activity. We can choose any integer number, so we choose the number 1.

     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;



//    /**
//     * Then we need to override the three methods specified in the LoaderCallbacks interface. We need onCreateLoader(), for when
//     * the LoaderManager has determined that the loader with our specified ID isn't running, so we should create a new one
//     */
//
//    @Override
//    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
//        // Create a new loader for the given URL
//        return new EarthquakeLoader(this,USGS_REQUEST_URL);
//    }
//
//
//    /**
//     * We need onLoadFinished(), where we'll do exactly what we did in onPostExecute(), and use the earthquake data to update
//     * our UI - by updating the dataset in the adapter.
//     */
//    public void onLoadFinished(Loader<List<Earthquake>> loader,List<Earthquake> earthquakes)
//    {
//
//
////         Set empty state text to display "No earthquakes found."
//        mEmptyStateTextView.setText(R.string.no_earthquakes);
//
//
//        // Clear the adapter of previous earthquake data
//        mAdapter.clear();
//
//
//        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//        // data set. This will trigger the ListView to update.
//        if(earthquakes != null && !earthquakes.isEmpty())
//        {
////            mAdapter.addAll(earthquakes);
//        }
//
//
//    }
//
//
//    /**
//     * And we need onLoaderReset(), we're we're being informed that the data from our
//     * loader is no longer valid. This isn't actually a case that's going to come up with our simple loader,
//     * but the correct thing to do is to remove all the earthquake data from our UI by clearing out the adapter’s data set.
//     */
//    @Override
//    public void onLoaderReset(Loader<List<Earthquake>> loader)
//    {
//        // Loader reset, so we can clear out our existing data.
//        mAdapter.clear();
//    }



    /**
     * Adapter for the list of earthquakes
     */
    private EarthquakeAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_list_view);


//        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
//
//        earthquake_list_view.setEmptyView(mEmptyStateTextView);



        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        // Get a reference to the ConnectivityManager to check state of network connectivity
//        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        // Get details on the currently active default data network
//        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        // If there is a network connection, fetch data
//        if(networkInfo != null && networkInfo.isConnected())
//        {
//
//            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//            // because this activity implements the LoaderCallbacks interface).
//            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
//        }
//        else
//        {
//            // Otherwise, display error
//            // First, hide loading indicator so error message will be visible
//            View loadingIndicator = findViewById(R.id.loading_indicator);
//            loadingIndicator.setVisibility(View.GONE);
//
//            // Update empty state with no connection error message
//            mEmptyStateTextView.setText(R.string.no_internet_connection);
//        }



        // Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);


//        final ArrayList<Earthquake> earthquakes = com.example.android.quakereport.QueryUtils.extractEarthquakes();
//
//        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);


        ListView listView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position); // currentEarthquakes just a object ,you can crate anything.

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);


                // Send the intent to launch a new activity
                startActivity(websiteIntent);


            }
        });


    }



    /**
     * Then we need to override the three methods specified in the LoaderCallbacks interface. We need onCreateLoader(), for when
     * the LoaderManager has determined that the loader with our specified ID isn't running, so we should create a new one
     */

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }


    /**
     * We need onLoadFinished(), where we'll do exactly what we did in onPostExecute(), and use the earthquake data to update
     * our UI - by updating the dataset in the adapter.
     */
    public void onLoadFinished(Loader<List<Earthquake>> loader,List<Earthquake> earthquakes)
    {


//        Set empty state text to display "No earthquakes found."
//        mEmptyStateTextView.setText(R.string.no_earthquakes);


        // Clear the adapter of previous earthquake data
        mAdapter.clear();


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(earthquakes != null && !earthquakes.isEmpty())
        {
            mAdapter.addAll(earthquakes);
        }



    }


    /**
     * And we need onLoaderReset(), we're we're being informed that the data from our
     * loader is no longer valid. This isn't actually a case that's going to come up with our simple loader,
     * but the correct thing to do is to remove all the earthquake data from our UI by clearing out the adapter’s data set.
     */
    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader)
    {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        protected List<Earthquake> doInBackground(String... urls) {

            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> result = com.example.android.quakereport.QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        protected void onPostExecute(List<Earthquake> data) {

            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
