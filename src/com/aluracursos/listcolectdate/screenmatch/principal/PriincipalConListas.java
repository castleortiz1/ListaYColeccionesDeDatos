package com.aluracursos.listcolectdate.screenmatch.principal;
import com.aluracursos.listcolectdate.screenmatch.modelos.Pelicula;
import com.aluracursos.listcolectdate.screenmatch.modelos.Serie;
import com.aluracursos.listcolectdate.screenmatch.modelos.Titulo;
import java.util.ArrayList;

public class PriincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("Pulp Fiction", 1994);
        peliculaDeBruno.evalua(10);
        Serie lost = new Serie("Lost", 2000);

        ArrayList<Titulo> lista = new ArrayList<>();
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
    }
}
