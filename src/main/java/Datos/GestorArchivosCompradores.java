package Datos;

import Usuarios.Servicio;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosCompradores {
    //Ventana y consola
    public static void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        GestionArchivos.CrearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;confirmaciones;servicios;");
        for (int i = 0; i < compradores.size(); i++) {
            Usuario comprador = compradores.get(i);
            AñadirCompradorArchivo(comprador);
        }
    }
    public static void AñadirCompradorArchivo(Usuario comprador){
        String nombre = comprador.GetNombre();
        String apellido = comprador.GetApellido();
        String correo = comprador.GetCorreo();
        String rut = comprador.GetRut();
        int numero = comprador.GetNumero();
        String contraseña = comprador.GetContraseña();
        ArrayList<String> confirmaciones = comprador.GetConfirmaciones();
        ArrayList<ArrayList> serv = comprador.GetListaDeListaServicios();

        GestionArchivos.NuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + confirmaciones + ";" + serv + ";");
    }
    public static void CargarCompradoresAPrograma(ArrayList<Usuario> compradores){
        String strCompradores =  GestionArchivos.LeerArchivo("Compradores.csv");

        //Quita las lineas
        strCompradores = strCompradores.replaceAll("\\n", "");

        //Quito los datos innecesarios, que son en el archivo las "categorias" ejemplo: nombre
        ArrayList<String> compraIdoneo = CrearArrayIdoneoCompradores(strCompradores);


        AñadirAListaCompradores(compradores, compraIdoneo);

    }
    public static void AñadirAListaCompradores(ArrayList<Usuario> compradores, ArrayList<String> compraIdoneo){

        for (int i = 0; i < compraIdoneo.size() - 1; i += 8) {
            String nombre = compraIdoneo.get(i);
            String apellido = compraIdoneo.get(i+1);
            String correo = compraIdoneo.get(i+2);
            String rut = compraIdoneo.get(i+3);
            int numero = Integer.parseInt(compraIdoneo.get(i+4));
            String contraseña = compraIdoneo.get(i+5);

            Usuario comprador = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

            //A partir de aqui se agregan los atributos que son arrays y que no estan en el constructor
            String strConfirmaciones = compraIdoneo.get(i+6);
            AgregarConfirmacionesVendedor(strConfirmaciones, comprador);

            String strObjetos = compraIdoneo.get(i+7);
            AgregarServiciosComprador(strObjetos, comprador);

            compradores.add(comprador);
        }
    }
    public static void AgregarConfirmacionesVendedor(String strConfirmaciones, Usuario comprador){
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionesList = Arrays.asList(strConfirmaciones.split(","));

        comprador.AgregarConfExt(confirmacionesList);
    }

    public static void AgregarServiciosComprador(String strObjetos, Usuario comprador){

        strObjetos = strObjetos.replaceAll("\\[", "");
        strObjetos = strObjetos.replaceAll("\\]", "");
        strObjetos = strObjetos.replaceAll(" ", "");

        List<String> listObjetos = Arrays.asList(strObjetos.split(","));

        ArrayList<Servicio> serv = new ArrayList<>();

        if (strObjetos != "") {
            for (int j = 0; j < listObjetos.size() ; j+= 2) {
                String nombre = listObjetos.get(j);
                String descripcion = listObjetos.get(j);

                Servicio servicio = new Servicio(nombre, descripcion);

                serv.add(servicio);
            }
            comprador.AgregarExtServ(serv);
        }
    }
    public static ArrayList<String> CrearArrayIdoneoCompradores(String strCompradores){
        List<String> compradoresList = Arrays.asList(strCompradores.split(";"));
        ArrayList<String> compraDefList = new ArrayList<String>();

        for (int i = 0; i < compradoresList.size(); i++) {
            compraDefList.add(compradoresList.get(i));
        }

        for (int i = 0; i < 8; i++) {
            compraDefList.remove(0);
        }
        return compraDefList;
    }
}
