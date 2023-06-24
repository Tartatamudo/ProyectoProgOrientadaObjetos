package Datos;

import Usuarioss.Servicio;
import Usuarioss.Vendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosVendedores {

    public static void AñadirVendedoresArchivo(ArrayList<Vendedor> vendedores){
        GestionArchivos.crearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;estrellas;comentarios;confirmaciones;servicios;");
        for (int i = 0; i < vendedores.size(); i++) {
            Vendedor vendedor = vendedores.get(i);

            AñadirVendedorArchivo(vendedor);
        }
    }
    public static void AñadirVendedorArchivo(Vendedor vendedor){
        String nombre = vendedor.getNombre();
        String apellido = vendedor.getApellido();
        String correo = vendedor.getCorreo();
        String rut = vendedor.getRut();
        int numero = vendedor.getNumero();
        String contraseña = vendedor.getContraseña();
        ArrayList<Integer> estrellas = vendedor.GetEstrellas();
        ArrayList<String> comentarios = vendedor.GetComentarios();
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();
        ArrayList<ArrayList> serv = vendedor.getListaDeListaServicios();

        GestionArchivos.nuevaLinea("vendedores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + estrellas + ";" + comentarios + ";" + confirmaciones + ";" + serv + ";");
    }
    public static void CargarVendedoresAPrograma(ArrayList<Vendedor> vendedores){
        String vendedors =  GestionArchivos.leerArchivo("vendedores.csv");

        vendedors = vendedors.replaceAll("\\n", "");

        ArrayList<String> vende = CrearArrayIdoneoVendedores(vendedors);

        AñadirAListaVendedores(vendedores, vende);

    }
    public static void AñadirAListaVendedores(ArrayList<Vendedor> vendedores, ArrayList<String> vende){
        for (int i = 0; i < vende.size() - 1; i += 10) {

            String nombre = vende.get(i);
            String apellido = vende.get(i+1);
            String correo = vende.get(i+2);
            String rut = vende.get(i+3);
            int numero = Integer.parseInt(vende.get(i+4));
            String contraseña = vende.get(i+5);

            Vendedor ven = new Vendedor(nombre, apellido, correo, rut,numero, contraseña);

            //A partir de aqui se agregan los atributos que son arrays
            String strEstrellas = vende.get(i+6);
            AgregarEstrellasVendedor(strEstrellas, ven);

            String strComentarios = vende.get(i+7);
            AgregarComenentariosVendedor(strComentarios, ven);

            String strConfirmaciones = vende.get(i+8);
            AgregarConfirmacionesVendedor(strConfirmaciones,ven);

            String strObjetos = vende.get(i+9);
            AgregarServiciosVendedor(strObjetos, ven);

            vendedores.add(ven);
        }
    }
    public static void AgregarConfirmacionesVendedor(String strConfirmaciones, Vendedor vendedor){
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionList = Arrays.asList(strConfirmaciones.split(","));

        vendedor.AgregarConfExt(confirmacionList);
    }
    public static void AgregarComenentariosVendedor(String strComentarios, Vendedor vendedor){
        strComentarios = strComentarios.replaceAll("\\[", "");
        strComentarios = strComentarios.replaceAll("\\]", "");
        strComentarios = strComentarios.replaceAll(" ", "");

        List<String> comentariosList = Arrays.asList(strComentarios.split(","));

        vendedor.AgregarComExt(comentariosList);
    }
    public static void AgregarEstrellasVendedor(String strEstrellas, Vendedor vendedor){
        strEstrellas = strEstrellas.replaceAll("\\[", "");
        strEstrellas = strEstrellas.replaceAll("\\]", "");
        strEstrellas = strEstrellas.replaceAll(" ", "");

        List<String> estrellasList = Arrays.asList(strEstrellas.split(","));

        ArrayList<Integer> estrellasListInt = new ArrayList<Integer>();
        if(estrellasList.get(0) != ""){
            for (int i = 0; i < estrellasList.size(); i++) {
                estrellasListInt.add(Integer.parseInt(estrellasList.get(i)));
            }
            vendedor.AgregarEstExt(estrellasListInt);
        }

    }

    public static void AgregarServiciosVendedor(String strObjetos, Vendedor ven){

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
    }
    public static ArrayList<String> CrearArrayIdoneoVendedores(String vendedors){
        List<String> vend = Arrays.asList(vendedors.split(";"));
        ArrayList<String> vende = new ArrayList<String>();

        for (int i = 0; i < vend.size(); i++) {
            vende.add(vend.get(i));
        }

        for (int i = 0; i < 10; i++) {
            vende.remove(0);
        }

        return vende;
    }
}
