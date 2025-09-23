package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AutorService {
    Page<AutorDto> findAll(Pageable pagination);
    AutorDto save(AutorDto autorDto);
    AutorDto update(UUID id, AutorDto autorDto);
    void delete(UUID id);
}
