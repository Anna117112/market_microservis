package com.geekbrains.spring_data1.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    // позволяет задать сообщение
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
