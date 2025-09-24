package br.com.ferrgusttavo.libraryapp.model;

import br.com.ferrgusttavo.libraryapp.dto.LivroDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_livros")
public class Livro {
    @Id
    @GeneratedValue
    private UUID id;

    private String titulo;
    private String descricao;
    private Integer qtdePaginas;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @Builder.Default
    private List<Autor> autores = new ArrayList<>();
}
