package com.example.CatalogoDeLivros.excecoes;

public class AutorNaoExiste extends RuntimeException {
    public AutorNaoExiste(String message) {
        super(message);
    }
}
