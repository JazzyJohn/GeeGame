package nstuff.geogame.character;

import android.widget.TextView;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class CharacterManagerInMemory implements CharacterManager {
    private QuestCharacter character;
    private MainActivity activity;
    @Override
    public void init(MainActivity activity) {
        this.activity =activity;
        character = new QuestCharacterInMemory();

    }

    @Override
    public QuestCharacter getCharacter() {

        return character;
    }

    @Override
    public void Update() {
        ((TextView)activity.findViewById(R.id.playerHp)).setText(character.getHp()+"");
        ((TextView)activity.findViewById(R.id.playerExp)).setText(character.getExp()+"");
        ((TextView)activity.findViewById(R.id.playerGold)).setText(character.getGold()+"");
    }
}
