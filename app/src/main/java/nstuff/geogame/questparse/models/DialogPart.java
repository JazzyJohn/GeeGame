package nstuff.geogame.questparse.models;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class DialogPart {
    private int nextPhase;

    private String name;

    private int difficulty;

    private int badPhase;

    public int getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(int nextPhase) {
        this.nextPhase = nextPhase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBadPhase() {
        return badPhase;
    }

    public void setBadPhase(int badPhase) {
        this.badPhase = badPhase;
    }
}
