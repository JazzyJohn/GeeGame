package nstuff.geogame.character;

import nstuff.geogame.character.skills.SkillType;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface Item {

    String getName();

    String getDescription();

    int getSkill(SkillType skillType);

    String getSkillDescr();
}
