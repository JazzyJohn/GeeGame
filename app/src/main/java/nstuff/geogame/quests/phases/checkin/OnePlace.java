package nstuff.geogame.quests.phases.checkin;

import android.location.Location;

import com.google.android.gms.maps.model.Marker;

import nstuff.geogame.quests.phases.Phase;

/**
 * Created by vania_000 on 27.03.2015.
 */
public class OnePlace{

    public OnePlace(Phase nextPhase, String place,float latitude,float longitude) {
        this.nextPhase = nextPhase;
        this.place = place;
        location = new Location(place);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
    }
    public Location location;
    public Phase nextPhase;
    public Marker marker;
    public String place;
}