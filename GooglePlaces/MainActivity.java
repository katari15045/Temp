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

public class MainActivity extends AppCompatActivity
{
    private Button buttonFind;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        buttonFind.setOnClickListener( new MyListener() );
    }

    private void initializeViews()
    {
        buttonFind = (Button) findViewById(R.id.buttonFind);
        textViewResults = (TextView) findViewById(R.id.textViewResult);
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
                textViewResults.setText(place.getName()+"\n"+ place.getAddress() +"\n" + place.getPhoneNumber());
            }

            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
            {
                status = PlaceAutocomplete.getStatus(this, data);
                textViewResults.setText( status.toString() );
            }
        }
    }

    private class MyListener implements View.OnClickListener
    {
        private Intent intent;

        @Override
        public void onClick(View v)
        {
            try
            {
                intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(MainActivity.this);
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
    }

}
