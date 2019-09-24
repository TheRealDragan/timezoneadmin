package com.petar.timezoneadmin.exception;

public class TimezoneOwnershipChangeNotAllowedException extends Exception {
    public TimezoneOwnershipChangeNotAllowedException() {
        super("You are not allowed to change the ownership of this record");
    }
}
