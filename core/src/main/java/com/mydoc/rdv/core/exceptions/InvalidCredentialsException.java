package com.mydoc.rdv.core.exceptions;

import org.springframework.util.StringUtils;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(String email) {
        super("for email " + StringUtils.quote(email));
    }
}
