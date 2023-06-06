package Utilidades;

import Usuarioss.Servicio;
import Usuarioss.Vendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosVendedores {
    private ArrayList<Vendedor> vendedores;

    public GestorArchivosVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public static void AñadirVendedoresArchivo(ArrayList<Vendedor> vendedores){
        GestionArchivos.crearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;servicios;");
        for (int i = 0; i < vendedores.size(); i++) {
            String nombre = vendedores.get(i).getNombre();
            String apellido = vendedores.get(i).getApellido();
            String correo = vendedores.get(i).getCorreo();
            String rut = vendedores.get(i).getRut();
            String contraseña = vendedores.get(i).getContraseña();
            int numero = vendedores.get(i).getNumero();

            ArrayList<ArrayList> serv = vendedores.get(i).getListaDeListaServicios();

            GestionArchivos.nuevaLinea("vendedores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + contraseña + ";" + numero + ";" + serv + ";");
        }
    }
    public static void AñadirVendedorArchivo(ArrayList<Vendedor> vendedores){
        int largo = vendedores.size()-1;
        String nombre = vendedores.get(largo).getNombre();
        String apellido = vendedores.get(largo).getApellido();
        String correo = vendedores.get(largo).getCorreo();
        String rut = vendedores.get(largo).getRut();
        String contraseña = vendedores.get(largo).getContraseña();
        int numero = vendedores.get(largo).getNumero();

        ArrayList<ArrayList> serv = vendedores.get(largo).getListaDeListaServicios();

        GestionArchivos.nuevaLinea("vendedores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + contraseña + ";" + numero + ";" + serv + ";");
    }

    public static void CargarVendedoresAPrograma(ArrayList<Vendedor> vendedores){
        String vendedors =  GestionArchivos.leerArchivo("vendedores.csv");

        vendedors = vendedors.replaceAll("\\n", "");

        ArrayList<String> vende = CrearArrayIdoneoVendedores(vendedors);

        AñadirAListaVendedores(vendedores, vende);

    }

    public static void AñadirAListaVendedores(ArrayList<Vendedor> vendedores, ArrayList<String> vende){
        for (int i = 0; i < vende.size(); i += 7) {
            String nombre = vende.get(i);
            String apellido = vende.get(i+1);
            String correo = vende.get(i+2);
            String rut = vende.get(i+3);
            int numero = Integer.parseInt(vende.get(i+4));
            String contraseña = vende.get(i+5);

            Vendedor ven = new Vendedor(nombre,apellido, correo, rut, contraseña, numero);

            String strObjetos = vende.get(i+6);
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
                ven.agregarExtServ(serv);
            }
            vendedores.add(ven);
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
