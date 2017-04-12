package com.example.root.googleplaces;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;
    private Place place;
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
    private LatLng latLng;

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
        Status status;


        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                place = PlaceAutocomplete.getPlace(this, data);
                getData(place);
                initializeMap();
                displayResults();
            }

            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
            {
                status = PlaceAutocomplete.getStatus(this, data);
                //error
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        latLng = place.getLatLng();
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker at Target"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void getData(Place place)
    {
        initializeDefaultValues();

        if( place.getName() != null )
        {
            name = place.getName().toString();
        }

        if( place.getAddress() != null )
        {
            address = place.getAddress().toString();
        }

        if( place.getPhoneNumber() != null )
        {
            mobile = place.getPhoneNumber().toString();
        }

        if( place.getWebsiteUri() != null )
        {
            website = place.getWebsiteUri().toString();
        }

    }

    private void initializeDefaultValues()
    {
        name = "Unavailable";
        address = "Unavailable";
        mobile = "Unavailable";
        website = "Unavailable";
    }


    private void initializeMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void displayResults()
    {
        textViewAddress.setText(address);
        textViewMobile.setText(mobile);
        textViewWebsite.setText(website);
    }


}
