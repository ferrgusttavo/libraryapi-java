package br.com.ferrgusttavo.libraryapp.dto.exemplarLivro;

import br.com.ferrgusttavo.libraryapp.model.StatusConservacao;
import br.com.ferrgusttavo.libraryapp.model.StatusLivro;
import jakarta.validation.constraints.NotNull;

public record CreateExemplarLivroDto(
        @NotNull StatusConservacao statusConservacao,
        @NotNull StatusLivro statusLivro
) {
}
