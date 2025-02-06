package com.smoothy.bff_agendador_tarefas.Infrastructure.Client;

import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "notificacao", url = "${servers.notificacao.url}" )
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);

}
