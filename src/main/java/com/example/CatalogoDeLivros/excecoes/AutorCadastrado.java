package com.example.CatalogoDeLivros.excecoes;

public class AutorCadastrado extends RuntimeException {
    public AutorCadastrado(String message) {
        super(message);
    }
}
