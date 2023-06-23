package Datos;

import Usuarioss.Servicio;
import Usuarioss.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosCompradores {

    public static void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        GestionArchivos.crearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;confirmaciones;servicios;");
        for (int i = 0; i < compradores.size(); i++) {
            Usuario comprador = compradores.get(i);
            AñadirCompradorArchivo(comprador);
        }
    }
    public static void AñadirCompradorArchivo(Usuario comprador){
        String nombre = comprador.getNombre();
        String apellido = comprador.getApellido();
        String correo = comprador.getCorreo();
        String rut = comprador.getRut();
        int numero = comprador.getNumero();
        String contraseña = comprador.getContraseña();
        ArrayList<String> confirmaciones = comprador.GetConfirmaciones();
        ArrayList<ArrayList> serv = comprador.getListaDeListaServicios();

        GestionArchivos.nuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + confirmaciones + ";" + serv + ";");
    }
    public static void CargarCompradoresAPrograma(ArrayList<Usuario> compradores){
        String compradors =  GestionArchivos.leerArchivo("Compradores.csv");

        compradors = compradors.replaceAll("\\n", "");

        ArrayList<String> compra = CrearArrayIdoneoCompradores(compradors);
        AñadirAListaCompradores(compradores, compra);

    }
    public static void AñadirAListaCompradores(ArrayList<Usuario> compradores, ArrayList<String> compra){

        for (int i = 0; i < compra.size() - 1; i += 8) {
            String nombre = compra.get(i);
            String apellido = compra.get(i+1);
            String correo = compra.get(i+2);
            String rut = compra.get(i+3);
            int numero = Integer.parseInt(compra.get(i+4));
            String contraseña = compra.get(i+5);

            Usuario com = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

            //A partir de aqui se agregan los atributos que son arrays
            String strConfirmaciones = compra.get(i+6);
            AgregarConfirmacionesVendedor(strConfirmaciones, com);

            String strObjetos = compra.get(i+7);
            AgregarServiciosComprador(strObjetos, com);

            compradores.add(com);
        }
    }
    public static void AgregarConfirmacionesVendedor(String strConfirmaciones, Usuario comprador){
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionList = Arrays.asList(strConfirmaciones.split(","));

        comprador.AgregarConfExt(confirmacionList);
    }

    public static void AgregarServiciosComprador(String strObjetos, Usuario com){

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
    }
    public static ArrayList<String> CrearArrayIdoneoCompradores(String compradors){
        List<String> comp = Arrays.asList(compradors.split(";"));
        ArrayList<String> compra = new ArrayList<String>();

        for (int i = 0; i < comp.size(); i++) {
            compra.add(comp.get(i));
        }

        for (int i = 0; i < 8; i++) {
            compra.remove(0);
        }
        return compra;
    }
}
