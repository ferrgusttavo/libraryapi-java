package br.com.ferrgusttavo.libraryapp.model;

import br.com.ferrgusttavo.libraryapp.dto.AutorDto;
import br.com.ferrgusttavo.libraryapp.service.AutorService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_autores")
public class Autor {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 255)
    private String nome;

    public static Autor fromDto(AutorDto autorDto) {
        return new Autor(
                autorDto.id(),
                autorDto.nome()
        );
    }
}
