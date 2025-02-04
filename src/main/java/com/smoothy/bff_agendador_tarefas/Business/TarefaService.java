package com.smoothy.bff_agendador_tarefas.Business;


import com.smoothy.bff_agendador_tarefas.Business.Enums.StatusNotificacao;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TarefasDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefaService {


    private final TarefasClient tarefasClient;

    public TarefasDTOResponse salvarTarefas(String token, TarefasDTORequest dto){

        return tarefasClient.salvarTarefas(dto , token);
    }

    public List<TarefasDTOResponse> buscaTarefaAgendadaPeriodo(LocalDateTime dataInicial,
                                                               LocalDateTime dataFinal,
                                                               String token) {

        return tarefasClient.buscaTarefaAgendadaPeriodo(dataInicial , dataFinal , token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token){

        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaPorId(String Id , String token){

        tarefasClient.deletaPorId(Id , token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacao status, String Id , String token){

        return tarefasClient.alteraStatusNotificacao(status, Id , token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String Id , String token){

        return tarefasClient.updateTarefas(dto , Id , token);
    }

}
