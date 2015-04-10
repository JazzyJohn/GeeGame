package nstuff.geogame.quests.phases;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.character.skills.SkillType;
import nstuff.geogame.quests.phases.battle.BattleTexts;
import nstuff.geogame.quests.phases.battle.DamageType;
import nstuff.geogame.quests.phases.battle.Enemy;
import nstuff.geogame.quests.phases.battle.InBattlePhase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class BattlePhase extends BasePhase {
    private Enemy enemy;

    private BattleTexts texts;

    private String curText;

    private InBattlePhase inBattlePhase;

    public void load(Enemy enemy, Phase nextPhase,BattleTexts texts){
        this.enemy = enemy;
        enemy.setPhase(this);
        this.nextPhase = nextPhase;
        this.texts = texts;
        this.curText = texts.seeEnemy;
        inBattlePhase = InBattlePhase.Attack;
    }

    @Override
    public void draw(MainActivity activity) {
        ViewGroup viewGroup=prepareActivity(activity);
        TextView tv = new TextView(activity);
        tv.setText(enemy.toString());
        viewGroup.addView(tv);
        tv = new TextView(activity);
        tv.setText(curText);
        viewGroup.addView(tv);
        switch (inBattlePhase){
            case Attack:
            {
                Button btn = new Button(activity);
                btn.setText(activity.getResources().getString(R.string.melee));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        meleeAttack();
                    }
                });
                viewGroup.addView(btn);
                btn = new Button(activity);
                btn.setText(activity.getResources().getString(R.string.range));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rangeAttack();
                    }
                });
                viewGroup.addView(btn);
            }
            break;
            case Defence:
            {
                Button btn = new Button(activity);
                btn.setText(activity.getResources().getString(R.string.next));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        defence();
                    }
                });
                viewGroup.addView(btn);
            }
            break;
            case End:
            {
                Button btn = new Button(activity);
                btn.setText(activity.getResources().getString(R.string.next));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quest.nextPhase();
                    }
                });
                viewGroup.addView(btn);
            }
            break;
        }
    }

    void meleeAttack(){
        if(enemy.damage(DamageType.Melee,quest.getCharacter().getSkill(SkillType.Melee))){
            if(enemy.isDead()){
                inBattlePhase = InBattlePhase.End;
                curText = texts.enemyKill;
            }else{
                inBattlePhase = InBattlePhase.Defence;
                curText = texts.playerHit;
            }
        }else{
            inBattlePhase = InBattlePhase.Defence;
            curText = texts.playerMiss;
        }
        quest.redrawPhase();
    }
    void rangeAttack(){
        if(enemy.damage(DamageType.Range,quest.getCharacter().getSkill(SkillType.Range))){
            if(enemy.isDead()){
                inBattlePhase = InBattlePhase.End;
                curText = texts.enemyKill;
            }else{
                inBattlePhase = InBattlePhase.Defence;
                curText = texts.playerHit;
            }
        }else{
            inBattlePhase = InBattlePhase.Defence;
            curText = texts.playerMiss;
        }
        quest.redrawPhase();
    }
    void defence(){
        int damage =enemy.attackAC(quest.getCharacter().getSkill(SkillType.AC));
        if(damage==0){

            curText = texts.enemyMiss;
        }else{

            curText = String.format(texts.enemyHit,damage);
            quest.getCharacter().lowerHP(damage);
        }
        inBattlePhase = InBattlePhase.Attack;
        quest.redrawPhase();
    }
}
