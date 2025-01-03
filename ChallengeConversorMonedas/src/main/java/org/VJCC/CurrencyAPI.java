package org.VJCC;

import java.net.URI;
import java.net.http.*;

public class CurrencyAPI {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    private static final String API_Key = "74da5d0c5ce36190cac450d4";

    public static String tasaObtenccion(String baseCurrency) throws CurrencyAPIException {
        String apiUrl = API_URL + API_Key + "/latest/" + baseCurrency;
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new CurrencyAPIException("Error al conectar con la API: " + e.getMessage(), e);
        }
        if (response.statusCode() != 200) {
            throw new CurrencyAPIException("No se pudieron obtener los datos. Código HTTP:" + response.statusCode(), null);
        }
        return response.body();
    }

}