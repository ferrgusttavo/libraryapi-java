package br.com.ferrgusttavo.libraryapp.dto.livro;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import br.com.ferrgusttavo.libraryapp.model.Livro;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record LivroDto(
        UUID id,
        String titulo,
        String descricao,
        Integer qtdePaginas,
        List<AutorDto> autores
) {
    public LivroDto (Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getDescricao(),
                livro.getQtdePaginas(),
                (livro.getAutores() != null)
                        ? livro.getAutores().stream()
                        .map(AutorDto::new)
                        .collect(Collectors.toList())
                        : List.of()
        );
    }
}
