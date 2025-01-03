package org.VJCC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversionHistory historial = new ConversionHistory();

        while (true) {
            // Mostrar menú principal
            System.out.println(Menu());
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        // Mostrar submenú de conversor de monedas
                        mostrarMenuConversorMonedas(scanner, historial);
                        break;

                    case 2:
                        // Mostrar historial de conversiones
                        historial.mostrarHistorial();
                        break;

                    case 3:
                        // Salir
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    public static String Menu() {
        return """
                *<------->* Menú de Opciones *<------->*
                1. Conversor de Monedas
                2. Ver Historial de Conversiones
                3. Salir
                *<------->*                  *<------->*
                """;
    }

    // Submenú de Conversor de Monedas
    public static void mostrarMenuConversorMonedas(Scanner scanner, ConversionHistory historial) {
        System.out.println(MenuConversorMonedas());
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                // Realizar conversión de monedas
                conversorMonedas(scanner, historial);
                break;
            case 2:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("Opción no válida. Regresando al menú principal...");
        }
    }

    public static String MenuConversorMonedas() {
        return """
                *<------->* Menú Conversor de Monedas *<------->*
                1. Realizar conversión
                2. Regresar al menú principal
                *<------->*                          *<------->*
                """;
    }

    // Función para realizar la conversión de monedas
    public static void conversorMonedas(Scanner scanner, ConversionHistory historial) {
        try {
            // Mostrar las monedas disponibles
            MonedaValidator.mostrarMonedasDisponibles();

            // Ingresar moneda base
            String baseCurrency = MonedaValidator.obtenerMonedaValida(scanner, "Ingresa la moneda base (Ejemplo: USD): ");

            // Ingresar moneda destino
            String targetCurrency = MonedaValidator.obtenerMonedaValida(scanner, "Ingresa la moneda destino (Ejemplo: EUR): ");

            // Ingresar cantidad a convertir
            System.out.print("Ingresa la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            // Obtener respuesta de la API
            String jsonResponse = CurrencyAPI.tasaObtenccion(baseCurrency);

            // Obtener la tasa de conversión usando CurrencyParser
            double rate = CurrencyParser.obtenerTasa(targetCurrency, jsonResponse);

            // Realizar la conversión
            double convertedAmount = amount * rate;

            // Mostrar el resultado
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

            // Guardar tasas filtradas en archivo
            CurrencyParser.guardarTasasFiltradasEnArchivo(jsonResponse);

            // Registrar la conversión en el historial
            historial.registrarConversion(baseCurrency, targetCurrency, amount, convertedAmount);

        } catch (CurrencyAPIException e) {
            System.out.println("Error con la API: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error al realizar la conversión: " + e.getMessage());
        }
    }
}