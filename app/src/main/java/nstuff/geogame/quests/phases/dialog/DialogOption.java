package nstuff.geogame.quests.phases.dialog;

import android.view.View;

import nstuff.geogame.quests.phases.DialogPhase;

/**
 * Created by vania_000 on 25.03.2015.
 */
public interface DialogOption {

    View.OnClickListener getAction();

    void init(DialogPhase dialog);

    String toString();
}
