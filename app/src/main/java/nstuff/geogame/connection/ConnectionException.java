package nstuff.geogame.connection;

import com.google.inject.internal.ErrorsException;

/**
 * Created by vania_000 on 18.03.2015.
 */
public class ConnectionException extends Exception {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
