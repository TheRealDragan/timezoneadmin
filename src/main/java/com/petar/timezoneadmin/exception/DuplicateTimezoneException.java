package com.petar.timezoneadmin.exception;

public class DuplicateTimezoneException extends Exception {
    public DuplicateTimezoneException() {
        super("The inserted timezone already exists");
    }
}