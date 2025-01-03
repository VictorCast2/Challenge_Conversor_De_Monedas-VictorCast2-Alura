package org.VJCC;

import com.google.gson.*;
import java.io.FileWriter;

public class CurrencyParser {

    // Función para obtener la tasa de conversión de una moneda destino
    public static double obtenerTasa(String targetCurrency, String jsonResponse) {
        try {
            // Parsear la respuesta JSON
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            // Obtener la tasa de conversión de la moneda destino
            if (conversionRates.has(targetCurrency)) {
                return conversionRates.get(targetCurrency).getAsDouble();
            } else {
                throw new IllegalArgumentException("Moneda destino no encontrada en los datos de conversión.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tasa de conversión: " + e.getMessage(), e);
        }
    }

    // Función para guardar las tasas filtradas en un archivo
    public static void guardarTasasFiltradasEnArchivo(String jsonResponse) {
        try {
            // Parsear la respuesta JSON
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            // Crear un objeto JSON solo con las monedas de interés
            JsonObject tasasFiltradas = new JsonObject();
            for (Monedas moneda : Monedas.values()) {
                if (conversionRates.has(moneda.name())) {
                    tasasFiltradas.addProperty(moneda.name(), conversionRates.get(moneda.name()).getAsDouble());
                }
            }

            // Guardar las tasas filtradas en un archivo
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter archivo = new FileWriter("TasasFiltradas.Json")) {
                gson.toJson(tasasFiltradas, archivo);
            }
            System.out.println("¡Tasas filtradas guardadas exitosamente en 'TasasFiltradas.Json'!");
        } catch (Exception e) {
            throw new CurrencyAPIException("Error al guardar las tasas filtradas en el archivo.", e);
        }
    }
}