package nstuff.geogame.quests;

import android.location.Location;

import nstuff.geogame.MainActivity;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.quests.log.QuestLogger;
import nstuff.geogame.quests.phases.Phase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface Quest {

    void init(MainActivity activity,QuestCharacter character);

    void startPhase();

    void nextPhase();

    QuestCharacter getCharacter();

    void redrawPhase();

    QuestLogger getQuestLogger();

    Location getLocation();
}
