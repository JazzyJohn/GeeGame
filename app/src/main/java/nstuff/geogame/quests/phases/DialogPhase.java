package nstuff.geogame.quests.phases;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.quests.Quest;
import nstuff.geogame.quests.phases.dialog.DialogOption;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class DialogPhase extends BasePhase {

    private List<DialogOption> options;

    private String startText;

    public void load(String startText, List<DialogOption> options) {
        this.startText = startText;
        this.options = options;
        for(DialogOption option : options){
            option.init(this);
        }
    }

    @Override
    public void draw(MainActivity activity) {
        ViewGroup view =prepareActivity(activity);
        TextView tv = new TextView(activity);
        tv.setText(startText);
        view.addView(tv);
        for(DialogOption option : options){
            Button btn = new Button(activity);
            btn.setText( option.toString());
            btn.setOnClickListener(option.getAction());
            view.addView(btn);
        }
    }


    public void answer(Phase nextPhase) {
        this.nextPhase = nextPhase;
        quest.nextPhase();
    }
}
