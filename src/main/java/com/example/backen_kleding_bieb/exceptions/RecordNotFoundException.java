package com.example.backen_kleding_bieb.exceptions;


public class RecordNotFoundException extends RuntimeException {


    public RecordNotFoundException() {
        super( "Not found");
    }
    public RecordNotFoundException(String message) {
        super(message);
    }


}

