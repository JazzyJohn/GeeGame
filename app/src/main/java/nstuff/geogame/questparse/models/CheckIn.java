package nstuff.geogame.questparse.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.CheckInPhase;
import nstuff.geogame.quests.phases.checkin.OnePlace;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class CheckIn extends Phase{

    private List<CheckInPlace> checkInPlaces;

    public List<CheckInPlace> getCheckInPlaces() {
        return checkInPlaces;
    }

    public void setCheckInPlaces(List<CheckInPlace> checkInPlaces) {
        this.checkInPlaces = checkInPlaces;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items) {
        List<OnePlace> places = new ArrayList<>();
        for(CheckInPlace inPlace: checkInPlaces){
            OnePlace place = new OnePlace(phaseDictionary.get(inPlace.getNextPhase()),inPlace.getName(),inPlace.getLat(),inPlace.getLong());
            places.add(place);
        }
        ((CheckInPhase)(phaseDictionary.get(getId()))).load(places);
    }
}
