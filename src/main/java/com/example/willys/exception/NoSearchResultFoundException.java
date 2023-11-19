package com.example.willys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSearchResultFoundException extends RuntimeException {
    public NoSearchResultFoundException(String search) {
        super("No search result found for the search '" + search + "'");
    }
}
