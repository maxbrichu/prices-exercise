package com.example.pricesexercise.core.domain;

public class PriceException extends Exception {
    private String message;
    public PriceException(String message){
        this.message = message;
    }
}
