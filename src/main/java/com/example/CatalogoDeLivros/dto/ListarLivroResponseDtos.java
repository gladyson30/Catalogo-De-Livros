package com.example.CatalogoDeLivros.dto;

import java.time.LocalDate;

public record ListarLivroResponseDtos(
        String titulo,
        LocalDate dataPublicacao,
        String descricao
) {
}
