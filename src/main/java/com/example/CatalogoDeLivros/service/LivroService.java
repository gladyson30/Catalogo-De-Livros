package com.example.CatalogoDeLivros.service;

import com.example.CatalogoDeLivros.dto.LivroDto;
import com.example.CatalogoDeLivros.excecoes.AutorNaoExiste;
import com.example.CatalogoDeLivros.excecoes.LivroNaoExiste;
import com.example.CatalogoDeLivros.model.Autor;
import com.example.CatalogoDeLivros.model.Livro;
import com.example.CatalogoDeLivros.repository.AutorRepository;
import com.example.CatalogoDeLivros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void salvar(LivroDto livroDto){

        Autor autor = autorRepository.findById(livroDto.getId()).orElseThrow(() -> new AutorNaoExiste("autor nao existe"));

        Livro livro = new Livro();
        livro.setTitulo(livroDto.getTitulo());
        livro.setDescricao(livroDto.getDescricao());
        livro.setDataPublicacao(livroDto.getDataPublicacao());
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    public Livro buscar(UUID id){
        return livroRepository.findById(id).orElseThrow(() -> new LivroNaoExiste("autor nao encontrado"));
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

    public List<Livro> listar(){
        return livroRepository.findAll();
    }
}
