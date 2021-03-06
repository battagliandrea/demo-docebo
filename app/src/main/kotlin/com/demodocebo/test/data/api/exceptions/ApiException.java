package com.demodocebo.test.data.api.exceptions;


import com.demodocebo.test.R;

/**
 * Created by andrea on 16/01/2018.
 */

public class ApiException extends RuntimeException {

    private int code;
    private String body;

    private ApiException(int code, String body) {
        this.code = code;
        this.body = body;
    }

    private ApiException(String message, Throwable cause, int code, String body) {
        super(message, cause);
        this.code = code;
        this.body = body;
    }

    public static ApiException create(int httpStatus, String body) {
        return new ApiException(httpStatus, body);
    }

    public static ApiException create(String message, Throwable cause, int httpStatus, String body) {
        return new ApiException(message, cause, httpStatus, body);
    }

    public int getErrorString() {
        switch (code){
            case 400:
                return R.string.error_api_400;
            case 401:
                return R.string.error_api_401;
            case 403:
                return R.string.error_api_403;
            case 404:
                return R.string.error_api_404;
            case 406:
                return R.string.error_api_406;
            case 500:
                return R.string.error_api_500;
            default:
                return R.string.error_undefined;
        }
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}
