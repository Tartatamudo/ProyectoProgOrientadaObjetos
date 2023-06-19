package Datos;

import Usuarioss.Servicio;
import Usuarioss.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosCompradores {

    public static void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        GestionArchivos.crearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;servicios;");
        for (int i = 0; i < compradores.size(); i++) {
            Usuario comprador = compradores.get(i);
            String nombre = comprador.getNombre();
            String apellido = comprador.getApellido();
            String correo = comprador.getCorreo();
            String rut = comprador.getRut();
            int numero = comprador.getNumero();
            String contraseña = comprador.getContraseña();
            ArrayList<ArrayList> serv = comprador.getListaDeListaServicios();

            GestionArchivos.nuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + serv + ";");
        }
    }
    public static void AñadirCompradorArchivo(Usuario comprador){
        String nombre = comprador.getNombre();
        String apellido = comprador.getApellido();
        String correo = comprador.getCorreo();
        String rut = comprador.getRut();
        int numero = comprador.getNumero();
        String contraseña = comprador.getContraseña();
        ArrayList<ArrayList> serv = comprador.getListaDeListaServicios();

        GestionArchivos.nuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + serv + ";");
    }
    public static void CargarCompradoresAPrograma(ArrayList<Usuario> compradores){
        String vendedors =  GestionArchivos.leerArchivo("Compradores.csv");

        vendedors = vendedors.replaceAll("\\n", "");

        ArrayList<String> compra = CrearArrayIdoneoVendedores(vendedors);

        AñadirAListaCompradores(compradores, compra);

    }
    public static void AñadirAListaCompradores(ArrayList<Usuario> compradores, ArrayList<String> compra){

        for (int i = 0; i < compra.size() - 1; i += 7) {
            String nombre = compra.get(i);
            String apellido = compra.get(i+1);
            String correo = compra.get(i+2);
            String rut = compra.get(i+3);
            int numero = Integer.parseInt(compra.get(i+4));
            String contraseña = compra.get(i+5);

            Usuario com = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

            String strObjetos = compra.get(i+6);
            strObjetos = strObjetos.replaceAll("\\[", "");
            strObjetos = strObjetos.replaceAll("\\]", "");
            strObjetos = strObjetos.replaceAll(" ", "");

            List<String> listObjetos = Arrays.asList(strObjetos.split(","));

            ArrayList<Servicio> serv = new ArrayList<>();
            if (strObjetos != "") {
                for (int j = 0; j < listObjetos.size() ; j+= 2) {
                    Servicio ser = new Servicio();
                    ser.setExtNombre(listObjetos.get(j));
                    ser.setExtDescricion(listObjetos.get(j+1));
                    serv.add(ser);
                }
                com.agregarExtServ(serv);
            }
            compradores.add(com);
        }
    }
    public static ArrayList<String> CrearArrayIdoneoVendedores(String vendedors){
        List<String> vend = Arrays.asList(vendedors.split(";"));
        ArrayList<String> vende = new ArrayList<String>();

        for (int i = 0; i < vend.size(); i++) {
            vende.add(vend.get(i));
        }

        for (int i = 0; i <7; i++) {
            vende.remove(0);
        }
        return vende;
    }
}
