package com.demodocebo.test.data.api.exceptions;

import com.demodocebo.test.R;

/**
 * Created by andrea on 16/01/2018.
 */

public class NetworkException extends RuntimeException {

    public enum Type {
        GENERIC,
        NO_NETWORK,
        TIMEOUT
    }

    private Type type;


    private NetworkException(Throwable cause, String message, Type type) {
        super(message, cause);
        this.type = type;
    }

    public static NetworkException generic(Throwable cause) {
        return new NetworkException(cause, "Generic network exception", Type.GENERIC);
    }

    public static NetworkException noNetwork(Throwable cause) {
        return new NetworkException(cause, "No network exception" , Type.NO_NETWORK);
    }

    public static NetworkException timeout(Throwable cause) {
        return new NetworkException(cause, "Network timeout exception", Type.TIMEOUT);
    }

    public int getErrorString() {
        switch (type) {
            case NO_NETWORK:
                return R.string.error_network_no_network;
            case TIMEOUT:
                return R.string.error_network_server_timeout;
            default:
                return R.string.error_network_undefined;
        }
    }

    public Type getType() {
        return type;
    }
}