package com.simpledotatrade.exceptions;

public class SuchUserExistsException extends Exception {

    public SuchUserExistsException() {
    }

    public SuchUserExistsException(String arg0) {
        super(arg0);
    }

    public SuchUserExistsException(Throwable arg0) {
        super(arg0);
    }

    public SuchUserExistsException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SuchUserExistsException(String arg0, Throwable arg1, boolean arg2,
                                   boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
