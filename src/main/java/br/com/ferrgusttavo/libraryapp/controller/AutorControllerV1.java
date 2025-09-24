package br.com.ferrgusttavo.libraryapp.controller;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import br.com.ferrgusttavo.libraryapp.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/autores")
@RequiredArgsConstructor
public class AutorControllerV1 {

    private final AutorService autorService;

    @GetMapping
    public ResponseEntity<Page<AutorDto>> findAll(Pageable pagination) {
        return ResponseEntity.ok(autorService.findAll(pagination));
    }

    @PostMapping
    public ResponseEntity<AutorDto> save(
            @Valid @RequestBody AutorDto autorDto,
            UriComponentsBuilder uriBuilder) {
        var autorDtoSaved = autorService.save(autorDto);
        URI uri = uriBuilder
                .path("/api/v1/autores/{id}")
                .buildAndExpand(autorDtoSaved.id())
                .toUri();
        return ResponseEntity.created(uri).body(autorDtoSaved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AutorDto> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AutorDto autorDto) {
        return ResponseEntity.ok(autorService.update(id, autorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AutorDto> delete(@PathVariable("id") UUID id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
