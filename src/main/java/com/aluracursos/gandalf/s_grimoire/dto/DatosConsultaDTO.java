package com.aluracursos.gandalf.s_grimoire.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosConsultaDTO(@JsonAlias("count") Integer resultsCount,
                               @JsonAlias("next") String masResultados,
                               @JsonAlias("results") List<DatosLibroDTO> libros) {
}
