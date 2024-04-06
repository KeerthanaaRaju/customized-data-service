package com.nielseniq.data.service.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class DataServiceException extends Exception
{
    @Getter
    @Setter
    private HttpStatus errorCode;

    public DataServiceException(String message, HttpStatus errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public DataServiceException(String message, Throwable e, HttpStatus errorCode)
    {
        super(message,e);
        this.errorCode = errorCode;
    }
}