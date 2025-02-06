package com.smoothy.bff_agendador_tarefas.Infrastructure.Client.config;

import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.BusinessException;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.ConflictException;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.ResourceNotFoundException;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()){
            case 409:
                return new ConflictException("[409] Erro atrbuto ja existente");
            case 403:
                return new ResourceNotFoundException("[403] Erro atributo nao encontrado");
            case 401:
                return new UnauthorizedException("[401] Erro usuario nao autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }

}
