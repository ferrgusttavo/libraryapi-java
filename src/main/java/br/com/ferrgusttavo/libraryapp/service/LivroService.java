package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.livro.CreateLivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.LivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.PatchLivroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LivroService {
    Page<LivroDto> findAll(String titulo, Pageable pagination);
    LivroDto findById(UUID id);
    LivroDto save(CreateLivroDto createLivroDto);
    LivroDto update(UUID id, PatchLivroDto patchLivroDto);
    void delete(UUID id);
}
