package com.example.CatalogoDeLivros.service;

import com.example.CatalogoDeLivros.dto.ListarLivroResponseDtos;
import com.example.CatalogoDeLivros.dto.LivroDto;
import com.example.CatalogoDeLivros.dto.LivroResponseDto;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.excecoes.LivroNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.model.Livro;
import com.example.CatalogoDeLivros.repository.AutorRepository;
import com.example.CatalogoDeLivros.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;


    public void salvar(LivroDto livroDto){

        Autor autor = autorRepository.findById(livroDto.getId()).orElseThrow(() -> new AutorNaoExiste("autor nao existe"));

        Livro livro = new Livro();
        livro.setTitulo(livroDto.getTitulo());
        livro.setDescricao(livroDto.getDescricao());
        livro.setDataPublicacao(livroDto.getDataPublicacao());
        livro.setAutor(autor);
        autor.getLivros().add(livro);
        livroRepository.save(livro);
    }

    public LivroResponseDto buscar(UUID id){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoExiste("Livro nao encontrado"));

        LivroResponseDto livroDto = new LivroResponseDto();
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setDescricao(livro.getDescricao());
        livroDto.setDataPublicacao(livro.getDataPublicacao());
        livroDto.setNomeAutor(livro.getAutor().getNome());

        return livroDto;
    }

    public void deletar(UUID id){
        if (!livroRepository.existsById(id)){
            throw new LivroNaoExiste("esse livro nao foi encontrado");
        }
        livroRepository.deleteById(id);
    }

    public Livro atualizar(UUID id ,LivroDto livroDto){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoExiste("livro nao encontrado"));

        if (livroDto.getTitulo() != null && !livroDto.getTitulo().trim().isEmpty()){
            livro.setTitulo(livroDto.getTitulo());
        }
        if (livroDto.getDescricao() != null && !livroDto.getDescricao().trim().isEmpty()){
            livro.setDescricao(livroDto.getDescricao());
        }
        if (livroDto.getDataPublicacao() != null){
            livro.setDataPublicacao(livroDto.getDataPublicacao());
        }

        livroRepository.save(livro);
        return livro;
    }

    public List<ListarLivroResponseDtos> listar(){
        List<Livro> livros = livroRepository.findAll();
        List<ListarLivroResponseDtos> dtos = new ArrayList<>();

        for(Livro livro: livros){
            dtos.add(new ListarLivroResponseDtos(
                    livro.getTitulo(),
                    livro.getDataPublicacao(),
                    livro.getDescricao()
            ));
        }
        return dtos;
    }
}
