package com.example.CatalogoDeLivros.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LivroResponseDto {
    private String titulo;
    private String descricao;
    private LocalDate dataPublicacao;
    private String nomeAutor;
}
