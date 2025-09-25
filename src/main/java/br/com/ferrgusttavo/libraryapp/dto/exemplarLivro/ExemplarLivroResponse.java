package br.com.ferrgusttavo.libraryapp.dto.exemplarLivro;

import br.com.ferrgusttavo.libraryapp.dto.livro.LivroDto;

import java.util.List;

public record ExemplarLivroResponse(
        LivroDto livro,
        List<ExemplarLivroDto> exemplares
) {
}
