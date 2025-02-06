package com.smoothy.bff_agendador_tarefas.Business.dto.out;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTOResponse {
    private Long id;
    private String numero;
    private String ddd;

}
