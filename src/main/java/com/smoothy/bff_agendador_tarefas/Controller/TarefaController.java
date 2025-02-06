package com.smoothy.bff_agendador_tarefas.Controller;

import com.smoothy.bff_agendador_tarefas.Business.Enums.StatusNotificacao;
import com.smoothy.bff_agendador_tarefas.Business.TarefaService;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TarefasDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salva Tarefas de Usuarios", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa criada com Sucesso!")
    @ApiResponse(responseCode = "500", description = "Server error!")
    public ResponseEntity<TarefasDTOResponse> salvarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization" , required = false) String token){

        return ResponseEntity.ok(tarefaService.salvarTarefas(token , dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefa",
               description = "Busca tarefa de um usuario por um determinado periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuario nao autorizado")
    @ApiResponse(responseCode = "500", description = "Server error!")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefaAgendadaPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.buscaTarefaAgendadaPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email",
            description = "Busca todas as tarefas que o usuario tem pelo seu email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuario nao autorizado")
    @ApiResponse(responseCode = "403", description = "Email nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error!")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(value = "Authorization" , required = false) String token){

        List<TarefasDTOResponse> tarefas = tarefaService.buscaTarefasPorEmail(email, token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID",
            description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuario nao autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa ID not found")
    @ApiResponse(responseCode = "500", description = "Server error!")
    public ResponseEntity<Void> deletaPorId(@RequestParam("Id") String Id,
                                            @RequestHeader(value = "Authorization", required = false) String token){

        tarefaService.deletaPorId(Id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status da tarefa",
            description = "Altera o status da Tarefa PENDENTE, NOTIFICADO, CANCELADO")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuario nao autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa ID not found")
    @ApiResponse(responseCode = "500", description = "Server error!")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                                      @RequestParam("id") String Id,
                                                                      @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.alteraStatus(status, Id, token));
    }

    @PutMapping
    @PatchMapping
    @Operation(summary = "Altera dados da tarefa",
            description = "Altera os dados das tarefas (Nome, Descricao, Data do evento). ")
    @ApiResponse(responseCode = "200", description = "[200] Tarefa altera")
    @ApiResponse(responseCode = "401", description = "[401] Usuario nao autorizado")
    @ApiResponse(responseCode = "403", description = "[403] Tarefa ID not found")
    @ApiResponse(responseCode = "500", description = "[500] Server error!")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String Id,
                                                            @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.updateTarefas(dto, Id, token));
    }

}
