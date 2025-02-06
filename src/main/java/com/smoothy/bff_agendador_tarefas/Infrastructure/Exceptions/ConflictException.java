package com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException (String mensagem){
        super(mensagem);
    }
    public ConflictException (String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
