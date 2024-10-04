package com.aluracursos.listcolectdate.screenmatch.calculos;

import com.aluracursos.listcolectdate.screenmatch.modelos.Titulo;

public class CalculadoraDeTiempo {
    private int tiempoTotal;

    public int getTiempoTotal() {
        return this.tiempoTotal;
    }



    public void incluido(Titulo titulo) {
        System.out.println("Agregando duración en minutos de " + titulo);
        this.tiempoTotal += titulo.getDuracionEnMinutos();
    }
}