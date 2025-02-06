package com.smoothy.bff_agendador_tarefas.Business;

import com.smoothy.bff_agendador_tarefas.Business.Enums.StatusNotificacao;
import com.smoothy.bff_agendador_tarefas.Business.dto.in.LoginDTORequest;
import com.smoothy.bff_agendador_tarefas.Business.dto.out.TarefasDTOResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${super.user.email}")
    private String email;

    @Value("${super.user.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasDTOResponse> listaTarefas = tarefaService.buscaTarefaAgendadaPeriodo(horaFutura , horaFuturaMaisCinco , token);
        log.info("Tarefas encontradas " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.getEmailUsuario());
            tarefaService.alteraStatus(StatusNotificacao.NOTIFICADO, tarefa.getId(),
                    token);
            log.info("As tarefas: " + listaTarefas + " alteraram o status de PENDENTE para NOTIFICADO ");
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
