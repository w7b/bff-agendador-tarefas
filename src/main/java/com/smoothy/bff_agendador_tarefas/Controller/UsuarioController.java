package com.smoothy.bff_agendador_tarefas.Controller;

import com.smoothy.bff_agendador_tarefas.Business.UsuarioService;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.EnderecoDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.LoginDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TelefoneDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.UsuarioDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.EnderecoDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TelefoneDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Buscar dados de usuarios via email",
            description = "Buscar dados via email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario nao cadastrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @PostMapping
    @Operation(summary = "Salvar usuarios", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Falta informacoes")
    @ApiResponse(responseCode = "409", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){

        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios", description = "login de usuarios")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Falta informacoes")
    @ApiResponse(responseCode = "500", description = "Server error")
    public String login(@RequestBody LoginDTORequest usuarioDTO){

        return usuarioService.loginUsuario(usuarioDTO);
    }

    //                 Cadastra novos Enderecos & Telefones
    @PostMapping("/endereco")
    @Operation(summary = "Salva endereco para o usuario",
               description = "Salva um novo endereco para o usuario")
    @ApiResponse(responseCode = "200", description = "Endereco adicionado com sucesso")
    @ApiResponse(responseCode = "403", description = "Falta informacoes")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    //                 Deleta Usuario por Email
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuarios via ID", description = "Deleta usuarios por ID")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(value = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva um endereco para o usuario",
               description = "Salva um novo endereco para o usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    //                 Atualiza Usuario, Endereco & Telefone
    @PutMapping
    @Operation(summary = "Atualiza usuario",
               description = "Atualiza dados de usuarios")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UsuarioDTOResponse> attUser(@RequestBody UsuarioDTORequest dto,
                                                      @RequestHeader(value = "Authorization", required = false) String token) {
            return ResponseEntity.ok(usuarioService.atualizaUsuarios(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereco",
               description = "Atualiza endereco de usuarios")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<EnderecoDTOResponse> attEndereco(@RequestBody EnderecoDTORequest dto,
                                                           @RequestParam("id") Long id,
                                                           @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone",
               description = "Atualiza dados de telefone do usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TelefoneDTOResponse> attTelefone(@RequestBody TelefoneDTORequest dto,
                                                           @RequestParam("id") Long id,
                                                           @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

}

