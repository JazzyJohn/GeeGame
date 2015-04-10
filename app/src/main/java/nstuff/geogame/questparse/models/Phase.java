package nstuff.geogame.questparse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Dictionary;

import nstuff.geogame.character.*;
import nstuff.geogame.character.Item;

/**
 * Created by vania_000 on 04.04.2015.
 */

public abstract class Phase {

    private int id;
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public abstract void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items);
}
