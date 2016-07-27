package com.udacity.caraher.emma.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("main activity", "onCreate called");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if (id == R.id.action_map) {
            openLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openLocationInMap() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String locationPref = sharedPref.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", locationPref)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.e("Opening Map Call", "Couldn't call " + locationPref + ", no receiving apps installed!");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("main activity", "onPause called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("main activity", "onResume called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("main activity", "onStop called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("main activity", "onStart called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("main activity", "onDestroy called");
    }
}