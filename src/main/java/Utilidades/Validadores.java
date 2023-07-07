package Utilidades;

import Datos.GestionArchivos;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Validadores {
    public Validadores() {
    }
    //Obtiene rut de la forma 12345678-9 o 1234567-8, no importa cuantos puntos o guiones se pongan, ya que tod0 eso será eliminado
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
    public boolean validarFormatoRut(String rut) {
        // Validar el formato del RUT utilizando una expresión regular
        if (!rut.matches("\\d{7,8}[0-9Kk]")) {
            return false;
        }

        return true;
    }
    public boolean ValidarRut(String rut, ArrayList<ArrayList> usuarios) {
        // Eliminar puntos y guion del RUT
        rut = rut.replace(".", "").replace("-", "");

        //Confirmar si es la primera vez que se ha utilizado el rut
        if (ConfirmarUnicidadRut(rut, usuarios) == false){
            return false;
        }
        // Validar el formato del RUT utilizando una expresión regular
        if (!rut.matches("\\d{7,8}[0-9Kk]")) {;
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
        }
        return bool;
    }

    //Obtiene correo de la forma ejemplo@gmail.com hasta que el usuario lo entregue de esa manera

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

    public boolean ValidarCorreoElectronico(String correo, ArrayList<ArrayList> usuarios) {
        //Confirmar si es la primera vez que se ha utilizado el rut
        if (ConfirmarUnicidadCorreo(correo, usuarios) == false){
            return false;
        }
        //Confirma si el correo tiene la forma Ejemplo@ejemplo.com, letras, numeros y signos primero,
        // luego va un solo @ despues del arroba letras en minusculas seguido de un solo punto y despues del punto letras en minusculas
        String patron = "^[A-Za-z0-9+_.-]+@[a-z]+[.][A-za-z]+$";

        boolean bool =  Pattern.matches(patron, correo);

        if ( bool == false){
        }
        return bool;
    }

    //Repite hasta que validador se confirme
    public boolean ValidarNumero(String num){
        //Valida que el largo del numero sea si o si de 8 y luego sean solo numeros
        if (!num.matches("\\d{7}[0-9]")) {
            return false;
        }
        else{
            return  true;
        }
    }

    //Ve si estan los archivos, si no esta, lo crea
    public void ValidarArchivos(){
        GestionArchivos gestionArchivos = new GestionArchivos();
        if(gestionArchivos.LeerArchivo("vendedores.csv").equals("")){
            gestionArchivos.CrearArchivo("vendedores.csv","nombre;apellido;correo;rut;numero;contrasena;servicios;");
        }
        if(gestionArchivos.LeerArchivo("compradores.csv").equals("")){
            gestionArchivos.CrearArchivo("compradores.csv","nombre;apellido;correo;rut;numero;contrasena;servicios;");
        }
    }
}
