package com.aluracursos.listcolectdate.screenmatch.principal;

import com.aluracursos.listcolectdate.screenmatch.modelos.Titulo;
import com.aluracursos.listcolectdate.screenmatch.modelos.TituloOmdb;
import com.aluracursos.listcolectdate.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(true){
            System.out.println("Escribe el título de la película: ");
            var busqueda = lectura.nextLine();

            if (busqueda.trim().isEmpty()) {
                System.out.println("El título no puede estar vacío. Inténtalo de nuevo.");
                continue;
            }


            if(busqueda.equalsIgnoreCase("Salir")){
                break;
            }

            String direccion = "https://www.omdbapi.com/?t=" +
                    busqueda.replace(" ", "+") +
                    "&apikey=c1bd64c";
            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(response.body());
                ;

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb);

                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " + miTitulo);

//                FileWriter escritura = new FileWriter("pelicula.txt");
//                escritura.write(miTitulo.toString());
//                escritura.close();

                titulos.add(miTitulo);

                // Guardar en un archivo CSV
                String csv = "peliculas.csv";
                try (FileWriter writer = new FileWriter(csv, true)) {
                    writer.append(miTitulo.toString()).append("\n");
                } catch (IOException e) {
                    System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
                }

            }catch (NumberFormatException e){
                System.out.println("ocurrio un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la direccion. ");
            }catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizo la ejecucion del programa!");
    }
}