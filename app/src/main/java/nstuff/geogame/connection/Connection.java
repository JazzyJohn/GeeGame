package nstuff.geogame.connection;

import com.google.inject.Inject;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;


/**
 * Created by vania_000 on 18.03.2015.
 */
public class Connection {

    private static final String HOST ="192.168.1.102";

    private static final int PORT =9991;

    private Socket socket;

    private BufferedReader br;

    private PrintWriter out;



    private volatile boolean work = true;

    //private volatile Queue<Request> queue = new <>();

    public void init(){
        Thread thread =(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(HOST, PORT);
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void resolveError(Request request) throws ConnectionException {
        try{


            request.result.resolvedError();
        }catch (Exception e){
            throw new ConnectionException(e);
        }
    }

    private void resolve(Request request, String answer) throws ConnectionException {

            request.result.resolvedAnswer(answer);

    }


    public void sendRequest(final JSONObject requestObj, final AsyncCommand command){
        (new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request(requestObj,command);
                String jsonString = request.inMessage.toString();
                if (jsonString != null) {
                    out.println(jsonString);
                    out.flush();
                    String answer = null;
                    try {
                        answer = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        resolve(request, answer);
                    } catch (ConnectionException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        resolveError(request);
                    } catch (ConnectionException e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();

    }

    private class Request{
        private JSONObject inMessage;

        private AsyncCommand result;

        private Request(JSONObject inMessage, AsyncCommand result) {
            this.inMessage = inMessage;
            this.result = result;
        }


    }
}
