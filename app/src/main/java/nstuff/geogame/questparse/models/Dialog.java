package nstuff.geogame.questparse.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import nstuff.geogame.character.*;
import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.DialogPhase;
import nstuff.geogame.quests.phases.dialog.BaseDialogOption;
import nstuff.geogame.quests.phases.dialog.DialogOption;
import nstuff.geogame.quests.phases.dialog.SkillDialogOption;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Dialog extends Phase{
    
    private String text;

    private List<DialogPart> dialogParts;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DialogPart> getDialogParts() {
        return dialogParts;
    }

    public void setDialogParts(List<DialogPart> dialogParts) {
        this.dialogParts = dialogParts;
    }

    @Override
    public void releaseData(Dictionary<Integer, nstuff.geogame.quests.phases.Phase> phaseDictionary, Dictionary<Integer, Item> items) {
        List<DialogOption> options = new ArrayList<>();
        for(DialogPart part : dialogParts){
            if(part.getDifficulty()!=0){
                SkillDialogOption option = new SkillDialogOption();
                option.load(part.getName(),phaseDictionary.get(part.getNextPhase()),phaseDictionary.get(part.getBadPhase()),part.getDifficulty());
                options.add(option);
            }else{
                BaseDialogOption option = new BaseDialogOption();
                option.load(part.getName(),phaseDictionary.get(part.getNextPhase()));
                options.add(option);
            }
            ((DialogPhase) phaseDictionary.get(getId())).load(text,options);
        }
    }
}
