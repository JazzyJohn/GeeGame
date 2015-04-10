package nstuff.geogame.quests;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.character.CharacterManager;
import nstuff.geogame.character.Item;
import nstuff.geogame.character.SimpleItem;
import nstuff.geogame.character.skills.SkillBuff;
import nstuff.geogame.character.skills.SkillType;
import nstuff.geogame.connection.AsyncCommand;
import nstuff.geogame.connection.Connection;
import nstuff.geogame.connection.RequestGenerator;
import nstuff.geogame.questparse.Parser;
import nstuff.geogame.questparse.models.Battle;
import nstuff.geogame.questparse.models.CheckIn;
import nstuff.geogame.questparse.models.Chest;
import nstuff.geogame.questparse.models.Dialog;
import nstuff.geogame.questparse.models.Finish;
import nstuff.geogame.questparse.models.ItemSkill;
import nstuff.geogame.questparse.models.Message;
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
public class QuestManagerRemote implements  QuestManager{

    private Quest currentQuest;

    @Inject
    private CharacterManager characterManager;

    private MainActivity activity;

    @Inject
    private Parser parser;

    @Inject
    private Connection connection;

    @Inject
    private RequestGenerator generator;

    @Override
    public void loadNewQuest(MainActivity activity) {

        connection.sendRequest(generator.forQuestRequest(0),new AsyncCommand() {
            @Override
            public void resolvedAnswer(String response) {
                try {
                    endQuestResolving(parser.parseQuest(response));
                } catch (IOException e) {
                    showError();
                }
            }

            @Override
            public void resolvedError() {
                showError();
            }
        });
    }

    @Override
    public Quest currentQuest() {
        return currentQuest;
    }

    private void endQuestResolving(nstuff.geogame.questparse.models.Quest quest){
        Dictionary<Integer,Item> items = new Hashtable<>();
        for(nstuff.geogame.questparse.models.Item item : quest.getItems()){
            List<SkillBuff> buffs = new ArrayList<>();
            for(ItemSkill itemSkill : item.getSkills()){
                buffs.add(new SkillBuff(itemSkill.getSkill(),itemSkill.getValue()));
            }

            SimpleItem simpleItem = new SimpleItem(item.getName(),item.getDescription(),buffs,"");
            items.put(item.getId(),simpleItem);

        }
        Dictionary<Integer,Phase> phaseDictionary = new Hashtable<>();
        for(nstuff.geogame.questparse.models.Phase phase : quest.getPhases()){
            Phase inPhase = generatePhase(phase);
            if(inPhase==null){
                showError();
                return;
            }
            phaseDictionary.put(phase.getId(),inPhase);
        }
        for(nstuff.geogame.questparse.models.Phase phase : quest.getPhases()) {

            phase.releaseData(phaseDictionary,items);
        }
        currentQuest= new QuestMemory(phaseDictionary.get(0));
        currentQuest.init(activity,characterManager.getCharacter());
        currentQuest.startPhase();

    }

    private Phase generatePhase(nstuff.geogame.questparse.models.Phase  phase) {
        Phase answerPhase =null;
        if(phase instanceof Message){
            answerPhase= new MessagePhase();

        }
        if( phase instanceof Dialog){
            answerPhase= new DialogPhase();
        }
        if( phase instanceof Chest){
            answerPhase= new ChestPhase();
        }
        if( phase instanceof Battle){
            answerPhase= new BattlePhase();
        }
        if( phase instanceof CheckIn){
            answerPhase= new CheckInPhase();
        }
        if( phase instanceof Finish){
            answerPhase= new FinalStage();
        }
        if( phase instanceof nstuff.geogame.questparse.models.Trap){
            answerPhase= new TrapPhase();
        }
        return answerPhase;
    }

    private  void showError(){

    }
}
