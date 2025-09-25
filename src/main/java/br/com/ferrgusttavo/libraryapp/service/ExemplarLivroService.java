package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.exemplarLivro.*;

import java.util.UUID;

public interface ExemplarLivroService {
    ExemplarLivroDto findById(UUID id);
    ExemplarLivroResponse findAllByLivroId(UUID livroId);
    QuantidadeExemplaresDto contarExemplaresPorLivroId(UUID livroId);
    ExemplarLivroDto save(UUID livroId, CreateExemplarLivroDto createExemplarLivroDto);
    ExemplarLivroDto update(UUID id, PatchExemplarLivroDto patchExemplarLivroDto);
    void delete(UUID id);
}
