package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
