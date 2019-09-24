package com.petar.timezoneadmin.exception;

public class TimezoneNotFoundException extends Exception {
    public TimezoneNotFoundException() {
        super("The requested record has not been found");
    }
}
