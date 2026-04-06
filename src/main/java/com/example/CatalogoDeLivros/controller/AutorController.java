package com.example.CatalogoDeLivros.controller;

import com.example.CatalogoDeLivros.dto.AutorDto;
import com.example.CatalogoDeLivros.dto.AutorResponseDto;
import com.example.CatalogoDeLivros.excecoes.AutorCadastrado;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.service.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("autor")
public class AutorController {

    private  AutorService service;



    @PutMapping("{id}")
    public ResponseEntity<String> atualixar(@PathVariable UUID id,@RequestBody AutorDto autorDto){
        service.atualizar(id,autorDto);
        return ResponseEntity.status(200).body("Autor atualizado! ");
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AutorDto autorDto){
        service.Salvar(autorDto);
        return ResponseEntity.status(200).body("autor salvo");
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDto> buscar(@PathVariable UUID id){
       AutorResponseDto autorResponseDto= service.buscar(id);
        return ResponseEntity.ok(autorResponseDto);
    }

//    @GetMapping("listar")
//    public ResponseEntity<List<Autor>> listar(){
//        List<Autor> autores = service.listar();
//        return ResponseEntity.ok(autores);
//    }

    @GetMapping("listar")
    public ResponseEntity<List<AutorResponseDto>> listar(){
        List<AutorResponseDto> autor = service.listarAutor();
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.status(200).body("autor deletado");
    }
}
