package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import io.github.projetoSpringJpa.model.GeneroLivro;
import io.github.projetoSpringJpa.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();

        livro.setIsbn("99623-2907");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 1));

        Autor autor = autorRepository
                .findById(UUID.fromString("cf7de516-006c-47f8-8395-4e843c233639"))
                .orElse(null);


        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();

        livro.setIsbn("99623-2907");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 1));

        Autor autor = new Autor();

        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 12,27));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();

        livro.setIsbn("99623-2907");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 1));

        Autor autor = new Autor();

        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 12,27));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorLivro() {
        UUID id = UUID.fromString("cf7de516-006c-47f8-8395-4e843c233639");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("cf7de516-007c-47f8-8395-4e843c233639");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletarLivro() {
        UUID id = UUID.fromString("cf7de516-006c-47f8-8395-4e843c233639");

        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("cf7de516-006c-47f8-8395-4e843c233639");

        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println(livro.getTitulo());

        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaLivroTest() {
        List<Livro> lista = livroRepository.findByTitulo("UFO");

        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        Livro livro = livroRepository.findByIsbn("99623-2907");

        System.out.println(livro);
    }

    @Test
    void pesquisaPorTituloEPreco() {
        List<Livro> livro = livroRepository.findByTituloAndPrecoOrderByTitulo("UFO", BigDecimal.valueOf(29.0));

        livro.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloOuPreco() {
        List<Livro> livro = livroRepository.findByTituloOrPreco("UFO", BigDecimal.valueOf(50.0));

        livro.forEach(System.out::println);
    }

    @Test
    void pesquisaPorPeriodo() {
        List<Livro> livro = livroRepository
                .findByDataPublicacaoBetween(LocalDate.of(2000, 01, 01), LocalDate.of(2024, 01,01));

        livro.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = livroRepository.listarTodosOrdenadoPorTitulo();

        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = livroRepository.listarAutoresDosLivros();

        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosDiferentes() {
        var resultado = livroRepository.listarTitulosDiferentesLivros();

        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros() {
        var resultado = livroRepository.listarGenerosAutoresBrasileiros();

        resultado.forEach(System.out::println);
    }
}