package nstuff.geogame;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nstuff.geogame.character.CharacterManager;
import nstuff.geogame.character.Item;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.logic.Main;


public class ProfileWindow extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_window);
        CharacterManager manager =Main.getInstance().getInstance(CharacterManager.class);
        QuestCharacter character = manager.getCharacter();
        ((TextView) findViewById(R.id.playerHp)).setText(character.getHp() + "");
        ((TextView)findViewById(R.id.playerExp)).setText(character.getExp()+"");
        ((TextView)findViewById(R.id.playerGold)).setText(character.getGold()+"");
        List<Item> itemList =character.getItems();
        ViewGroup items = (ViewGroup) findViewById(R.id.items);
        LayoutInflater inflater =
                (LayoutInflater)this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        for(Item item : itemList){
            View view = inflater.inflate(R.layout.oneitem,null);
            ((TextView)view.findViewById(R.id.itemName)).setText(item.getName());
            ((TextView)view.findViewById(R.id.itemDescr)).setText(item.getDescription());
            ((TextView)view.findViewById(R.id.itemSkill)).setText(item.getSkillDescr());
            items.addView(view);
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
