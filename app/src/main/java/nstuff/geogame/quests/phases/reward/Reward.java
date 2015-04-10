package nstuff.geogame.quests.phases.reward;

import java.util.List;

import nstuff.geogame.GeoApp;
import nstuff.geogame.R;
import nstuff.geogame.character.Item;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.quests.phases.ChestPhase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class Reward {

    public  List<Item> items;
    public int money;
    public int skill;
    public int exp;
    private ChestPhase phase;

    public Reward(List<Item> items, int money, int skill, int exp) {
        this.items = items;
        this.money = money;
        this.skill = skill;
        this.exp = exp;
    }
    public void resolved(QuestCharacter character){
        for(Item item :items){
            character.addItem(item);
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.itemFound),item.getName()));

        }
        if(money>0){
            character.addMoney(money);
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.moneyFound),money));

        }
        if(skill>0){
            character.addSkill(skill);
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.skillFound),skill));
        }
        if(exp>0){
            character.addExp(exp);
            phase.getQuest().getQuestLogger().addString(String.format(GeoApp.getContext().getResources().getString(R.string.expFound),exp));
        }
    }

    public void setPhase(ChestPhase phase) {
        this.phase = phase;
    }
}
