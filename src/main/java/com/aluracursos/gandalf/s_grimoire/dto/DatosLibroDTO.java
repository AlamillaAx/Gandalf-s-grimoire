package com.aluracursos.gandalf.s_grimoire.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroDTO(@JsonAlias("id") Integer id,
                            @JsonAlias("title") String title,
                            @JsonAlias("authors") List<DatosAutorDTO> autores,
                            @JsonAlias("bookshelves") List<String> genero,
                            @JsonAlias("languages") List<String> idioma,
                            @JsonAlias("download_count") Integer numDescargas) {
}
