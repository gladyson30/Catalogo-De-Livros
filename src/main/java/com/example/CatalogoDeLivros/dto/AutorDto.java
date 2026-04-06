package com.example.CatalogoDeLivros.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDto(
        String nome,
        LocalDate dataNascimento
) {
}
