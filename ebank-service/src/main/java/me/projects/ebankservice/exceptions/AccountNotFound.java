package me.projects.ebankservice.exceptions;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound() {
        super("Account not found!!");
    }
}

