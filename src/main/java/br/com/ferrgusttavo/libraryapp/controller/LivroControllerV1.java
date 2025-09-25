package br.com.ferrgusttavo.libraryapp.controller;

import br.com.ferrgusttavo.libraryapp.dto.livro.CreateLivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.LivroDto;
import br.com.ferrgusttavo.libraryapp.dto.livro.PatchLivroDto;
import br.com.ferrgusttavo.libraryapp.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/livros")
@RequiredArgsConstructor
public class LivroControllerV1 {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<LivroDto>> findAll(
            @RequestParam(required = false) String titulo,
            Pageable pagination) {
        return ResponseEntity.ok(livroService.findAll(titulo, pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<LivroDto> save(@Valid @RequestBody CreateLivroDto dto) {
        return ResponseEntity.ok(livroService.save(dto));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<LivroDto> update(
            @PathVariable("id") UUID id,
            @RequestBody PatchLivroDto dto) {
        return ResponseEntity.ok(livroService.update(id, dto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        livroService.delete(id);
        return ResponseEntity.ok().build();
    }
}
