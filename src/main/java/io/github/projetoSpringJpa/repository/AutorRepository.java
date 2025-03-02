package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
