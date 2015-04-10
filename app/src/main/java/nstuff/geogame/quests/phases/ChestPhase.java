package nstuff.geogame.quests.phases;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.character.Item;
import nstuff.geogame.quests.phases.reward.Reward;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class ChestPhase extends BasePhase implements View.OnClickListener {

    private Reward reward;
    protected String message;

    public void load( Reward reward,String message,Phase nextPhase) {
        this.reward = reward;
        this.message = message;
        this.nextPhase = nextPhase;
        reward.setPhase(this);
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
        resolve();
    }
    protected void resolve(){
        reward.resolved(quest.getCharacter());
    }

    @Override
    public void onClick(View v) {

        quest.nextPhase();
    }
}
