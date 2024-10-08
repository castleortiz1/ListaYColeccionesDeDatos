package com.aluracursos.listcolectdate.screenmatch.principal;
import com.aluracursos.listcolectdate.screenmatch.modelos.Pelicula;
import com.aluracursos.listcolectdate.screenmatch.modelos.Serie;
import com.aluracursos.listcolectdate.screenmatch.modelos.Titulo;

import java.util.*;

public class PriincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("Pulp Fiction", 1994);
        peliculaDeBruno.evalua(10);
        Serie lost = new Serie("Lost", 2000);

        List<Titulo> lista = new LinkedList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(lost);

        for (Titulo item: lista){
            System.out.println(item.getNombre());
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
                System.out.println(pelicula.getClasificacion());

            }

        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Jennifer Lawrence");
        listaDeArtistas.add("Christopher Nolan");
        listaDeArtistas.add("Antonio Banderas");
        System.out.println(listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas ordenada: " + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista de titulos ordenadas: " + lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista ordenanda por fechas: " + lista);

    }
}
