package com.smoothy.bff_agendador_tarefas.Business;



import com.smoothy.bff_agendador_tarefas.Business.dto.in.EnderecoDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.LoginDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.TelefoneDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.UsuarioDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.EnderecoDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TelefoneDTOResponse;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.UsuarioDTOResponse;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Client.UsuarioClient;
import com.smoothy.bff_agendador_tarefas.Infrastructure.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest dto){
            return client.login(dto);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String token){
            return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
            client.deletaUsuarioPorEmail(email , token);
    }

    public UsuarioDTOResponse atualizaUsuarios(String token, UsuarioDTORequest dto){
            return client.attUser(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
            return client.attEndereco(enderecoDTO , idEndereco , token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
            return client.attTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
            return client.cadastraEndereco(dto , token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
            return client.cadastraTelefone(dto , token);
    }

}

