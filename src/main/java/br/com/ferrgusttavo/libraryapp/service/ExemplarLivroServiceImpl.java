package br.com.ferrgusttavo.libraryapp.service;

import br.com.ferrgusttavo.libraryapp.dto.exemplarLivro.*;
import br.com.ferrgusttavo.libraryapp.dto.livro.LivroDto;
import br.com.ferrgusttavo.libraryapp.model.ExemplarLivro;
import br.com.ferrgusttavo.libraryapp.model.Livro;
import br.com.ferrgusttavo.libraryapp.model.StatusLivro;
import br.com.ferrgusttavo.libraryapp.repository.ExemplarLivroRepository;
import br.com.ferrgusttavo.libraryapp.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExemplarLivroServiceImpl implements ExemplarLivroService {

    private final ExemplarLivroRepository exemplarLivroRepository;
    private final LivroRepository livroRepository;

    @Override
    public ExemplarLivroDto findById(UUID id) {
        return exemplarLivroRepository
                .findById(id)
                .map(ExemplarLivroDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar não encontrado."));
    }

    @Override
    public ExemplarLivroResponse findAllByLivroId(UUID livroId) {

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        List<ExemplarLivro> exemplares = exemplarLivroRepository.findAllByLivroId(livroId);

        List<ExemplarLivroDto> exemplaresDto = exemplares.stream()
                .map(ExemplarLivroDto::new)
                .toList();

        return new ExemplarLivroResponse(new LivroDto(livro), exemplaresDto);
    }

    @Override
    public QuantidadeExemplaresDto contarExemplaresPorLivroId(UUID livroId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        List<ExemplarLivro> exemplares = exemplarLivroRepository.findAllByLivroId(livroId);

        long total = exemplares.size();
        long disponivel = exemplares.stream().filter(e -> e.getStatusLivro() == StatusLivro.DISPONIVEL).count();
        long emprestado = exemplares.stream().filter(e -> e.getStatusLivro() == StatusLivro.EMPRESTADO).count();
        long reservado = exemplares.stream().filter(e -> e.getStatusLivro() == StatusLivro.RESERVADO).count();

        return new QuantidadeExemplaresDto(
                livro.getId(),
                livro.getTitulo(),
                total,
                disponivel,
                emprestado,
                reservado
        );
    }

    @Transactional
    @Override
    public ExemplarLivroDto save(UUID livroId, CreateExemplarLivroDto createExemplarLivroDto) {
        Livro livro = livroRepository
                .findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        ExemplarLivro exemplar = ExemplarLivro.builder()
                .livro(livro)
                .statusConservacao(createExemplarLivroDto.statusConservacao())
                .statusLivro(createExemplarLivroDto.statusLivro())
                .build();

        return new ExemplarLivroDto(exemplarLivroRepository.save(exemplar));
    }

    @Transactional
    @Override
    public ExemplarLivroDto update(UUID id, PatchExemplarLivroDto patchExemplarLivroDto) {
        var exemplar =  exemplarLivroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        Optional.ofNullable(patchExemplarLivroDto.statusConservacao())
                .ifPresent(exemplar::setStatusConservacao);

        Optional.ofNullable(patchExemplarLivroDto.statusLivro())
                .ifPresent(exemplar::setStatusLivro);

        return new ExemplarLivroDto(exemplarLivroRepository.save(exemplar));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        ExemplarLivro exemplar = exemplarLivroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
        exemplarLivroRepository.delete(exemplar);
    }
}
