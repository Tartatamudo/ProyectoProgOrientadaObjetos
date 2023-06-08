package Utilidades;

import Usuarioss.Servicio;
import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosCompradores {

    public static void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        GestionArchivos.crearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;servicios;");
        for (int i = 0; i < compradores.size(); i++) {
            String nombre = compradores.get(i).getNombre();
            String apellido = compradores.get(i).getApellido();
            String correo = compradores.get(i).getCorreo();
            String rut = compradores.get(i).getRut();
            int numero = compradores.get(i).getNumero();
            String contraseña = compradores.get(i).getContraseña();
            ArrayList<ArrayList> serv = compradores.get(i).getListaDeListaServicios();

            GestionArchivos.nuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + serv + ";");
        }
    }
    public static void AñadirCompradorArchivo(ArrayList<Usuario> compradores){
        int largo = compradores.size()-1;
        String nombre = compradores.get(largo).getNombre();
        String apellido = compradores.get(largo).getApellido();
        String correo = compradores.get(largo).getCorreo();
        String rut = compradores.get(largo).getRut();
        int numero = compradores.get(largo).getNumero();
        String contraseña = compradores.get(largo).getContraseña();
        ArrayList<ArrayList> serv = compradores.get(largo).getListaDeListaServicios();

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
