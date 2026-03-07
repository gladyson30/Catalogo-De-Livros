package com.example.CatalogoDeLivros.repository;

import com.example.CatalogoDeLivros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
