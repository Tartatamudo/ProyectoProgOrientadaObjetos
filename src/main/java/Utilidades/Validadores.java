package Utilidades;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static Datos.GestionArchivos.CrearArchivo;
import static Datos.GestionArchivos.LeerArchivo;

public class Validadores {
    private JFrame jFrame = new JFrame();

    public Validadores() {
    }

    //Son los más generales, solo es entero y cadena normales

    //Consola
    public static String GetCadena() {
        Scanner teclado = new Scanner(System.in);
        String cadena = teclado.nextLine();
        return cadena;
    }

    //Consola
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
    //consola
    public static String GetRut(ArrayList<ArrayList> usuarios){

        String rut = "";
        boolean valido = false;
        while (!valido) {

                System.out.println("Ingrese rut");
                rut = GetCadena();// RUT a validar

                    valido = true;//ValidarRutChileno(rut, usuarios)

                    if (valido) {
                        System.out.println("El RUT es valido.");
                    } else {
                        System.out.println("El RUT no es valido o a sido ingresado con anterioridad.");
                    }
            }

        return rut;
    }

    //Ventana y consola
    public boolean ConfirmarUnicidadRut(String rut,ArrayList<ArrayList> usuarios){
        ArrayList<Usuario> compradores = usuarios.get(0);
        ArrayList<Vendedor> vendedores = usuarios.get(1);
        for (int i = 0; i < compradores.size(); i++) {
            if(rut.contains(compradores.get(i).GetRut())){
                return false;
            }
        }
        for (int i = 0; i < vendedores.size(); i++) {
            if(rut.contains(vendedores.get(i).GetRut())){
                return false;
            }
        }
        return true;
    }
    public boolean ValidarRutChileno(String rut, ArrayList<ArrayList> usuarios) {
        // Eliminar puntos y guion del RUT
        rut = rut.replace(".", "").replace("-", "");

        //Confirmar si es la primera vez que se ha utilizado el rut
        if (ConfirmarUnicidadRut(rut, usuarios) == false){
            JOptionPane.showMessageDialog(jFrame, "No puede usar un rut que se haya utilizado con anterioridad");
            return false;
        }
        // Validar el formato del RUT utilizando una expresión regular
        if (!rut.matches("\\d{7,8}[0-9Kk]")) {
            JOptionPane.showMessageDialog(jFrame, "Rut malo");
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

        // Comparar el dígito verificador calculado con el dígito verificador del RUT entregado
        boolean bool = dv == dvEsperado || (dv == 'k' || dv == 'K') && dvEsperado == 'K';
        if (bool == false){
            JOptionPane.showMessageDialog(jFrame, "Rut malo");
        }
        return bool;
    }

    //Obtiene correo de la forma ejemplo@gmail.com hasta que el usuario lo entregue de esa manera
    //Consola
    public static String GetCorreo(ArrayList<ArrayList> usuarios) {
            String correo = "";

            boolean valido = false;
            while (!valido) {
                System.out.println("Ingrese correo");
                correo = GetCadena();// Correo a validar

                valido = true;//ValidarCorreoElectronico(correo, usuarios)

                if (valido) {
                    System.out.println("El correo electronico es valido.");
                } else {
                    System.out.println("El correo electronico no es valido.");
                }

            }
            return correo;
        }

    //Ventana y consola
    public boolean ConfirmarUnicidadCorreo(String correo,ArrayList<ArrayList> usuarios){
        ArrayList<Usuario> compradores = usuarios.get(0);
        ArrayList<Vendedor> vendedores = usuarios.get(1);
        for (int i = 0; i < compradores.size(); i++) {
            if(correo.contains(compradores.get(i).GetCorreo())){
                return false;
            }
        }
        for (int i = 0; i < vendedores.size(); i++) {
            if(correo.contains(vendedores.get(i).GetCorreo())){
                return false;
            }
        }
        return true;
    }

    //Ventana y consola
    public boolean ValidarCorreoElectronico(String correo, ArrayList<ArrayList> usuarios) {
        //Confirmar si es la primera vez que se ha utilizado el rut
        if (ConfirmarUnicidadCorreo(correo, usuarios) == false){
            JOptionPane.showMessageDialog(jFrame, "No puede usar varias veces un mismo correo");
            return false;
        }
        //Confirma si el correo tiene la forma Ejemplo@ejemplo.com, letras, numeros y signos primero,
        // luego va un solo @ despues del arroba letras en minusculas seguido de un solo punto y despues del punto letras en minusculas
        String patron = "^[A-Za-z0-9+_.-]+@[a-z]+[.][A-za-z]+$";

        boolean bool =  Pattern.matches(patron, correo);

        if ( bool == false){
            JOptionPane.showMessageDialog(jFrame, "Correo erroneo");
        }
        return bool;
    }

    //Repite hasta que validador se confirme
    //Consola
    public static int GetNumero(){
        String numero = "";
        int num = 0;

        boolean validar = false;
        while(!validar){

            System.out.println("Ingrese numero");
            numero  = Integer.toString(GetEntero());
            validar = true;//ValidarNumero(numero)

            if (validar){
                num =Integer.parseInt(numero);
                System.out.println("El numero es valido.");
            }else{
                System.out.println("El numero no es valido.");
            }
        }
        return num;
    }

    //Ventana y consola
    public boolean ValidarNumero(String num){
        //Valida que el largo del numero sea si o si de 8 y luego sean solo numeros
        if (!num.matches("\\d{7}[0-9]")) {
            JOptionPane.showMessageDialog(jFrame, "Numero erroneo");
            return false;
        }
        else{
            return  true;
        }
    }

    //Ve si estan los archivos, si no esta, lo crea
    //Ventana y consola
    public static void ValidarArchivos(){
        if(LeerArchivo("vendedores.csv").equals("")){
            CrearArchivo("vendedores.csv","nombre;apellido;correo;rut;numero;contrasena;servicios;");
        }
        if(LeerArchivo("compradores.csv").equals("")){
            CrearArchivo("compradores.csv","nombre;apellido;correo;rut;numero;contrasena;servicios;");
        }
    }
}
