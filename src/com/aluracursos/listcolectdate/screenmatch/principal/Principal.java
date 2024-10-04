package com.aluracursos.listcolectdate.screenmatch.principal;

import com.aluracursos.listcolectdate.screenmatch.calculos.CalculadoraDeTiempo;
import com.aluracursos.listcolectdate.screenmatch.calculos.FiltroRecomendacion;
import com.aluracursos.listcolectdate.screenmatch.modelos.Episodio;
import com.aluracursos.listcolectdate.screenmatch.modelos.Pelicula;
import com.aluracursos.listcolectdate.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        // constructor miPelicula
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.setDuracionEnMinutos(180);
        miPelicula.muestraFichaTecnica();
        miPelicula.evalua(8);
        miPelicula.evalua(5);
        miPelicula.evalua(10);
        System.out.println("Duración de la película: " + miPelicula.getDuracionEnMinutos());
        System.out.println("Total de evaluaciones: " + miPelicula.getTotalDeEvaluaciones());
        System.out.println(miPelicula.calculaMediaEvaluaciones());

        // contructor lost
        Serie lost = new Serie("Lost", 2000);
        lost.muestraFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        System.out.println("Duracion de la série: " + lost.getDuracionEnMinutos());

        // constructor otraPelicula
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.setDuracionEnMinutos(200);

        CalculadoraDeTiempo calculadora = new CalculadoraDeTiempo();
        calculadora.incluido(miPelicula);
        calculadora.incluido(otraPelicula);
        calculadora.incluido(lost);
        System.out.println(calculadora.getTiempoTotal());

        FiltroRecomendacion filtro = new FiltroRecomendacion();
        filtro.filtra(miPelicula);

        // constructor episodio
        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizaciones(300);
        filtro.filtra(episodio);

        // constructor peliculaDeBruno
        var peliculaDeBruno = new Pelicula("Pulp Fiction", 1994);
        peliculaDeBruno.setDuracionEnMinutos(180);

        // constructor listaDePelicula
        ArrayList<Pelicula> listaDePelicula = new ArrayList<>();
        listaDePelicula.add(peliculaDeBruno);
        listaDePelicula.add(miPelicula);
        listaDePelicula.add(otraPelicula);

        System.out.println("**************************");
        System.out.println("Tamaño de la lista: " + listaDePelicula.size());
        System.out.println("La primera pelicula es: " + listaDePelicula.get(0).getNombre());

        System.out.println("**************************");
        System.out.println(listaDePelicula.toString());
        System.out.println("toString de la pelicula: " + listaDePelicula.get(0).toString());

    }

}
