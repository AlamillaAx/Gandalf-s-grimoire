package com.aluracursos.gandalf.s_grimoire.model;

import com.aluracursos.gandalf.s_grimoire.dto.DatosAutorDTO;
import com.aluracursos.gandalf.s_grimoire.dto.DatosLibroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numDescargas;
    @ManyToOne
    private Autor autor;

    private Libro(){}

    public Libro(DatosLibroDTO datosLibroDTO) {
        this.title = datosLibroDTO.title();
        this.genero = Categoria.fromEnglish(datosLibroDTO.genero().get(0));
        this.idioma = Idioma.fromEnglish(datosLibroDTO.idioma().get(0));
//        try{
//            this.genero = Categoria.fromEnglish(datosLibroDTO.genero().get(0));
//        }catch (Exception e){
//            System.out.println("No es una categoria reconocida: " + datosLibroDTO.genero());
//        }
//        try{
//            this.idioma = Idioma.fromEnglish(datosLibroDTO.idioma().get(0));
//        }catch (Exception e){
//            System.out.println("Excepcion de idioma encontrada: " +datosLibroDTO.idioma());
//        }
        this.numDescargas = datosLibroDTO.numDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Integer numDescargas) {
        this.numDescargas = numDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titulo: " + title +
                ", Genero: " + genero +
                ", Idioma: " + idioma +
                ", Numero de descargas: " +numDescargas;
    }
}
