package br.com.ferrgusttavo.libraryapp.dto;

import java.util.List;
import java.util.UUID;

public record LivroPatchDto(
        String titulo,
        String descricao,
        Integer qtdePaginas,
        List<UUID> autorId
) { }
