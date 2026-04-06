package com.example.CatalogoDeLivros.excecoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExecoesControllerAdivaice {

    // AUTORES

    @ExceptionHandler(AutorCadastrado.class)
    public ResponseEntity<String> autor(AutorCadastrado autorCadastrado){
        return ResponseEntity.status(404).body("esse autor ja possui cadastro");
    }

    @ExceptionHandler(AutorNaoExiste.class)
    public ResponseEntity<String> autorInexistente(AutorNaoExiste autorNaoExiste){
        return ResponseEntity.status(404).body("autor nao existente");
    }

    // LIVROS

    @ExceptionHandler(LivroNaoExiste.class)
    public ResponseEntity<String> livroNaoExiste(LivroNaoExiste livroNaoExiste){
        return ResponseEntity.status(404).body(livroNaoExiste.getMessage());
    }
}
