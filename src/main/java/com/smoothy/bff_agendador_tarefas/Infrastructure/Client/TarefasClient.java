package com.smoothy.bff_agendador_tarefas.Infrastructure.Client;

import com.smoothy.bff_agendador_tarefas.Business.Enums.StatusNotificacao;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TarefasDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendar", url = "${servers.tarefas.url}" )
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse salvarTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader(value = "Authorization", required = false) String token);


    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaTarefaAgendadaPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader(value = "Authorization", required = false) String token, String email);

    @DeleteMapping
    Void deletaPorId(@RequestParam("Id") String Id,
                     @RequestHeader(value = "Authorization", required = false) String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                               @RequestParam("id") String Id,
                                               @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestParam("id") String Id,
                                     @RequestHeader(value = "Authorization", required = false) String token);

}
