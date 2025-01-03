package org.VJCC;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {

    private List<String> historialDeConversiones = new ArrayList<>();

    // Función para registrar las conversiones en el historial
    public void registrarConversion(String baseCurrency, String targetCurrency, double amount, double convertedAmount) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String conversion = String.format("(%s) %.2f %s = %.2f %s", timestamp, amount, baseCurrency, convertedAmount, targetCurrency);
        historialDeConversiones.add(conversion);
    }

    // Función para mostrar el historial de conversiones
    public void mostrarHistorial() {
        if (historialDeConversiones.isEmpty()) {
            System.out.println("No hay historial de conversiones.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (String conversion : historialDeConversiones) {
                System.out.println(conversion);
            }
        }
    }
}