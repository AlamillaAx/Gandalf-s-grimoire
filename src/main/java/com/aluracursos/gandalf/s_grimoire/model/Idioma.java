package com.aluracursos.gandalf.s_grimoire.model;

public enum Idioma {
    EN("en"),
    ES("es"),
    FR("fr")
    ;

    private String idiomasIngles;

    Idioma(String idiomasIngles){
        this.idiomasIngles = idiomasIngles;
    }

    public static Idioma fromEnglish(String text){
        for (Idioma idioma : Idioma.values()){
            if(idioma.idiomasIngles.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no idenficado: " +text);
    }

}
