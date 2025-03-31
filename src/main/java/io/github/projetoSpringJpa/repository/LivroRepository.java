package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import io.github.projetoSpringJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query method

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Livro findByIsbn(String isbn);

    List<Livro> findByTituloAndPrecoOrderByTitulo(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
}