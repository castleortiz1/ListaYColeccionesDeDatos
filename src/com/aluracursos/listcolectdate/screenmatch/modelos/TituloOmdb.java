package com.aluracursos.listcolectdate.screenmatch.modelos;

public record TituloOmdb(String title, String year, String runtime) {
    public CharSequence getTitle() {
        return title;
    }
}
