package nstuff.geogame.connection;


import org.json.JSONObject;

/**
 * Created by vania_000 on 18.03.2015.
 */
public interface AsyncCommand {

    void resolvedAnswer(String response);

    void resolvedError();
}
