package nstuff.geogame.quests.log;

import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import nstuff.geogame.MainActivity;
import nstuff.geogame.R;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class SimpleQuestLogger implements QuestLogger {

    private ScrollView scroll;

    private MainActivity activity;

    private ViewGroup view;

    @Override
    public void init(MainActivity activity) {
        this.activity =activity;
        scroll = (ScrollView)activity.findViewById(R.id.LogScroll);
        view = (ViewGroup)activity.findViewById(R.id.Log);

    }

    @Override
    public void addString(String text) {
        TextView tv = new TextView(activity);
        tv.setText(text);
        view.addView(tv);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void reset() {
        view.removeAllViews();
    }


}
