package nstuff.geogame.quests.phases;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;
import nstuff.geogame.quests.phases.trap.Trap;
import nstuff.geogame.quests.phases.trap.TrapTexts;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class TrapPhase extends BasePhase {
    private Trap trap;

    private String message;

    private TrapTexts texts;

    private boolean resoled= false;

    public void load(Trap trap, TrapTexts texts,Phase nextPhase) {
        this.trap =trap;
        trap.setPhase(this);
        this.texts = texts;
        this.nextPhase = nextPhase;
        this.message = texts.seeTrap;
    }

    @Override
    public void draw(MainActivity activity) {
        ViewGroup viewGroup=prepareActivity(activity);
        TextView tv = new TextView(activity);
        tv.setText(message);
        viewGroup.addView(tv);
        if(!resoled){
            Button btn = new Button(activity);
            btn.setText(texts.go);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noDisarm();
                }
            });
            viewGroup.addView(btn);
            btn = new Button(activity);
            btn.setText(texts.disarm);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   disarm();
                }
            });
            viewGroup.addView(btn);
        }else{
            Button btn = new Button(activity);
            btn.setText(activity.getResources().getString(R.string.next));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quest.nextPhase();
                }
            });
            viewGroup.addView(btn);
        }
    }

    private void noDisarm() {
        trap.damage(quest.getCharacter());
        message = texts.goText;
        resoled= true;
        quest.redrawPhase();

    }

    private void disarm() {
        if(trap.disarm(quest.getCharacter())){
            message = texts.disarmBad;
        }else{
            message = texts.disarmOk;
        }
        resoled= true;
        quest.redrawPhase();
    }
}
