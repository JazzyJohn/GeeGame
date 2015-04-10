package nstuff.geogame.quests.phases.dialog;

import android.view.View;

import java.util.Random;

import nstuff.geogame.GeoApp;
import nstuff.geogame.R;
import nstuff.geogame.character.skills.SkillType;
import nstuff.geogame.logic.Main;
import nstuff.geogame.quests.phases.Phase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class SkillDialogOption  extends BaseDialogOption {
    private Phase badPhase;

    private int difficulty;


    public void load(String option, Phase nextPhase,Phase badPhase,int difficulty) {
        super.load(option, nextPhase);
        this.badPhase =badPhase;
        this.difficulty = difficulty;
    }

    @Override
    public void onClick(View v) {

        Random rand = Main.getInstance().getInstance(Random.class);
        int dice =rand.nextInt(Main.DICE_SIZE);
        int skill = phase.getQuest().getCharacter().getSkill(SkillType.Diplomacy);

        phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.diplomacy),difficulty,dice+skill,skill,dice));
        dice +=skill ;
        if(dice>difficulty){
            phase.answer(nextPhase);
        }else{
            phase.answer(badPhase);
        }
    }
}
