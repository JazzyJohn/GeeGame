package nstuff.geogame.questparse.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import nstuff.geogame.character.*;
import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.*;
import nstuff.geogame.quests.phases.Phase;
import nstuff.geogame.quests.phases.reward.Reward;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class Finish extends Chest {

    @Override
    public void releaseData(Dictionary<Integer, Phase> phaseDictionary, Dictionary<Integer, Item> itemsDic) {
        List<Item> itemList = new ArrayList<>();
        for(Integer id: getItems()){
            itemList.add(itemsDic.get(id));
        }
        Reward reward =new Reward(itemList,getMoney(),getSkill(),getExp());

        ((FinalStage) phaseDictionary.get(getId())).load(reward, getMessage());
    }
}
