package com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions;


import org.apache.hc.client5.http.auth.AuthenticationException;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable throwable) {
    super(message , throwable);
  }
}
