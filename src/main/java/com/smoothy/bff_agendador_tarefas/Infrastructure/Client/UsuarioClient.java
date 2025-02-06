package com.smoothy.bff_agendador_tarefas.Infrastructure.Client;

import com.smoothy.bff_agendador_tarefas.Business.dto.in.EnderecoDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.LoginDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TelefoneDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.UsuarioDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.EnderecoDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TelefoneDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${servers.usuario.url}" )
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    Void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse attUser(@RequestBody UsuarioDTORequest dto,
                               @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse attEndereco(@RequestBody EnderecoDTORequest dto,
                                    @RequestParam("id") Long id,
                                    @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse attTelefone(@RequestBody TelefoneDTORequest dto,
                                    @RequestParam("id") Long id,
                                    @RequestHeader("Authorization") String token);

}
