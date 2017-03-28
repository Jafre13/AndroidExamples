package jafre13.mapsexample;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    LocationManager lm;
    ArrayList<String> providers;
    Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        lm = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        providers = new ArrayList<>(lm.getAllProviders());
    }

    public void setup(){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        try {
            myLocation = lm.getLastKnownLocation("gps");
        } catch (SecurityException e) {
            System.out.println(e);
        }
        // adds a marker where we are
        LatLng here = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(here).title("Marker here"));
        mMap.addCircle(new CircleOptions().fillColor(1).center(here).radius(50));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(here).zoom(15).build()));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        setup();
    }
}
