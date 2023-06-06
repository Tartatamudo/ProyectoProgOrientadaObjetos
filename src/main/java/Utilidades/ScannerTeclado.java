package Utilidades;

import java.util.Scanner;

public class ScannerTeclado {
    public static String getCadena() {
        Scanner teclado = new Scanner(System.in);
        String cadena = teclado.nextLine();
        return cadena;
    }

    public static int getNumero() {
        Scanner teclado = new Scanner(System.in);
        int numero = teclado.nextInt();
        return numero;
    }
}
