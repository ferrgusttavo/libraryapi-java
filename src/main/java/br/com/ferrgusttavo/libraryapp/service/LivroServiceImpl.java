package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.LivroCreateDto;
import br.com.ferrgusttavo.libraryapp.dto.LivroDto;
import br.com.ferrgusttavo.libraryapp.dto.LivroPatchDto;
import br.com.ferrgusttavo.libraryapp.model.Autor;
import br.com.ferrgusttavo.libraryapp.model.Livro;
import br.com.ferrgusttavo.libraryapp.repository.AutorRepository;
import br.com.ferrgusttavo.libraryapp.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Override
    public Page<LivroDto> findAll(String titulo, Pageable pagination) {
        Page<Livro> livros;

        if (titulo == null || titulo.isBlank()) {
            livros = livroRepository.findAll(pagination);
        } else {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo, pagination);
        }

        return livros.map(LivroDto::new);
    }

    @Override
    public LivroDto findById(UUID id) {
        return livroRepository.findById(id)
                .map(LivroDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }

    @Transactional
    @Override
    public LivroDto save(LivroCreateDto livroCreateDto) {
        List<Autor> autores = livroCreateDto.autorId().stream()
                .map(id -> autorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado: " + id)))
                .toList();

        Livro livro = Livro.builder()
                .titulo(livroCreateDto.titulo())
                .descricao(livroCreateDto.descricao())
                .qtdePaginas(livroCreateDto.qtdePaginas())
                .build();

        livro.getAutores().addAll(autores);

        return new LivroDto(livroRepository.save(livro));
    }

    @Transactional
    @Override
    public LivroDto update(UUID id, LivroPatchDto livroPatchDto) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        Optional.ofNullable(livroPatchDto.titulo()).ifPresent(livro::setTitulo);
        Optional.ofNullable(livroPatchDto.descricao()).ifPresent(livro::setDescricao);
        Optional.ofNullable(livroPatchDto.qtdePaginas()).ifPresent(livro::setQtdePaginas);

        if (livroPatchDto.autorId() != null && !livroPatchDto.autorId().isEmpty()) {
            List<Autor> autores = livroPatchDto.autorId().stream()
                    .map(autorId -> autorRepository.findById(autorId)
                            .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado: " + autorId)))
                    .toList();
            livro.getAutores().clear();
            livro.getAutores().addAll(autores);
        }

        return new LivroDto(livroRepository.save(livro));
    }

    @Override
    public void delete(UUID id) {
        livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
        livroRepository.deleteById(id);
    }
}
