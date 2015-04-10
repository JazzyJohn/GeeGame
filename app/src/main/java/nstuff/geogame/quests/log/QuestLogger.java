package nstuff.geogame.quests.log;

import nstuff.geogame.MainActivity;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface QuestLogger {
    void init(MainActivity activity);

    void addString(String text);

    void reset();
}
