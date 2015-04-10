package nstuff.geogame.character;

import java.util.ArrayList;
import java.util.List;

import nstuff.geogame.character.skills.SkillType;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class QuestCharacterInMemory implements QuestCharacter {
    private List<Item> items = new ArrayList<>();
    private int hp=10;
    private int exp=0;
    private int gold=0;

    @Override
    public int getSkill(SkillType skillType) {
        int base = 0;
        for(Item item : items){
            base+= item.getSkill(skillType);
        }
        return base;
    }

    @Override
    public void lowerHP(int damage) {
        hp-=damage;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void addMoney(int money) {
        gold+=money;
    }

    @Override
    public void addSkill(int skill) {

    }

    @Override
    public void addExp(int exp) {
        this.exp+=exp;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getExp() {
        return exp;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
