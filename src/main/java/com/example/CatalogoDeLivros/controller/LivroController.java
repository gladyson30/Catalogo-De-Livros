package com.example.CatalogoDeLivros.controller;


import com.example.CatalogoDeLivros.dto.LivroDto;
import com.example.CatalogoDeLivros.excecoes.LivroNaoExiste;
import com.example.CatalogoDeLivros.model.Livro;
import com.example.CatalogoDeLivros.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("livros")
public class LivroController {

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody LivroDto livroDto){
        service.salvar(livroDto);
        return ResponseEntity.status(200).body("Livro salvo");
    }

    @GetMapping("{id}")
    public ResponseEntity<Livro> buscar(@PathVariable UUID id){
        Livro livro = service.buscar(id);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.status(200).body("Livro deletado");
    }

    @PutMapping("{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable UUID id,@RequestBody LivroDto livroDto){
        Livro livro = service.atualizar(id,livroDto);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("listar")
    public List<Livro> listar(){
        return service.listar();
    }


    @ExceptionHandler(LivroNaoExiste.class)
    public ResponseEntity<String> livroNaoExiste(LivroNaoExiste livroNaoExiste){
        return ResponseEntity.status(404).body(livroNaoExiste.getMessage());
    }


}
