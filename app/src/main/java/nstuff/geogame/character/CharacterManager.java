package nstuff.geogame.character;

import nstuff.geogame.MainActivity;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface CharacterManager {
    void init(MainActivity activity);
    QuestCharacter getCharacter();
    void Update();
}
