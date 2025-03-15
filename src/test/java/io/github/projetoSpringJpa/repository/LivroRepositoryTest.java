package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import io.github.projetoSpringJpa.model.GeneroLivro;
import io.github.projetoSpringJpa.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
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
}