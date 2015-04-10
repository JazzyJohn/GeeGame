package nstuff.geogame.questparse.models;

import nstuff.geogame.character.skills.SkillType;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class ItemSkill {

    private SkillType skill;

    private int value;

    public SkillType getSkill() {
        return skill;
    }

    public void setSkill(SkillType skill) {
        this.skill = skill;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
