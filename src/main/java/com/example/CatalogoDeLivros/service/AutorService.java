package com.example.CatalogoDeLivros.service;

import com.example.CatalogoDeLivros.dto.AutorDto;
import com.example.CatalogoDeLivros.dto.AutorResponseDto;
import com.example.CatalogoDeLivros.dto.ListarLivroResponseDtos;
import com.example.CatalogoDeLivros.excecoes.AutorCadastrado;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.model.Livro;
import com.example.CatalogoDeLivros.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Service
public class AutorService {
    private AutorRepository repository;


    public AutorResponseDto buscar(UUID id){
       Autor autor = repository.findById(id).orElseThrow(() -> new AutorNaoExiste("Autor nao encontrado"));

       List<ListarLivroResponseDtos> livrosDto = new ArrayList<>();

       for (Livro livro: autor.getLivros()){
           ListarLivroResponseDtos dto = new ListarLivroResponseDtos(
                   livro.getTitulo(),
                   livro.getDataPublicacao(),
                   livro.getDescricao()
           );
           livrosDto.add(dto);
       }
       AutorResponseDto autorResponseDto = new AutorResponseDto(
               autor.getId(),
               autor.getNome(),
               autor.getDataNascimento(),
               livrosDto
       );
       return autorResponseDto;
    }


    public void Salvar(AutorDto autorDto){
        if (repository.existsByNome(autorDto.nome())){
            throw new AutorCadastrado(" ");
        }
        Autor autor = new Autor();
        autor.setNome(autorDto.nome());
        autor.setDataNascimento(autorDto.dataNascimento());
        repository.save(autor);
    }

    public void atualizar(UUID id, AutorDto autorDto){
        Autor autor = repository.findById(id).orElseThrow(() -> new AutorNaoExiste("Autor nao existe"));

        if (autorDto.nome() != null && !autorDto.nome().trim().isEmpty()){
            autor.setNome(autorDto.nome());
        }
        if (autorDto.dataNascimento() != null){
            autor.setDataNascimento(autorDto.dataNascimento());
        }
        repository.save(autor);
    }

    public List<Autor> listar(){
        return repository.findAll();
    }


    public List<AutorResponseDto> listarAutor(){
         List<Autor> autor = repository.findAll();
         List<AutorResponseDto> autorResponseDto = new ArrayList<>();

         for(Autor autor1: autor){
             List<ListarLivroResponseDtos> livroResponseDtos = new ArrayList<>();

             for(Livro livro: autor1.getLivros()){
                 livroResponseDtos.add( new ListarLivroResponseDtos(
                         livro.getTitulo(),
                         livro.getDataPublicacao(),
                         livro.getDescricao()
                 ));
             }
             autorResponseDto.add(new AutorResponseDto(
                     autor1.getId(),
                     autor1.getNome(),
                     autor1.getDataNascimento(),
                     livroResponseDtos
             ));

         }
         return autorResponseDto;
    }

    public void deletar(UUID id){
        if (!repository.existsById(id)){
            throw new AutorNaoExiste("autor nao encontrado");
        }
        repository.deleteById(id);
    }
}
