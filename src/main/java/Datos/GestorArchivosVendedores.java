package Datos;

import Usuarios.Servicio;
import Usuarios.Vendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivosVendedores {
    public static void AñadirVendedoresArchivo(ArrayList<Vendedor> vendedores) {
        GestionArchivos.CrearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;estrellas;comentarios;confirmaciones;servicios;");
        for (int i = 0; i < vendedores.size(); i++) {
            Vendedor vendedor = vendedores.get(i);
            AñadirVendedorArchivo(vendedor);
        }
    }

    public static void AñadirVendedorArchivo(Vendedor vendedor) {
        String nombre = vendedor.GetNombre();
        String apellido = vendedor.GetApellido();
        String correo = vendedor.GetCorreo();
        String rut = vendedor.GetRut();
        int numero = vendedor.GetNumero();
        String contraseña = vendedor.GetContraseña();
        ArrayList<Integer> estrellas = vendedor.GetEstrellas();
        ArrayList<String> comentarios = vendedor.GetComentarios();
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        ArrayList<ArrayList> serv = vendedor.GetListaDeListaServicios();

        GestionArchivos.NuevaLinea("vendedores.csv", nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + estrellas + ";" + comentarios + ";" + confirmaciones + ";" + serv + ";");
    }

    public static void CargarVendedoresAPrograma(ArrayList<Vendedor> vendedores) {
        String strVendedores = GestionArchivos.LeerArchivo("vendedores.csv");
        strVendedores = strVendedores.replaceAll("\\n", "");
        ArrayList<String> vendeIdoneo = CrearArrayIdoneoVendedores(strVendedores);
        AñadirAListaVendedores(vendedores, vendeIdoneo);
    }

    public static void AñadirAListaVendedores(ArrayList<Vendedor> vendedores, ArrayList<String> vendeIdoneo) {
        for (int i = 0; i < vendeIdoneo.size() - 1; i += 10) {
            String nombre = vendeIdoneo.get(i);
            String apellido = vendeIdoneo.get(i + 1);
            String correo = vendeIdoneo.get(i + 2);
            String rut = vendeIdoneo.get(i + 3);
            int numero = Integer.parseInt(vendeIdoneo.get(i + 4));
            String contraseña = vendeIdoneo.get(i + 5);

            Vendedor vendedor = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

            String strEstrellas = vendeIdoneo.get(i + 6);
            AgregarEstrellasVendedor(strEstrellas, vendedor);

            String strComentarios = vendeIdoneo.get(i + 7);
            AgregarComenentariosVendedor(strComentarios, vendedor);

            String strConfirmaciones = vendeIdoneo.get(i + 8);
            AgregarConfirmacionesVendedor(strConfirmaciones, vendedor);

            String strObjetos = vendeIdoneo.get(i + 9);
            AgregarServiciosVendedor(strObjetos, vendedor);

            vendedores.add(vendedor);
        }
    }

    public static void AgregarConfirmacionesVendedor(String strConfirmaciones, Vendedor vendedor) {
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionesList = Arrays.asList(strConfirmaciones.split(","));

        vendedor.AgregarConfExt(confirmacionesList);
    }

    public static void AgregarComenentariosVendedor(String strComentarios, Vendedor vendedor) {
        strComentarios = strComentarios.replaceAll("\\[", "");
        strComentarios = strComentarios.replaceAll("\\]", "");
        strComentarios = strComentarios.replaceAll(" ", "");

        List<String> comentariosList = Arrays.asList(strComentarios.split(","));

        vendedor.AgregarComExt(comentariosList);
    }

    public static void AgregarEstrellasVendedor(String strEstrellas, Vendedor vendedor) {
        strEstrellas = strEstrellas.replaceAll("\\[", "");
        strEstrellas = strEstrellas.replaceAll("\\]", "");
        strEstrellas = strEstrellas.replaceAll(" ", "");

        List<String> estrellasList = Arrays.asList(strEstrellas.split(","));

        ArrayList<Integer> estrellasListInt = new ArrayList<>();
        for (String estrella : estrellasList) {
            if (!estrella.isEmpty()) {
                estrellasListInt.add(Integer.parseInt(estrella));
            }
        }
        vendedor.AgregarEstExt(estrellasListInt);
    }

    public static void AgregarServiciosVendedor(String strObjetos, Vendedor ven) {
        strObjetos = strObjetos.replaceAll("\\[", "");
        strObjetos = strObjetos.replaceAll("\\]", "");
        strObjetos = strObjetos.replaceAll(" ", "");

        List<String> listObjetos = Arrays.asList(strObjetos.split(","));

        ArrayList<Servicio> serv = new ArrayList<>();

        if (!strObjetos.isEmpty()) {
            for (int j = 0; j < listObjetos.size(); j += 2) {
                String nombre = listObjetos.get(j);
                String descripcion = listObjetos.get(j + 1);

                Servicio servicio = new Servicio(nombre, descripcion);

                serv.add(servicio);
            }
            ven.AgregarExtServ(serv);
        }
    }

    public static ArrayList<String> CrearArrayIdoneoVendedores(String strVendedores) {
        List<String> vendedoresList = Arrays.asList(strVendedores.split(";"));
        ArrayList<String> vendeDefList = new ArrayList<>(vendedoresList);
        for (int i = 0; i < 10; i++) {
            vendeDefList.remove(0);
        }
        return vendeDefList;
    }
}
