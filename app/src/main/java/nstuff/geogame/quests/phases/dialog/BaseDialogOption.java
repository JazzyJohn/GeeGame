package nstuff.geogame.quests.phases.dialog;

import android.view.View;

import nstuff.geogame.quests.phases.DialogPhase;
import nstuff.geogame.quests.phases.Phase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class BaseDialogOption  implements DialogOption,View.OnClickListener{
    protected DialogPhase phase;

    protected Phase nextPhase;

    private String option;

    public void load(String option,Phase nextPhase) {
        this.option = option;
        this.nextPhase = nextPhase;
    }

    @Override
    public View.OnClickListener getAction() {
        return this;
    }

    @Override
    public void init(DialogPhase phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return option;
    }

    @Override
    public void onClick(View v) {
        phase.answer(nextPhase);
    }
}
