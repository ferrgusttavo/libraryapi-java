package br.com.ferrgusttavo.libraryapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_exemplares_livros")
public class ExemplarLivro {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatusConservacao statusConservacao;

    @Enumerated(EnumType.STRING)
    private StatusLivro statusLivro;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
}
