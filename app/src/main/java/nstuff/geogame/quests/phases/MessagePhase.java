package nstuff.geogame.quests.phases;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.quests.phases.dialog.DialogOption;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class MessagePhase extends BasePhase implements View.OnClickListener {
    private String message;

    public void load(String startText, Phase nextPhase){
        this.nextPhase = nextPhase;
        this.message =startText;
    }
    @Override
    public void draw(MainActivity activity) {
        ViewGroup view =prepareActivity(activity);
        TextView tv = new TextView(activity);
        tv.setText(message);
        view.addView(tv);

        Button btn = new Button(activity);
        btn.setText(activity.getResources().getString(R.string.next));
        btn.setOnClickListener(this);
        view.addView(btn);

    }

    @Override
    public void onClick(View v) {
        quest.nextPhase();
    }
}
