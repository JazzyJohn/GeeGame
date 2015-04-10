package nstuff.geogame.character;

import java.util.List;

import nstuff.geogame.character.skills.SkillType;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface QuestCharacter {
    int getSkill(SkillType skillType);

    void lowerHP(int damage);

    void addItem(Item item);

    void addMoney(int money);

    void addSkill(int skill);

    void addExp(int exp);

    int getHp();

    int getExp();

    int getGold();

    List<Item> getItems();
}
