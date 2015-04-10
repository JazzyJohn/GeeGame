package nstuff.geogame.questparse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import nstuff.geogame.questparse.models.Battle;
import nstuff.geogame.questparse.models.CheckIn;
import nstuff.geogame.questparse.models.Chest;
import nstuff.geogame.questparse.models.Dialog;
import nstuff.geogame.questparse.models.Finish;
import nstuff.geogame.questparse.models.Message;
import nstuff.geogame.questparse.models.Phase;
import nstuff.geogame.questparse.models.Quest;
import nstuff.geogame.questparse.models.Trap;


/**
 * Created by vania_000 on 04.04.2015.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Message.class, name = "message"),
        @JsonSubTypes.Type(value = Battle.class, name = "battle"),
        @JsonSubTypes.Type(value = CheckIn.class, name = "checkin"),
        @JsonSubTypes.Type(value = Chest.class, name = "chest"),
        @JsonSubTypes.Type(value = Finish.class, name = "finish"),
        @JsonSubTypes.Type(value = Trap.class, name = "trap"),
        @JsonSubTypes.Type(value = Dialog.class, name = "dialog")

})
abstract class PolymorphicPhaseMixIn{

}

public class Parser {

    ObjectMapper mapper;

    public Parser(){


        mapper  = new ObjectMapper();

        mapper.addMixIn(Phase.class,PolymorphicPhaseMixIn.class);


    }


    public Quest parseQuest(String data) throws IOException {
        Quest q = mapper.readValue(data, Quest.class);
        return q;
    }


}
