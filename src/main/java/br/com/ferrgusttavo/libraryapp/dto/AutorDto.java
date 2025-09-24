package br.com.ferrgusttavo.libraryapp.dto;

import br.com.ferrgusttavo.libraryapp.model.Autor;
import br.com.ferrgusttavo.libraryapp.model.Livro;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record AutorDto(
        UUID id,
        @NotBlank String nome
) {
    public AutorDto (Autor autor) {
        this(
                autor.getId(),
                autor.getNome()
        );
    }
}
