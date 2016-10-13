package com.example.henrik.p2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng myPosition = getLocation(this, MapsActivity.this);
        mMap.addMarker(new MarkerOptions().position(myPosition).title("My Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13.0f));


    }
    public LatLng getLocation(Context context, Activity activity) {
        int status = context.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                context.getPackageName());
        if(status == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        while (status == PackageManager.PERMISSION_DENIED){
            if(context.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, context.getPackageName())==PackageManager.PERMISSION_GRANTED){
                status = PackageManager.PERMISSION_GRANTED;
            }
        }

            LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = mgr.getAllProviders();
            if (providers != null && providers.contains(LocationManager.NETWORK_PROVIDER)) {
                Location loc = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (loc != null) {
                    return new LatLng(loc.getLatitude(), loc.getLongitude());
                }
            }
        return new LatLng(1.1, 1.1);
    }
}
