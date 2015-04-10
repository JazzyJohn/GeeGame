package nstuff.geogame.character;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by vania_000 on 27.03.2015.
 */
public class LocationListenerImpl implements LocationListener {
    private Location location;

    @Override
    public void onLocationChanged(Location location) {
        this.location= location;
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

    public Location getLocation() {
        return location;
    }
}
