package nstuff.geogame.quests;

import nstuff.geogame.MainActivity;

/**
 * Created by vania_000 on 25.03.2015.
 */

public interface QuestManager {

    void loadNewQuest(MainActivity activity);

    Quest currentQuest();
}
