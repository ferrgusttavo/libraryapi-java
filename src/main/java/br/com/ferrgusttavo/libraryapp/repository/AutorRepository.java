package br.com.ferrgusttavo.libraryapp.repository;

import br.com.ferrgusttavo.libraryapp.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
