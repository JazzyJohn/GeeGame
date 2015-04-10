package nstuff.geogame.questparse.models;

import java.util.List;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Item {

    private int id;

    private String description;

    private String name;

    private List<ItemSkill> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<ItemSkill> skills) {
        this.skills = skills;
    }
}
