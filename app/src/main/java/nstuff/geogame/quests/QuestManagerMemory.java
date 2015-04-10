package nstuff.geogame.quests;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.*;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.character.CharacterManager;
import nstuff.geogame.character.Item;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.character.skills.SkillType;
import nstuff.geogame.quests.phases.BattlePhase;
import nstuff.geogame.quests.phases.CheckInPhase;
import nstuff.geogame.quests.phases.ChestPhase;
import nstuff.geogame.quests.phases.DialogPhase;
import nstuff.geogame.quests.phases.FinalStage;
import nstuff.geogame.quests.phases.MessagePhase;
import nstuff.geogame.quests.phases.Phase;
import nstuff.geogame.quests.phases.TrapPhase;
import nstuff.geogame.quests.phases.battle.BattleTexts;
import nstuff.geogame.quests.phases.battle.Enemy;
import nstuff.geogame.quests.phases.checkin.OnePlace;
import nstuff.geogame.quests.phases.dialog.BaseDialogOption;
import nstuff.geogame.quests.phases.dialog.DialogOption;
import nstuff.geogame.quests.phases.dialog.SkillDialogOption;
import nstuff.geogame.quests.phases.reward.Reward;
import nstuff.geogame.quests.phases.trap.Trap;
import nstuff.geogame.quests.phases.trap.TrapTexts;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class QuestManagerMemory  implements  QuestManager{

    private Quest currentQuest;

    @Inject
    private CharacterManager characterManager;

    @Override
    public void loadNewQuest(MainActivity activity) {
        MessagePhase welcome = new MessagePhase();
        CheckInPhase villageEnter =new CheckInPhase();
        DialogPhase village = new DialogPhase();
        BaseDialogOption villageYes = new BaseDialogOption();
        SkillDialogOption villageNo = new SkillDialogOption();
        BattlePhase zombie = new BattlePhase();
        MessagePhase villageNoGood = new MessagePhase();
        MessagePhase villageNoBad= new MessagePhase();
        MessagePhase villageYesMess = new MessagePhase();

        CheckInPhase pathSelect =new CheckInPhase();

        TrapPhase trapPhase =new TrapPhase();

        BattlePhase ork = new BattlePhase();
        ChestPhase orkReward = new ChestPhase();

        CheckInPhase dragonLair = new CheckInPhase();
        MessagePhase dragonSee = new MessagePhase();
        DialogPhase dragonRiddle = new DialogPhase();
        BaseDialogOption dragonRiddleOne = new BaseDialogOption();
        BaseDialogOption dragonRiddleTwo = new BaseDialogOption();
        BaseDialogOption dragonRiddleThree = new BaseDialogOption();
        MessagePhase dragonRightRiddle = new MessagePhase();
        FinalStage riddleFinnishStage = new FinalStage();

        BattlePhase dragonBattle = new BattlePhase();
        FinalStage dragonBattleStage = new FinalStage();


        welcome.load(activity.getResources().getString(R.string.welcome),villageEnter);



        List<OnePlace> places = new ArrayList<>();
        places.add(new OnePlace(village,"Деревня",59.951635f, 30.225358f));
        villageEnter.load(places);

        villageYes.load(activity.getResources().getString(R.string.villageYes),villageYesMess);
        villageNo.load(activity.getResources().getString(R.string.villageNo),villageNoGood,villageNoBad,10);
        List<DialogOption> options = new ArrayList<>();
        options.add(villageYes);
        options.add(villageNo);
        village.load(activity.getResources().getString(R.string.village),options);

        villageYesMess.load(activity.getResources().getString(R.string.villageYesMess),zombie);


        Enemy eZombie =new Enemy(1,5,5,5,"Зомби гнилохруст",0,0);
        BattleTexts battleTexts = new BattleTexts(
                activity.getResources().getString(R.string.zombieSee) ,
                "",
                "",
                "",
                "",
                activity.getResources().getString(R.string.zombieDead)
        );
        zombie.load(eZombie,pathSelect,battleTexts);

        villageNoGood.load(activity.getResources().getString(R.string.villageNoGood) ,pathSelect);
        villageNoBad.load(activity.getResources().getString(R.string.villageNoBad) ,zombie);



        places = new ArrayList<>();
        places.add(new OnePlace(trapPhase,"ущелье",59.954536f, 30.222654f));
        places.add(new OnePlace(ork,"лес",59.952322f, 30.216732f));
        pathSelect.load(places);
        Trap trap = new Trap(10,1,5);
        TrapTexts trapTexts = new TrapTexts(
                activity.getResources().getString(R.string.trapSee) ,
                activity.getResources().getString(R.string.trapGo) ,
                activity.getResources().getString(R.string.trapGoText) ,
                activity.getResources().getString(R.string.trapDisarm) ,
                activity.getResources().getString(R.string.trapDisarmOk) ,
                activity.getResources().getString(R.string.trapDisarmBad)
        );
        trapPhase.load(trap,trapTexts,dragonLair);

        Enemy eOrk =new Enemy(1,5,0,10,"орк-ампутатор",5,1);
        battleTexts = new BattleTexts(
                activity.getResources().getString(R.string.orkSee) ,
                "",
                activity.getResources().getString(R.string.orkPlayerMiss) ,
                activity.getResources().getString(R.string.orkHit) ,
                activity.getResources().getString(R.string.orkMiss) ,
                activity.getResources().getString(R.string.orkDead)
        );
        ork.load(eOrk,orkReward,battleTexts);
        List<Item> items = new ArrayList<>();
        items.add(new Item() {
            @Override
            public String getName() {
                return "Ржавый ятаган";
            }

            @Override
            public String getDescription() {
                return "Ятаган принадлежавший одному из орков северного леса. Хоть и ржавый,но все еще представляет опасность в умелых руках.";
            }

            @Override
            public int getSkill(SkillType skillType) {
               if(skillType==SkillType.Melee){
                   return 1;
               }else{
                   return 0;
               }
            }

            @Override
            public String getSkillDescr() {
                return "+1 к attack";
            }
        });
        Reward reward = new Reward(items,0,0,10);
        orkReward.load(reward, activity.getResources().getString(R.string.orkReward),dragonLair);


        places = new ArrayList<>();
        places.add(new OnePlace(dragonSee,"Убежище дракона.",59.952322f, 30.216732f));
        dragonLair.load(places);

        dragonSee.load(activity.getResources().getString(R.string.dragonSee),dragonRiddle);

        dragonRiddleOne.load(activity.getResources().getString(R.string.dragonRiddleOne),dragonRightRiddle);
        dragonRiddleTwo.load(activity.getResources().getString(R.string.dragonRiddleTwo),dragonBattle);
        dragonRiddleThree.load(activity.getResources().getString(R.string.dragonRiddleThree),dragonBattle);
        options = new ArrayList<>();
        options.add(dragonRiddleOne);
        options.add(dragonRiddleTwo);
        options.add(dragonRiddleThree);
        dragonRiddle.load(activity.getResources().getString(R.string.dragonRiddle),options);
        dragonRightRiddle.load(activity.getResources().getString(R.string.dragonRiddleRight),riddleFinnishStage);

        Enemy eDragon =new Enemy(2,0,5,15,"Поэтический дракон",5,1);
        battleTexts = new BattleTexts(
                activity.getResources().getString(R.string.dragonBattleSee) ,
                activity.getResources().getString(R.string.dragonBattlPlayerHit) ,
                activity.getResources().getString(R.string.dragonBattlPlayerMiss) ,
                activity.getResources().getString(R.string.dragonBattlHit) ,
                activity.getResources().getString(R.string.dragonBattlMiss) ,
                activity.getResources().getString(R.string.dragonBattlDead)
        );
        dragonBattle.load(eDragon,dragonBattleStage,battleTexts);

        items = new ArrayList<>();
        items.add(new Item() {
            @Override
            public String getName() {
                return "Доспехи дракона";
            }

            @Override
            public String getDescription() {
                return "Доспехи,которые дракон бережно хранил в своем сундуке.";
            }

            @Override
            public int getSkill(SkillType skillType) {
                if(skillType==SkillType.AC){
                    return 1;
                }else{
                    return 0;
                }
            }
            @Override
            public String getSkillDescr() {
                return "+1 к AC";
            }
        });
        reward = new Reward(items,100,0,10);
        dragonBattleStage.load(reward, activity.getResources().getString(R.string.dragonReward),dragonLair);
        reward = new Reward(items,100,0,20);
        riddleFinnishStage.load(reward, activity.getResources().getString(R.string.dragonReward),dragonLair);

        currentQuest = new QuestMemory(welcome);
        currentQuest.init(activity,characterManager.getCharacter());
        currentQuest.startPhase();
    }

    @Override
    public Quest currentQuest() {
        return currentQuest;
    }
}
