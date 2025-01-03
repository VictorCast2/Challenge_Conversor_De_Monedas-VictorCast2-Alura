package org.VJCC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar menú principal
            System.out.println(Menu());
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        // Mostrar submenú de conversor de monedas
                        mostrarMenuConversorMonedas(scanner);
                        break;

                    case 2:
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
                2. Salir
                *<------->*                  *<------->*
                """;
    }

    // Submenú de Conversor de Monedas
    public static void mostrarMenuConversorMonedas(Scanner scanner) {
        System.out.println(MenuConversorMonedas());
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                // Realizar conversión de monedas
                conversorMonedas(scanner);
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
    public static void conversorMonedas(Scanner scanner) {
        try {
            // Mostrar las monedas disponibles
            System.out.println("Monedas disponibles:");
            System.out.println("ARS - Peso argentino");
            System.out.println("BOB - Boliviano boliviano");
            System.out.println("BRL - Real brasileño");
            System.out.println("CLP - Peso chileno");
            System.out.println("COP - Peso colombiano");
            System.out.println("USD - Dólar estadounidense");

            // Ingresar moneda base
            String baseCurrency = obtenerMonedaValida(scanner, "Ingresa la moneda base (Ejemplo: USD): ");

            // Ingresar moneda destino
            String targetCurrency = obtenerMonedaValida(scanner, "Ingresa la moneda destino (Ejemplo: EUR): ");

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

        } catch (Exception e) {
            System.out.println("Ocurrió un error al realizar la conversión: " + e.getMessage());
        }
    }

    // Función para obtener una moneda válida
    public static String obtenerMonedaValida(Scanner scanner, String mensaje) {
        String moneda;
        while (true) {
            System.out.print(mensaje);
            moneda = scanner.next().toUpperCase();
            if (esMonedaValida(moneda)) {
                break;
            } else {
                System.out.println("Moneda no válida. Por favor, ingresa una moneda válida de las siguientes: ARS, BOB, BRL, CLP, COP, USD.");
            }
        }
        return moneda;
    }

    // Función para validar si la moneda ingresada es válida
    public static boolean esMonedaValida(String moneda) {
        return moneda.equals("ARS") || moneda.equals("BOB") || moneda.equals("BRL") ||
                moneda.equals("CLP") || moneda.equals("COP") || moneda.equals("USD");
    }

}