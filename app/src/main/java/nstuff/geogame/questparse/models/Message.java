package nstuff.geogame.questparse.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Dictionary;

import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.MessagePhase;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Message extends Phase{
    private  int nextPhase;

    private String message;

    @JsonProperty("nextPhase")
    public int getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(int nextPhase) {
        this.nextPhase = nextPhase;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items) {
        ((MessagePhase) phaseDictionary.get(getId())).load(message,phaseDictionary.get(nextPhase));
    }
}
