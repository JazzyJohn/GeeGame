package nstuff.geogame.quests;


import android.location.Location;

import nstuff.geogame.MainActivity;
import nstuff.geogame.character.CharacterManager;
import nstuff.geogame.character.LocationListenerImpl;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.logic.Main;
import nstuff.geogame.quests.log.QuestLogger;
import nstuff.geogame.quests.log.SimpleQuestLogger;
import nstuff.geogame.quests.phases.Phase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class QuestMemory implements  Quest{
    private MainActivity activity;

    private QuestCharacter character;

    private Phase currentPhase;

    CharacterManager manager;

    LocationListenerImpl locationListener;

    SimpleQuestLogger questLogger;

    public QuestMemory(Phase currentPhase) {
        this.currentPhase = currentPhase;
        currentPhase.init(this);
        manager =Main.getInstance().getInstance(CharacterManager.class);
        locationListener=Main.getInstance().getInstance(LocationListenerImpl.class);
        questLogger = new SimpleQuestLogger();
    }

    @Override
    public void init(MainActivity activity, QuestCharacter character) {
        this.activity= activity;
        this.character =character;
        questLogger.init(activity);
        questLogger.reset();
    }

    @Override
    public void startPhase() {
        currentPhase.draw(activity);
        manager.Update();
    }

    @Override
    public void nextPhase() {
        if(currentPhase.hasNext()){
            currentPhase =currentPhase.getNextPhase();
            currentPhase.init(this);
            startPhase();
        }else{
            QuestManager manager =Main.getInstance().getInstance(QuestManager.class);
            manager.loadNewQuest(activity);
        }

    }

    @Override
    public QuestCharacter getCharacter() {
        return character;
    }

    @Override
    public void redrawPhase() {
        currentPhase.draw(activity);
        manager.Update();
    }

    @Override
    public QuestLogger getQuestLogger() {
        return questLogger;
    }

    @Override
    public Location getLocation() {
        return locationListener.getLocation();
    }
}
