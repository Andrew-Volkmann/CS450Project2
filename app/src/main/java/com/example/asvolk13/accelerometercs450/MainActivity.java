package com.example.asvolk13.accelerometercs450;


import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.IllegalFormatConversionException;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity implements Observer {
    public static final String LONGITUDE = "LONGITUDE";
    public static final String LATITUDE = "LATITUDE";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String Z = "Z";

    TextView z_accel_view = null;
    TextView x_accel_view = null;
    TextView y_accel_view = null;
    TextView longitude_view = null;
    TextView latitude_view = null;
    AccelerometerHandler ah = null;
    LocationHandler lh = null;
    Location location;
    String lat;
    String lon;
    float[] xyz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.z_accel_view = (TextView) findViewById(R.id.z_accel_view);
        this.x_accel_view = (TextView) findViewById(R.id.x_accel_view);
        this.y_accel_view = (TextView) findViewById(R.id.y_accel_view);
        this.longitude_view = (TextView) findViewById(R.id.Longitude_view);
        this.latitude_view = (TextView) findViewById(R.id.Latitude_view);


    }

    @Override
    protected void onStart() {
        super.onStart();
        this.ah = new AccelerometerHandler(this);
        this.ah.addObserver(this);
        this.lh = new LocationHandler(this);
        this.lh.addObserver(this);

        this.lat = getPreferences(MODE_PRIVATE).getString(LATITUDE, "");
        this.lon = getPreferences(MODE_PRIVATE).getString(LONGITUDE, "");
        this.latitude_view.setText(lat);
        this.longitude_view.setText(lon);

        this.x_accel_view.setText(getPreferences(MODE_PRIVATE).getString(X, ""));
        this.y_accel_view.setText(getPreferences(MODE_PRIVATE).getString(Y, ""));

        this.z_accel_view.setText(getPreferences(MODE_PRIVATE).getString(Z, ""));
    }
    @Override
    protected void onPause() {
        super.onPause();
        //save
        getPreferences(MODE_PRIVATE).edit().putString(LATITUDE, Double.toString(lh.getLocation().getLatitude())).apply();

        getPreferences(MODE_PRIVATE).edit().putString(LONGITUDE, Double.toString(lh.getLocation().getLongitude())).apply();

        //save xyz
        getPreferences(MODE_PRIVATE).edit().putString(X, Float.toString(xyz[0])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(Y, Float.toString(xyz[1])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(Z, Float.toString(xyz[2])).apply();




    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof AccelerometerHandler) {
            this.xyz = (float[]) o;

            this.z_accel_view.setText(Float.toString(xyz[2]));
            this.x_accel_view.setText(Float.toString(xyz[0]));
            this.y_accel_view.setText(Float.toString(xyz[1]));
        }

        if (observable instanceof LocationHandler) {
            this.location = (Location) o;
            this.lat= Double.toString(location.getLatitude());
            this.lon= Double.toString(location.getLongitude());
            this.latitude_view.setText(lat);
            this.longitude_view.setText(lon);
    }

    }
}


