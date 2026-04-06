package com.example.CatalogoDeLivros.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record AutorResponseDto(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        List<ListarLivroResponseDtos> livros
) {
}
