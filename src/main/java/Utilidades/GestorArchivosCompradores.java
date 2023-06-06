package Utilidades;

import Usuarioss.Servicio;
import Usuarioss.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosCompradores {

    public static void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        GestionArchivos.crearArchivo("compradores.csv", "nombre;apellido;correo;rut;contrasena;servicios;");
        for (int i = 0; i <compradores.size(); i++) {
            String nombre = compradores.get(i).getNombre();
            String apellido = compradores.get(i).getApellido();
            String correo = compradores.get(i).getCorreo();
            String rut = compradores.get(i).getRut();
            String contraseña = compradores.get(i).getContraseña();
            int numero = compradores.get(i).getNumero();
            ArrayList<ArrayList> serv = compradores.get(i).getListaDeListaServicios();

            GestionArchivos.nuevaLinea("compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + contraseña + ";" + numero + ";" + serv + ";");
        }
    }

    public static void AñadirCompradorArchivo(ArrayList<Usuario> compradores){
        int largo = compradores.size()-1;
        String nombre = compradores.get(largo).getNombre();
        String apellido = compradores.get(largo).getApellido();
        String correo = compradores.get(largo).getCorreo();
        String rut = compradores.get(largo).getRut();
        String contraseña = compradores.get(largo).getContraseña();
        int numero = compradores.get(largo).getNumero();
        ArrayList<ArrayList> serv = compradores.get(largo).getListaDeListaServicios();

        GestionArchivos.nuevaLinea("compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + contraseña + ";" + numero + ";" + serv + ";");
    }

    public static void CargarCompradoresAPrograma(ArrayList<Usuario> compradores){
        String compradors =  GestionArchivos.leerArchivo("compradores.csv");

        compradors =compradors.replaceAll("\\n", "");

        ArrayList<String> compra = CrearArrayIdoneoCompradores(compradors);

        AñadirAListaCompradores(compradores, compra);

    }
    public static ArrayList<String> CrearArrayIdoneoCompradores(String compradors){
        List<String> vend = Arrays.asList(compradors.split(";"));
        ArrayList<String> compra = new ArrayList<String>();

        for (int i = 0; i < vend.size(); i++) {
            compra.add(vend.get(i));
        }

        for (int i = 0; i <6; i++) {
            compra.remove(0);
        }
        return compra;
    }
    public static void AñadirAListaCompradores(ArrayList<Usuario> compradores, ArrayList<String> compra){
        for (int i = 0; i < compra.size() - 1; i += 7) {
            String nombre = compra.get(i);
            String apellido = compra.get(i+1);
            String correo = compra.get(i+2);
            String rut = compra.get(i+3);
            String contraseña = compra.get(i+4);
            int numero = Integer.parseInt(compra.get(i+5));

            Usuario com = new Usuario(nombre,apellido, correo, rut, contraseña, numero);

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
}
