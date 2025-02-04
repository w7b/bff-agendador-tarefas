package com.smoothy.bff_agendador_tarefas.Business.dto.in;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTORequest {

    private String ddd;
    private String numero;
}
