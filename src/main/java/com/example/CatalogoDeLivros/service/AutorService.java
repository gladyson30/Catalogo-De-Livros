package com.example.CatalogoDeLivros.service;

import com.example.CatalogoDeLivros.dto.AutorDto;
import com.example.CatalogoDeLivros.excecoes.AutorCadastrado;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AutorService {
    private AutorRepository repository;


    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor buscar(UUID id){
       return repository.findById(id).orElseThrow(() -> new AutorNaoExiste("Autor nao encontrado"));
    }


    public void Salvar(AutorDto autorDto){
        if (repository.existsByNome(autorDto.getNome())){
            throw new AutorCadastrado(" ");
        }
        Autor autor = new Autor();
        autor.setNome(autorDto.getNome());
        autor.setDataNascimento(autorDto.getDataNascimento());
        repository.save(autor);
    }

    public Autor atualizar(UUID id, AutorDto autorDto){
        Autor autor = repository.findById(id).orElseThrow(() -> new AutorNaoExiste("Autor nao existe"));

        if (autorDto.getNome() != null && !autorDto.getNome().trim().isEmpty()){
            autor.setNome(autorDto.getNome());
        }
        if (autorDto.getDataNascimento() != null){
            autor.setDataNascimento(autorDto.getDataNascimento());
        }

        repository.save(autor);

        return autor;
    }

    public List<Autor> listarAutor(){
         return repository.findAll();
    }

    public void deletar(UUID id){
        if (!repository.existsById(id)){
            throw new AutorNaoExiste("autor nao encontrado");
        }
        repository.deleteById(id);
    }
}
