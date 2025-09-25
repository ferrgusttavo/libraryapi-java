package br.com.ferrgusttavo.libraryapp.dto.exemplarLivro;

import java.util.UUID;

public record QuantidadeExemplaresDto(
        UUID livroId,
        String titulo,
        long total,
        long disponivel,
        long emprestado,
        long reservado
) {
}
