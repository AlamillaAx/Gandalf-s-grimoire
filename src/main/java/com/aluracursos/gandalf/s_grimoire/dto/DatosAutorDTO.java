package com.aluracursos.gandalf.s_grimoire.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutorDTO(@JsonAlias("name") String nombre,
                            @JsonAlias("birth_year") Integer anoNacimiento,
                            @JsonAlias("death_year") Integer anoMuerte) {
}
