package br.com.ferrgusttavo.libraryapp.dto.exemplarLivro;

import br.com.ferrgusttavo.libraryapp.model.StatusConservacao;
import br.com.ferrgusttavo.libraryapp.model.StatusLivro;

public record PatchExemplarLivroDto(
        StatusConservacao statusConservacao,
        StatusLivro statusLivro
) {}