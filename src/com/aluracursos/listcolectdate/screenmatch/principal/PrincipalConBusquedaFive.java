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

public class PrincipalConBusquedaFive {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escribe el título de la película: ");
        var busqueda = lectura.nextLine().trim();

        if (busqueda.isEmpty()) {
            System.out.println("El título no puede estar vacío. Inténtalo de nuevo.");
            return;
        }

        try {
            // Codificar la búsqueda para que sea compatible con la URL
            String busquedaCodificada = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
            // Generar la URL con el título codificado
            String direccion = "https://www.omdbapi.com/?t=" + busquedaCodificada + "&apikey=c1bd64c";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error en la solicitud: Código de estado " + response.statusCode());
                return;
            }

            String json = response.body();

            if (json.contains("\"Response\":\"False\"")) {
                System.out.println("Película no encontrada. Inténtalo de nuevo.");
                return;
            }

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(miTituloOmdb);

            if (miTituloOmdb.title() == null || miTituloOmdb.title().isEmpty()) {
                System.out.println("No se encontraron datos válidos para la película.");
                return;
            }

            Titulo miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Titulo ya convertido: " + miTitulo);

        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI. Verifica la dirección ingresada: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la solicitud. Inténtalo más tarde: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Finalizó la ejecución del programa.");
    }
}