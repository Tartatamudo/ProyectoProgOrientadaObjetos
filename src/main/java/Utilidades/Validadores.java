package Utilidades;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validadores {
    //Son los más generales, solo es entero y cadena normales
    public static String GetCadena() {
        Scanner teclado = new Scanner(System.in);
        String cadena = teclado.nextLine();
        return cadena;
    }

    public static int GetEntero() {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int numero = 0;

        while (!valid) {
            String numStr = scanner.nextLine();

            try {
                numero = Integer.parseInt(numStr);
                valid = true; // Si no se produce una excepción, el número es válido
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
            }
        }
        return numero;
    }

    //Obtiene rut de la forma 12345678-9 o 1234567-8, no importa cuantos puntos o guiones se pongan, ya que tod0 eso será eliminado
    public static String GetRut(){
        String rut = "";
        boolean valido = false;
        while (!valido){
            System.out.println("Ingrese rut");
            rut  = GetCadena();// RUT a validar

            valido = validarRutChileno(rut);

            if (valido) {
                System.out.println("El RUT es valido.");
            } else {
                System.out.println("El RUT no es valido.");
            }
        }
        return rut;
    }
    public static boolean validarRutChileno(String rut) {
        // Eliminar puntos y guion del RUT
        rut = rut.replace(".", "").replace("-", "");

        // Validar el formato del RUT utilizando una expresión regular
        if (!rut.matches("\\d{7,8}[0-9Kk]")) {
            return false;
        }

        // Obtener el dígito verificador y el número sin el dígito
        char dv = rut.charAt(rut.length() - 1);
        int num = Integer.parseInt(rut.substring(0, rut.length() - 1));

        // Calcular el dígito verificador esperado
        int m = 0;
        int s = 1;

        for (; num != 0; num /= 10) {
            s = (s + num % 10 * (9 - m++ % 6)) % 11;
        }

        char dvEsperado = (char) (s != 0 ? s + 47 : 75);

        // Comparar el dígito verificador calculado con el dígito verificador del RUT
        return dv == dvEsperado || (dv == 'k' || dv == 'K') && dvEsperado == 'K';
    }

    //Obtiene correo de la forma ejemplo@gmail.com, o sea al principio tiene que tener letras, digitos o lo que sea, después tiene que haber un @ luego letras en
    //minusculas seguidos de un punto y luego más letras minusculas
    public static String GetCorreo(){
        String correo = "";

        boolean valido = false;
        while(!valido){
            System.out.println("Ingrese correo");
            correo = GetCadena();// Correo a validar

            valido = ValidarCorreoElectronico(correo);

            if (valido) {
                System.out.println("El correo electronico es valido.");
            } else {
                System.out.println("El correo electronico no es valido.");
            }

        }
        return correo;
    }

    public static boolean ValidarCorreoElectronico(String correo) {
        String patron = "^[A-Za-z0-9+_.-]+@[a-z]+.[a-z]+$";
        return Pattern.matches(patron, correo);
    }

    //Obtiene solo numeros con un largo de 8
    public static int GetNumero(){
        String numero = "";
        int num = 0;

        boolean validar = false;
        while(!validar){

            System.out.println("Ingrese numero");
            numero  = Integer.toString(GetEntero());
            validar = ValidarNumero(numero);

            if (validar){
                num =Integer.parseInt(numero);
                System.out.println("El numero es valido.");
            }else{
                System.out.println("El numero no es valido.");
            }
        }
        return num;
    }
    public static boolean ValidarNumero(String num){

        if (!num.matches("\\d{7}[0-9]")) {
            return false;
        }
        else{
            return  true;
        }
    }
}
