package br.com.ferrgusttavo.libraryapp.repository;

import br.com.ferrgusttavo.libraryapp.model.ExemplarLivro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExemplarLivroRepository extends JpaRepository<ExemplarLivro, UUID> {
    List<ExemplarLivro> findAllByLivroId(UUID id);
}
