package nstuff.geogame.quests.phases;

import android.view.ViewGroup;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.quests.Quest;

/**
 * Created by vania_000 on 25.03.2015.
 */
public abstract class BasePhase implements Phase {

    protected Phase nextPhase;
    protected Quest quest;
    @Override
    public void init(Quest quest) {
        this.quest = quest;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Phase getNextPhase() {
        return nextPhase;
    }

    @Override
    public Quest getQuest() {
        return quest;
    }

    protected ViewGroup prepareActivity(MainActivity activity){
        ViewGroup view = (ViewGroup) activity.findViewById(R.id.LinerLayout);
        view.removeAllViews();
        return view;
    }
}
