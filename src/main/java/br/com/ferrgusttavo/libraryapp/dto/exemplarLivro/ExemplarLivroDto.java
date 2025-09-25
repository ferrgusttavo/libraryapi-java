package br.com.ferrgusttavo.libraryapp.dto.exemplarLivro;

import br.com.ferrgusttavo.libraryapp.model.ExemplarLivro;
import br.com.ferrgusttavo.libraryapp.model.StatusConservacao;
import br.com.ferrgusttavo.libraryapp.model.StatusLivro;

import java.util.UUID;

public record ExemplarLivroDto(
        UUID id,
        StatusConservacao statusConservacao,
        StatusLivro statusLivro
) {
    public ExemplarLivroDto(ExemplarLivro exemplarLivro) {
        this(
                exemplarLivro.getId(),
                exemplarLivro.getStatusConservacao(),
                exemplarLivro.getStatusLivro()
        );
    }
}
