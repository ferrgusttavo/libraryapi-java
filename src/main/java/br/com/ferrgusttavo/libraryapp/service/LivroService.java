package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import br.com.ferrgusttavo.libraryapp.dto.LivroCreateDto;
import br.com.ferrgusttavo.libraryapp.dto.LivroDto;
import br.com.ferrgusttavo.libraryapp.dto.LivroPatchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LivroService {
    Page<LivroDto> findAll(String titulo, Pageable pagination);
    LivroDto findById(UUID id);
    LivroDto save(LivroCreateDto livroCreateDto);
    LivroDto update(UUID id, LivroPatchDto livroPatchDto);
    void delete(UUID id);
}
