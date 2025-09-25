package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.livro.CreateLivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.LivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.PatchLivroDto;
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
    public LivroDto save(CreateLivroDto createLivroDto) {
        List<Autor> autores = createLivroDto.autorId().stream()
                .map(id -> autorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado: " + id)))
                .toList();

        Livro livro = Livro.builder()
                .titulo(createLivroDto.titulo())
                .descricao(createLivroDto.descricao())
                .qtdePaginas(createLivroDto.qtdePaginas())
                .build();

        livro.getAutores().addAll(autores);

        return new LivroDto(livroRepository.save(livro));
    }

    @Transactional
    @Override
    public LivroDto update(UUID id, PatchLivroDto patchLivroDto) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        Optional.ofNullable(patchLivroDto.titulo()).ifPresent(livro::setTitulo);
        Optional.ofNullable(patchLivroDto.descricao()).ifPresent(livro::setDescricao);
        Optional.ofNullable(patchLivroDto.qtdePaginas()).ifPresent(livro::setQtdePaginas);

        if (patchLivroDto.autorId() != null && !patchLivroDto.autorId().isEmpty()) {
            List<Autor> autores = patchLivroDto.autorId().stream()
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
