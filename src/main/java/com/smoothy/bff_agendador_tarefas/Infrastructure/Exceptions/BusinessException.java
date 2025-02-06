package com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message , throwable);
    }
}
