package com.example.root.googleplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity
{
    private Intent intent;
    private LatLngBounds bounds;
    private PlaceAutocomplete.IntentBuilder intentBuilder;

    private TextView textViewAddress;
    private TextView textViewMobile;
    private TextView textViewWebsite;

    private String name;
    private String address;
    private String mobile;
    private String website;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeIntentBuilder();

        try
        {
            intent = intentBuilder.build(MainActivity.this);
            startActivityForResult(intent, 1);
        }

        catch (GooglePlayServicesRepairableException e)
        {
            e.printStackTrace();
        }

        catch (GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        }

    }

    private void initializeViews()
    {
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewMobile = (TextView) findViewById(R.id.textViewMobile);
        textViewWebsite = (TextView) findViewById(R.id.textViewWebsite);
    }

    private void initializeIntentBuilder()
    {
        bounds = new LatLngBounds(new LatLng(28.390261, 76.574797), new LatLng(28.902470, 77.499016));  // Delhi
        intentBuilder = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN);
        intentBuilder.setBoundsBias(bounds);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Place place;
        Status status;


        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                place = PlaceAutocomplete.getPlace(this, data);
                getData(place);
                displayResults();
            }

            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
            {
                status = PlaceAutocomplete.getStatus(this, data);
                //error
            }
        }

    }

    private void getData(Place place)
    {
        name = place.getName().toString();
        address = place.getAddress().toString();
        mobile = place.getPhoneNumber().toString();
        website = place.getWebsiteUri().toString();
    }

    private void displayResults()
    {
        textViewAddress.setText(address);
        textViewMobile.setText(mobile);
        textViewWebsite.setText(website);
    }


}
