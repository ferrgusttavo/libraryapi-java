package br.com.ferrgusttavo.libraryapp.repository;

import br.com.ferrgusttavo.libraryapp.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
