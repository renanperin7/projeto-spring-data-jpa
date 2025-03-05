package io.github.projetoSpringJpa.repository;

import io.github.projetoSpringJpa.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1999, 12,27));

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("234d7274-13db-4ec2-a6d9-cf31d3a62676");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();

            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1932,12,2));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = autorRepository.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("234d7274-13db-4ec2-a6d9-cf31d3a62676");

        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("cf7de516-006c-47f8-8395-4e843c233639");
        var joao = autorRepository.findById(id).get();
        autorRepository.delete(joao);
    }
}
