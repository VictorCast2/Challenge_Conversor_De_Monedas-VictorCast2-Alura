package org.VJCC;

import java.util.Scanner;

public class MonedaValidator {

    // Mostrar las monedas disponibles
    public static void mostrarMonedasDisponibles() {
        System.out.println("Monedas disponibles:");
        System.out.println("ARS - Peso argentino");
        System.out.println("BOB - Boliviano boliviano");
        System.out.println("BRL - Real brasileño");
        System.out.println("CLP - Peso chileno");
        System.out.println("COP - Peso colombiano");
        System.out.println("USD - Dólar estadounidense");
        System.out.println("EUR - Euro");
        System.out.println("GBP - Libra esterlina");
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
                System.out.println("Moneda no válida. Por favor, ingresa una moneda válida de las siguientes: ARS, BOB, BRL, CLP, COP, USD, EUR, GBP.");
            }
        }
        return moneda;
    }

    // Función para validar si la moneda ingresada es válida
    public static boolean esMonedaValida(String moneda) {
        return moneda.equals("ARS") || moneda.equals("BOB") || moneda.equals("BRL") ||
                moneda.equals("CLP") || moneda.equals("COP") || moneda.equals("USD") ||
                moneda.equals("EUR") || moneda.equals("GBP");
    }

}