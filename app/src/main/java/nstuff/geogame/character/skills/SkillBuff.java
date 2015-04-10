package nstuff.geogame.character.skills;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class SkillBuff {

    public SkillType type;

    public int amount;

    public SkillBuff(SkillType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
