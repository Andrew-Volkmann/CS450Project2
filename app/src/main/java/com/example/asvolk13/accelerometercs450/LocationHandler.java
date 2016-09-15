package com.example.asvolk13.accelerometercs450;

import java.util.Observable;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by asvolk13 on 9/13/2016.
 */
public class LocationHandler extends Observable {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;

    Activity act;
    private static final long DISTANCE_TO_UPDATE = 0;
    private static final long TIME_BETWEEN_UPDATES = 0;

    public LocationHandler(Activity act) {
        this.act = (MainActivity) act;
        locationManager = (LocationManager) this.act.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                setChanged();
                notifyObservers(location);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {

            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        while (ActivityCompat.checkSelfPermission(this.act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this.act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                this.act.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                this.act.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }

        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, TIME_BETWEEN_UPDATES, DISTANCE_TO_UPDATE, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_BETWEEN_UPDATES, DISTANCE_TO_UPDATE, locationListener);

        this.location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        setChanged();
        notifyObservers(location);

    }
    public Location getLocation(){
        return location;
    }
}

