package nstuff.geogame.quests.phases;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Location;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.quests.Quest;
import nstuff.geogame.quests.phases.checkin.OnePlace;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class CheckInPhase extends BasePhase implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {




    public void load(List<OnePlace> places) {
            this.places = places;
            resolved=false;
    }

    private List<OnePlace> places;

    private MainActivity activity;

    private boolean resolved=false;
    @Override
    public void draw(MainActivity activity) {
        this.activity =activity;
        ViewGroup view =prepareActivity(activity);

        ViewGroup map = new LinearLayout(activity);
        map.setId(R.id.mapview);
        view.addView(map);
        // Create new fragment and transaction
        MapFragment newFragment = new MapFragment();
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.mapview, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        newFragment.getMapAsync(this);


    }





    LatLngBounds bounds= null;
    @Override
    public void onMapReady(GoogleMap gMap) {
        gMap.setMyLocationEnabled(true);
        //gMap.setOnInfoWindowClickListener(this);
        gMap.setOnMarkerClickListener(this);
        Location my = quest.getLocation();

        LatLng lalonMy = new LatLng(my.getLatitude(),my.getLongitude());
        double latitudeSouthern =my.getLatitude(),latitudeNorthern=my.getLatitude(),longitudeSouthern=my.getLongitude(),longitudeNorthern=my.getLongitude();
        for (OnePlace place : places) {
            LatLng lalonPlace = new LatLng(place.location.getLatitude(),place.location.getLongitude());
           if(latitudeSouthern>place.location.getLatitude()){
               latitudeSouthern =place.location.getLatitude();
           }
            if(latitudeNorthern<place.location.getLatitude()){
                latitudeNorthern =place.location.getLatitude();
            }
            if(longitudeSouthern>place.location.getLongitude()){
                longitudeSouthern =place.location.getLongitude();
            }
            if(longitudeNorthern<place.location.getLongitude()){
                longitudeNorthern =place.location.getLongitude();
            }
            place.marker  = gMap.addMarker(new MarkerOptions()
                    .title(place.place)
                    .snippet(place.place)

                    .position(lalonPlace));
            place.marker.showInfoWindow();
        }
        LatLng south = new LatLng(latitudeSouthern,longitudeSouthern);
        LatLng north = new LatLng(latitudeNorthern,longitudeNorthern);
        bounds = new LatLngBounds(south,north);
        ViewGroup view = (ViewGroup) activity.findViewById(R.id.LinerLayout);
        gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, view.getWidth(),view.getHeight(),10));


    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        if(resolved){
            return;
        }
        for (OnePlace place : places) {
            if(place.marker.equals(marker)){
                this.nextPhase = place.nextPhase;
                quest.nextPhase();
                break;
            }
        }
        resolved = true;
    }
    @Override
    public boolean onMarkerClick(Marker marker) {

        for (OnePlace place : places) {
            if(place.marker.equals(marker)){
                this.nextPhase = place.nextPhase;
                quest.nextPhase();
                return false;
            }
        }
        return true;
    }
}
