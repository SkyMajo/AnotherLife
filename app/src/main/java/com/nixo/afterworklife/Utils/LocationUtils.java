package com.nixo.afterworklife.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.jerey.loglib.Klog;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class LocationUtils {



    @SuppressLint("MissingPermission")
    private Location getUserLocation(Context context) {
         double lat = 0.0;
         double lon = 0.0;

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            @SuppressLint("MissingPermission")
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location!= null){
                lat = location.getLatitude();
                lon = location.getLongitude();
                return location;
            }else{
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        if (location != null) {
                            Klog.Companion.e("Map", "Location changed : Lat: "
                                    + location.getLatitude() + " Lng: "
                                    + location.getLongitude());
                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);
                Location afterLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(afterLocation != null){
                    lat = afterLocation.getLatitude(); //经度
                    lon = afterLocation.getLongitude(); //纬度
                    return afterLocation;
                }
            }
        }
    return null;
    }




}
