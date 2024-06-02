package com.aluracursos.gandalf.s_grimoire.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
