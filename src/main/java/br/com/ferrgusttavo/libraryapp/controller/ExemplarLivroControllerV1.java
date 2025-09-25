package br.com.ferrgusttavo.libraryapp.controller;

import br.com.ferrgusttavo.libraryapp.dto.exemplarLivro.*;
import br.com.ferrgusttavo.libraryapp.service.ExemplarLivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/livros/{livroId}/exemplares")
@RequiredArgsConstructor
public class ExemplarLivroControllerV1 {

    private final ExemplarLivroService exemplarLivroService;

    @GetMapping
    public ResponseEntity<ExemplarLivroResponse> findAllByLivroId(@PathVariable("livroId") UUID livroId) {
        return ResponseEntity.ok(exemplarLivroService.findAllByLivroId(livroId));
    }

    @GetMapping("/{exemplarId}")
    public ResponseEntity<ExemplarLivroDto> findById(@PathVariable("exemplarId") UUID exemplarId) {
        return ResponseEntity.ok(exemplarLivroService.findById(exemplarId));
    }

    @GetMapping("/quantidade")
    public ResponseEntity<QuantidadeExemplaresDto> contarExemplares(@PathVariable("livroId") UUID livroId) {
        return ResponseEntity.ok(exemplarLivroService.contarExemplaresPorLivroId(livroId));
    }

    @PostMapping()
    public ResponseEntity<ExemplarLivroDto> save(@PathVariable("livroId") UUID id, @Valid @RequestBody CreateExemplarLivroDto dto) {
        return ResponseEntity.ok(exemplarLivroService.save(id, dto));
    }

    @PatchMapping("/{exemplarId}")
    public ResponseEntity<ExemplarLivroDto> update(@PathVariable("exemplarId") UUID id, @Valid @RequestBody PatchExemplarLivroDto dto) {
        return ResponseEntity.ok(exemplarLivroService.update(id, dto));
    }

    @DeleteMapping("/{exemplarId}")
    public ResponseEntity<?> delete(@PathVariable("exemplarId") UUID id) {
        exemplarLivroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
