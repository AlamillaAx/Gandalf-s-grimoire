package com.aluracursos.gandalf.s_grimoire.model;

public enum Categoria {
    LITERATURA_INFANTIL("Children's Literature"),
    CIENCIA_FICCION_HISTORICA("Historical Fiction"),
    LOS_MEJORES_DE_TODOS_LOS_TIEMPOS("Best Books Ever Listings"),
    CLASICOS_DE_HARVARD("Harvard Classics"),
    FICCION_MISTERIO ("Mystery Fiction"),
    LIBROS_ILUSTRADOS_PARA_NINOS("Children's Picture Books"),
    FICCION_GOTICA("Gothic Fiction"),
    LITERTATURA_CLASICA("6 Best Loved Spanish Literary Classics");

    private String categoriaIngles;

    Categoria (String categoriaIngles){
        this.categoriaIngles = categoriaIngles;
    }

    public static Categoria fromEnglish(String text){
        for (Categoria categoria : Categoria.values()){
            if(categoria.categoriaIngles.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("No se encontro una categoria registrada con: " +text);
    }
}
