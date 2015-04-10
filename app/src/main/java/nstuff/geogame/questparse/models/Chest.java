package nstuff.geogame.questparse.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.ChestPhase;
import nstuff.geogame.quests.phases.reward.Reward;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Chest extends Phase{

    private int nextPhase;

    private String message;

    private int money;

    private int exp;

    private int skill;

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    private List<Integer> items;

    public int getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(int nextPhase) {
        this.nextPhase = nextPhase;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> itemsDic) {
        List<Item> itemList = new ArrayList<>();
        for(Integer id: items){
            itemList.add(itemsDic.get(id));
        }
        Reward reward =new Reward(itemList,money,skill,exp);

        ((ChestPhase) phaseDictionary.get(getId())).load(reward,message,phaseDictionary.get(nextPhase));
    }
}
