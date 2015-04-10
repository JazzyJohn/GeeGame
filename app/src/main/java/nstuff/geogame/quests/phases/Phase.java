package nstuff.geogame.quests.phases;

import android.app.Activity;

import java.util.Queue;

import nstuff.geogame.MainActivity;
import nstuff.geogame.quests.Quest;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface Phase {

  void draw(MainActivity activity);

  void init(Quest quest);

  boolean hasNext();

  Phase getNextPhase();

  Quest getQuest();

}
