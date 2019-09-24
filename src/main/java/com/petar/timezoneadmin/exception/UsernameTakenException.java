package com.petar.timezoneadmin.exception;

public class UsernameTakenException extends Exception {
    public UsernameTakenException() {
        super("The provided username is already taken");
    }
}