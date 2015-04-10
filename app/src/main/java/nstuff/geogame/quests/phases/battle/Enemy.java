package nstuff.geogame.quests.phases.battle;

import java.util.Date;
import java.util.Random;

import nstuff.geogame.GeoApp;
import nstuff.geogame.R;
import nstuff.geogame.logic.Main;
import nstuff.geogame.quests.phases.BattlePhase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class Enemy {

    private int hp;

    private int meleeWeak;

    private int rangeWeak;

    private int ac;

    private int attack;

    private int damage;

    private String name;
    private BattlePhase phase;

    public Enemy(int hp, int meleeWeak, int rangeWeak, int ac, String name,int attack,int damage) {
        this.hp = hp;
        this.meleeWeak = meleeWeak;
        this.rangeWeak = rangeWeak;
        this.ac = ac;
        this.name = name;
        this.attack = attack;
        this.damage = damage;
    }

    public Enemy(Enemy blueprint) {
        this.meleeWeak = blueprint.meleeWeak;
        this.rangeWeak =  blueprint.rangeWeak;
        this.ac =  blueprint.ac;
        this.name =  blueprint.name;
        this.hp =  blueprint.hp;
        this.damage = blueprint.damage;
        this.attack = blueprint.attack;
    }

    public String getName() {
        return name;
    }

    public boolean damage(DamageType type,int attack){
        Random rand = Main.getInstance().getInstance(Random.class);
        int dice =rand.nextInt(Main.DICE_SIZE);

        switch(type){
            case Melee:
                phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.attackLog),ac,dice+attack+meleeWeak,attack,dice,meleeWeak));
                dice+= meleeWeak;
                break;
            case Range:
                phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.attackLog),ac,dice+attack+rangeWeak,attack,dice,rangeWeak));
                dice+= rangeWeak;
                break;
        }
        dice+= attack;

        if(dice>=ac){
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.lowerHP),name));
            hp--;
            return true;
        }
        return false;

    }
    public boolean isDead(){
        return  hp<=0;
    }
    public int attackAC(int ac){
        Random rand = Main.getInstance().getInstance(Random.class);
        int dice =rand.nextInt(Main.DICE_SIZE);
        ac+=Main.BASE_AC;
        phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.attackAC),name,ac,dice+attack,attack,dice));
        dice+= attack;


        if(dice>=ac){
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.lowerPlayerHP),name,damage));
            return damage;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return name+" "+hp+" hp";
    }

    public void setPhase(BattlePhase phase) {
        this.phase = phase;
    }
}
