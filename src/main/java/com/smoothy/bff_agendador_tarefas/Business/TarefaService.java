package com.smoothy.bff_agendador_tarefas.Business;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.smoothy.bff_agendador_tarefas.Business.Enums.StatusNotificacao;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TarefasDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Client.TarefasClient;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.ConflictException;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse salvarTarefas(String token, TarefasDTORequest dto){

        try{
            log.info("A tarefa foi salva para o usuario de token: " +token);
            return tarefasClient.salvarTarefas(dto , token);
        } catch (ConflictException e){
            log.info("Ocorreu algum erro ao salvar a tarefa");
            throw new ConflictException("Ocorreu um erro ao salvar a tarefa");

        }

    }

    public List<TarefasDTOResponse> buscaTarefaAgendadaPeriodo(LocalDateTime dataInicial,
                                                               LocalDateTime dataFinal,
                                                               String token) {
        try{
            log.info("Tarefa sendo procurada no periodo: " + dataInicial + " | " + dataFinal);
            return tarefasClient.buscaTarefaAgendadaPeriodo(dataInicial , dataFinal , token);
        } catch (ConflictException e){
            log.info("Ocorreu algum erro ao buscar a tarefa");
            throw new ConflictException("Ocorreu um erro para buscar a tarefa " + e.getCause());
        }

    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String email, String token) {
        try {
            log.info("O Usuario realizou buscas de tarefas pelo email");
            return tarefasClient.buscaTarefasPorEmail(email, token);
        } catch (ResourceNotFoundException e){
            log.info("Nao foi encontradas tarefas ou usuarios");
            throw new ResourceNotFoundException("Nao foi encontrado o usuario ou, nao foram encontradas tarefas nesse email");
        }
    }

    public void deletaPorId(String Id , String token){

        try{
            log.info("Tarefa de " + Id + " excluida com sucesso");
            tarefasClient.deletaPorId(Id , token);
        } catch (ResourceNotFoundException e) {
            log.info("Nao foram encontradas tarefas com esse ID");
            throw new ResourceNotFoundException("Nao foram encontradas tarefas com esse ID");
        }

    }

    public TarefasDTOResponse alteraStatus(StatusNotificacao status, String Id , String token){
        try{
            return tarefasClient.alteraStatusNotificacao(status, Id , token);
        } catch (ConflictException e) {
            throw new ConflictException("Ocorreu um erro na alteracao do Status " +e.getCause());
        }
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String Id , String token){

        return tarefasClient.updateTarefas(dto , Id , token);
    }

}
