package nstuff.geogame.logic;

import android.location.LocationListener;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.Random;

import nstuff.geogame.character.CharacterManager;
import nstuff.geogame.character.CharacterManagerInMemory;
import nstuff.geogame.character.LocationListenerImpl;
import nstuff.geogame.character.QuestCharacter;
import nstuff.geogame.connection.Connection;

import nstuff.geogame.connection.RequestGenerator;
import nstuff.geogame.connection.SimpleRequestGenerator;
import nstuff.geogame.questparse.Parser;
import nstuff.geogame.quests.QuestManager;
import nstuff.geogame.quests.QuestManagerMemory;
import nstuff.geogame.quests.QuestManagerRemote;

/**
 * Created by vania_000 on 19.03.2015.
 */
public class Main {

    public static final int BASE_AC = 10;
    public static final int DICE_SIZE = 20;

    private static Main instance;
    Injector injector;

    public Main() {
        injector = Guice.createInjector(new MainModule());
        Connection connection =injector.getInstance(Connection.class);
        connection.init();


    }

    public static Main getInstance(){
        if(instance==null){
            instance = new Main();
        }
        return instance;
    }

    private class MainModule extends AbstractModule{

        @Override
        protected void configure() {
            bind(Connection.class).in(Singleton.class);
            bind(QuestManager.class).to(QuestManagerRemote.class).in(Singleton.class);
            bind(CharacterManager.class).to(CharacterManagerInMemory.class).in(Singleton.class);
            bind(Random.class).in(Singleton.class);
            bind(RequestGenerator.class).to(SimpleRequestGenerator.class).in(Singleton.class);
            bind(Parser.class).in(Singleton.class);
            bind(LocationListenerImpl.class).in(Singleton.class);
        }
    }
    public <T> T getInstance(Class name){
        return (T) injector.getInstance(name);
    }
}
