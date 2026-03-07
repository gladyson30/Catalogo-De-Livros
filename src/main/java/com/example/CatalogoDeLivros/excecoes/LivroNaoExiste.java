package com.example.CatalogoDeLivros.excecoes;

public class LivroNaoExiste extends RuntimeException {
    public LivroNaoExiste(String message) {
        super(message);
    }
}
