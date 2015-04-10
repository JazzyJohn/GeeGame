package nstuff.geogame.questparse.models;

import java.util.Dictionary;

import nstuff.geogame.character.*;
import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.BattlePhase;
import nstuff.geogame.quests.phases.battle.BattleTexts;
import nstuff.geogame.quests.phases.battle.Enemy;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Battle  extends Phase{

    private int nextPhase;

    private int hp;

    private int meleeWeak;

    private int rangeWeak;

    private int ac;

    private  int attack;

    private  int damage;

    private String name;

    private  String seeEnemy;

    private String playerHit;

    private  String playerMiss;

    private String enemyHit;

    private String enemyMiss;

    private String enemyKill;

    public int getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(int nextPhase) {
        this.nextPhase = nextPhase;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMeleeWeak() {
        return meleeWeak;
    }

    public void setMeleeWeak(int meleeWeak) {
        this.meleeWeak = meleeWeak;
    }

    public int getRangeWeak() {
        return rangeWeak;
    }

    public void setRangeWeak(int rangeWeak) {
        this.rangeWeak = rangeWeak;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeeEnemy() {
        return seeEnemy;
    }

    public void setSeeEnemy(String seeEnemy) {
        this.seeEnemy = seeEnemy;
    }

    public String getPlayerHit() {
        return playerHit;
    }

    public void setPlayerHit(String playerHit) {
        this.playerHit = playerHit;
    }

    public String getPlayerMiss() {
        return playerMiss;
    }

    public void setPlayerMiss(String playerMiss) {
        this.playerMiss = playerMiss;
    }

    public String getEnemyHit() {
        return enemyHit;
    }

    public void setEnemyHit(String enemyHit) {
        this.enemyHit = enemyHit;
    }

    public String getEnemyMiss() {
        return enemyMiss;
    }

    public void setEnemyMiss(String enemyMiss) {
        this.enemyMiss = enemyMiss;
    }

    public String getEnemyKill() {
        return enemyKill;
    }

    public void setEnemyKill(String enemyKill) {
        this.enemyKill = enemyKill;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items) {
        BattleTexts battleTexts =new BattleTexts(seeEnemy,playerHit,playerMiss,enemyHit,enemyMiss,enemyKill);
        Enemy enemy = new Enemy(hp,meleeWeak,rangeWeak,ac,name,attack,damage);
        ((BattlePhase)phaseDictionary.get(getId())).load(enemy,phaseDictionary.get(nextPhase),battleTexts);
    }
}
