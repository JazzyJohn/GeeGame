package nstuff.geogame.connection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vania_000 on 04.04.2015.
 */
public class SimpleRequestGenerator implements  RequestGenerator{
    private  JSONObject genrateBaseObj() throws JSONException {

        JSONObject object= new JSONObject();
        object.put("jsonrpc","2.0");
        object.put("id","1");
        return object;
    }

    @Override
    public JSONObject forQuestRequest(int id) {
        try {
            JSONObject object= genrateBaseObj();
            object.put("method","getQuest");
            object.put("params",id);
            return object;
        } catch (JSONException e) {
            return null;
        }


    }
}
