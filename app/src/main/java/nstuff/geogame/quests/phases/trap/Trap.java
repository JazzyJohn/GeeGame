package nstuff.geogame.quests.phases.trap;

import java.util.Random;

import nstuff.geogame.GeoApp;
import nstuff.geogame.R;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.character.skills.SkillType;
import nstuff.geogame.logic.Main;
import nstuff.geogame.quests.phases.TrapPhase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class Trap {
    private int difficulty;

    private int damage;

    private int exp;
    private TrapPhase phase;

    public Trap(int difficulty, int damage, int exp) {
        this.difficulty = difficulty;
        this.damage = damage;
        this.exp = exp;
    }

    public void damage(QuestCharacter character){
        character.lowerHP(damage);
    }

    public boolean disarm(QuestCharacter character){
        int skill = character.getSkill(SkillType.Disarm);
        Random rand = Main.getInstance().getInstance(Random.class);
        int dice =rand.nextInt(Main.DICE_SIZE);
        phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.trap),difficulty,dice+skill,skill,dice));

        dice+=skill;
        if(dice>difficulty){
            character.addExp(exp);
            return  true;
        }else{
            damage(character);
            return  false;
        }

    }

    public void setPhase(TrapPhase phase) {
        this.phase = phase;
    }
}
