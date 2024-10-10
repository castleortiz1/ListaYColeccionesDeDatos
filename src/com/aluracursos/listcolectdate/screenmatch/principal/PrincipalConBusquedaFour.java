package com.aluracursos.listcolectdate.screenmatch.principal;


import com.aluracursos.listcolectdate.screenmatch.modelos.Titulo;
import com.aluracursos.listcolectdate.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PrincipalConBusquedaFour {
    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        System.out.println("Escribe el título de la película: ");
        var busqueda = lectura.nextLine().trim(); // Elimina espacios en blanco adicionales

        if (busqueda.isEmpty()) {
            System.out.println("El título no puede estar vacío. Inténtalo de nuevo.");
            return;
        }

        try {
            // Codificar la búsqueda para que sea compatible con la URL
            String busquedaCodificada = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
            // Generar la URL con el título codificado
            String direccion = "https://www.omdbapi.com/?t=" + busquedaCodificada + "&apikey=c1bd64c";

            // Hacer la solicitud HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la respuesta es exitosa (código 200)
            if (response.statusCode() != 200) {
                System.out.println("Error en la solicitud: Código de estado " + response.statusCode());
                return;
            }

            String json = response.body();

            // Verificar si la respuesta contiene un error (por ejemplo, "Movie not found")
            if (json.contains("\"Error\":\"Movie not found!\"")) {
                System.out.println("Película no encontrada. Inténtalo de nuevo.");
                return;
            }

            // Procesar la respuesta JSON
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(miTituloOmdb);

            // Verificar si los datos devueltos son válidos
            if (miTituloOmdb.title() == null || miTituloOmdb.title().isEmpty()) {
                System.out.println("No se encontraron datos válidos para la película.");
                return;
            }

            Titulo miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Titulo ya convertido: " + miTitulo);

        } catch (NumberFormatException e) {
            System.out.println("Error de formato de número: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI. Verifica la dirección ingresada.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la solicitud. Inténtalo más tarde.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace(); // Agrega esto para ayudar en el diagnóstico
        }

        System.out.println("Finalizó la ejecución del programa.");
    }
}
