package br.com.ferrgusttavo.libraryapp.dto.livro;

import java.util.List;
import java.util.UUID;

public record PatchLivroDto(
        String titulo,
        String descricao,
        Integer qtdePaginas,
        List<UUID> autorId
) { }
