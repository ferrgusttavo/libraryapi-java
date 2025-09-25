package br.com.ferrgusttavo.libraryapp.dto.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateLivroDto(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @Positive Integer qtdePaginas,
        @NotNull
        @Size(min = 1)
        List<UUID> autorId
) { }
