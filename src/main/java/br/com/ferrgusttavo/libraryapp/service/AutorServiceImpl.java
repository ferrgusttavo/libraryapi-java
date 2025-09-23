package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import br.com.ferrgusttavo.libraryapp.model.Autor;
import br.com.ferrgusttavo.libraryapp.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    @Override
    public Page<AutorDto> findAll(Pageable pagination) {
        return autorRepository.findAll(pagination).map(AutorDto::new);
    }

    @Transactional
    @Override
    public AutorDto save(AutorDto autorDto) {
        var autor = Autor.fromDto(autorDto);
        return new AutorDto(autorRepository.save(autor));
    }

    @Transactional
    @Override
    public AutorDto update(UUID id, AutorDto autorDto) {
        var autor = autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado."));
        autor.setNome(autorDto.nome());
        return new AutorDto(autorRepository.save(autor));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado."));
        autorRepository.deleteById(id);
    }
}
