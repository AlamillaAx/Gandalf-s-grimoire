package com.aluracursos.gandalf.s_grimoire.repository;

import com.aluracursos.gandalf.s_grimoire.model.Autor;
import com.aluracursos.gandalf.s_grimoire.model.Idioma;
import com.aluracursos.gandalf.s_grimoire.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query(value = "SELECT e FROM Autor a JOIN a.libros e")
    List<Libro> listarLibrosRegistrados();

    @Query(value ="SELECT a from Autor a WHERE a.anoNacmiento >= :anoIngresado AND a.anoMuerte >= :anoIngresado")
    List<Autor> autoresVivos(Integer anoIngresado);

    @Query(value = "SELECT l FROM Autor a JOIN a.libros l WHERE l.idioma = :libroIdioma")
    List<Libro> librosPorIdioma(Idioma libroIdioma);

    @Query(value = "SELECT l from Autor a JOIN a.libros l WHERE l.title ilike %:nombreLibro%")
    List<Libro> libroPorNombre(String nombreLibro);

    @Query(value ="SELECT l FROM Autor a JOIN a.libros l ORDER BY l.numDescargas DESC LIMIT 10 ")
    List<Libro> top10Libros();

    @Query(value = "SELECT a FROM Autor a WHERE a.nombre ilike %:nomAutor%")
    List<Autor> autorPorNombre(String nomAutor);
}
