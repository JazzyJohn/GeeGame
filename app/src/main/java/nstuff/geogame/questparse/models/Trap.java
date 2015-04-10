package nstuff.geogame.questparse.models;

import java.util.Dictionary;

import nstuff.geogame.character.*;
import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.TrapPhase;
import nstuff.geogame.quests.phases.trap.TrapTexts;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Trap  extends Phase{

    private int nextPhase;

    private  int difficulty;

    private int exp;

    private int damage;

    private String seeTrap;

    private  String go;

    private String goText;

    private String disarm;

    private String disarmOk;

    private String disarmBad;


    public int getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(int nextPhase) {
        this.nextPhase = nextPhase;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getSeeTrap() {
        return seeTrap;
    }

    public void setSeeTrap(String seeTrap) {
        this.seeTrap = seeTrap;
    }

    public String getGo() {
        return go;
    }

    public void setGo(String go) {
        this.go = go;
    }

    public String getGoText() {
        return goText;
    }

    public void setGoText(String goText) {
        this.goText = goText;
    }

    public String getDisarm() {
        return disarm;
    }

    public void setDisarm(String disarm) {
        this.disarm = disarm;
    }

    public String getDisarmOk() {
        return disarmOk;
    }

    public void setDisarmOk(String disarmOk) {
        this.disarmOk = disarmOk;
    }

    public String getDisarmBad() {
        return disarmBad;
    }

    public void setDisarmBad(String disarmBad) {
        this.disarmBad = disarmBad;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items) {
        nstuff.geogame.quests.phases.trap.Trap trap = new nstuff.geogame.quests.phases.trap.Trap(difficulty,damage,exp);
        TrapTexts text = new TrapTexts(seeTrap,go,goText,disarm,disarmOk,disarmBad);
        ((TrapPhase)phaseDictionary.get(getId())).load(trap,text,phaseDictionary.get(nextPhase));

    }
}
