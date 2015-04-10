package nstuff.geogame.questparse.models;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class CheckInPlace {

    private int nextPhase;

    private String name;

    private String coord;

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

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public float getLat() {
        String[] tar= coord.split(",");

        return      Float.parseFloat(tar[0]);
    }
    public float getLong() {
        String[] tar= coord.split(",");

        return  Float.parseFloat(tar[1]);
    }
}
