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
public class FinalStage extends ChestPhase {



    public void load(Reward reward,String message) {
        super.load(reward,message, null);

    }

    @Override
    public void draw(MainActivity activity) {
        ViewGroup view =prepareActivity(activity);
        TextView tv = new TextView(activity);
        tv.setText(message);
        view.addView(tv);

        Button btn = new Button(activity);
        btn.setText(activity.getResources().getString(R.string.finish));
        btn.setOnClickListener(this);
        view.addView(btn);
        resolve();
    }

    @Override
    public boolean hasNext() {
        return false;
    }


}
