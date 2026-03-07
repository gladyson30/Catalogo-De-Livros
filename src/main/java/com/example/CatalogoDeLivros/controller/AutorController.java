package com.example.CatalogoDeLivros.controller;

import com.example.CatalogoDeLivros.dto.AutorDto;
import com.example.CatalogoDeLivros.excecoes.AutorCadastrado;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("autor")
public class AutorController {

    private AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PutMapping("{id}")
    public ResponseEntity<Autor> atualixar(@PathVariable UUID id,@RequestBody AutorDto autorDto){
        Autor autor = service.atualizar(id,autorDto);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AutorDto autorDto){
        service.Salvar(autorDto);
        return ResponseEntity.status(200).body("autor salvo");
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> buscar(@PathVariable UUID id){
       Autor autor = service.buscar(id);
        return ResponseEntity.ok(autor);

    }

    @GetMapping("listar")
    public ResponseEntity<List<Autor>> listar(){
        List<Autor> autores = service.listarAutor();
        return ResponseEntity.ok(autores);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.status(200).body("autor deletado");
    }


    @ExceptionHandler(AutorCadastrado.class)
    public ResponseEntity<String> autor(AutorCadastrado autorCadastrado){
        return ResponseEntity.status(404).body("esse autor ja possui cadastro");
    }

    @ExceptionHandler(AutorNaoExiste.class)
    public ResponseEntity<String> autorInexistente(AutorNaoExiste autorNaoExiste){
        return ResponseEntity.status(404).body("autor nao existente");
    }
}
