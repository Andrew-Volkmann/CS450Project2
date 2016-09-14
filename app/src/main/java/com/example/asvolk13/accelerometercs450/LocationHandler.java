package com.example.asvolk13.accelerometercs450;

import java.util.Observable;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by asvolk13 on 9/13/2016.
 */
public class LocationHandler extends Observable {
    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;
    MainActivity act;

    public LocationHandler(MainActivity act) {
        this.act = act;
        locationManager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);

    }
}
