package nstuff.geogame.character;

import java.util.List;

import nstuff.geogame.character.skills.SkillBuff;
import nstuff.geogame.character.skills.SkillType;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class SimpleItem implements Item {

    private String name;

    private String description;

    private List<SkillBuff> skillBuffs;

    private String skillDescr;


    public SimpleItem(String name, String description, List<SkillBuff> skillBuffs, String skillDescr) {
        this.name = name;
        this.description = description;
        this.skillBuffs = skillBuffs;
        this.skillDescr = skillDescr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSkill(SkillType skillType) {
        for(SkillBuff skillBuff : skillBuffs){
            if(skillBuff.type==skillType){
                return skillBuff.amount;
            }
        }
        return 0;
    }

    @Override
    public String getSkillDescr() {
        return skillDescr;
    }
}
