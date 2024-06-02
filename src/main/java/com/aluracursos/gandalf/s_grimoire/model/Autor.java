package com.aluracursos.gandalf.s_grimoire.model;

import com.aluracursos.gandalf.s_grimoire.dto.DatosAutorDTO;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name ="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer anoNacmiento;
    private Integer anoMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

//    public Autor(String nombre, Integer anoNacmiento, Integer anoMuerte) {
//        this.nombre = nombre;
//        this.anoNacmiento = anoNacmiento;
//        this.anoMuerte = anoMuerte;
//    }

    public Autor(DatosAutorDTO datosAutorDTO) {
        this.nombre = datosAutorDTO.nombre();
        this.anoNacmiento = datosAutorDTO.anoNacimiento();
        this.anoMuerte = datosAutorDTO.anoMuerte();
    }

    public List<Libro> getLibros() {return libros;}

    public void setLibros(List<Libro> libros) {
        libros.forEach(e -> e.setAutor(this));
        this.libros = libros;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacmiento() {
        return anoNacmiento;
    }

    public void setAnoNacmiento(Integer anoNacmiento) {
        this.anoNacmiento = anoNacmiento;
    }

    public Integer getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(Integer anoMuerte) {
        this.anoMuerte = anoMuerte;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Año de nacimiento: " + anoNacmiento +
                ", Año de fallecimiento: " + anoMuerte;
    }
}
