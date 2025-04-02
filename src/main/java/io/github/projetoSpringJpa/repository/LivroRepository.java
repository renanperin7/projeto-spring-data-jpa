package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import io.github.projetoSpringJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 *  @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query method

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Livro findByIsbn(String isbn);

    List<Livro> findByTituloAndPrecoOrderByTitulo(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> referencia as entidades e as propriedades
    @Query(" select l from Livro as l order by l.titulo")
    List<Livro> listarTodosOrdenadoPorTitulo();

    @Query(" select l from Livro as l join l.autor a ")
    List<Livro> listarAutoresDosLivros();

    @Query(" select distinct l.titulo from Livro ")
    List<String> listarTitulosDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
                join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();
}