package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;


import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;
import static Usuarios.GestionServicios.*;
import static Usuarios.GestionUsuarios.ImprimirPerfilComprador;
import static Usuarios.GestionUsuarios.ImprimirPerfilVendedor;
import static Utilidades.Validadores.GetEntero;

public class MenuLogueado {
    //Consola
    public static void MenuLogeadoComprador(ArrayList<ArrayList> usuarios, Usuario comprador) {
        int eleccion = 1;
        while (eleccion > 0 && eleccion < 7) {
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de vendedores");
            System.out.println("[2]Mostrar perfil de vendedor");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("[5]Evaluar vendedor");
            System.out.println("[6]Filtrar publicaciones");
            System.out.println("Escriba cualquier otro numero para salir");

            eleccion = GetEntero();
            switch (eleccion) {
                case 1:
                    ImprimirServiciosVenta(usuarios.get(1));
                    break;
                case 2:
                    ImprimirPerfilVendedor(usuarios.get(1), comprador);

                    AñadirVendedoresArchivo(usuarios.get(1));

                    break;
                case 3:
                    CrearServicioCompra(comprador, usuarios.get(0));
                    break;
                case 4:
                    ImprimirNuestrosServiciosCompra(comprador);
                    break;
                case 5:
                    EvaluarVendedorr(usuarios.get(1), comprador);
                    AñadirCompradoresArchivo(usuarios.get(0));
                    AñadirVendedoresArchivo(usuarios.get(1));
                    break;
                case 6:
                    FiltrarServiciosVenta(usuarios.get(1));
            }
        }
    }
    //Consola
    public static void MenuLogeadoVendedor(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        int eleccion = 1;
        while ((eleccion > 0) && (eleccion < 7)) {
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de compradores");
            System.out.println("[2]Mostrar perfil de comprador");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("[5]Confirmar contacto");
            System.out.println("[6]Filtrar publicaciones");
            System.out.println("Escriba cualquier otro numero para salir");

            eleccion = GetEntero();
            switch (eleccion) {
                case 1:
                    ImprimirServiciosCompra(usuarios.get(0));
                    break;
                case 2:
                    ImprimirPerfilComprador(usuarios.get(0));
                    break;
                case 3:
                    CrearServicioVenta(vendedor, usuarios.get(1));
                    break;
                case 4:
                    ImprimirNuestrosServiciosVenta(vendedor);
                    break;
                case 5:
                    ConfirmarContacto(vendedor, usuarios.get(0));

                    AñadirVendedoresArchivo(usuarios.get(1));
                    AñadirCompradoresArchivo(usuarios.get(0));
                    break;
                case 6:
                    FiltrarServiciosCompra(usuarios.get(0));
            }
        }
    }

    //Consola
    private static void ConfirmarContactoo(Vendedor vendedor, ArrayList<Usuario> compradores) {
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        //Aqui se añaden al ArrayList compradoresConf solo los compradores que tienen su rut en el aparto confirmaciones del vendedor, pero solo su rut
        ArrayList<Usuario> compradoresConf = new ArrayList<>();
        for (int i = 0; i < confirmaciones.size(); i++) {
            for (int j = 0; j < compradores.size(); j++) {
                if (confirmaciones.get(i).equals(compradores.get(j).GetRut())) {
                    compradoresConf.add(compradores.get(j));
                }
            }
        }
        //Aqui se muestran los compradores que tienen su rut y solo su rut en el apartado confirmaciones del vendedor
        //Si no hay compradores a los que confirmarles el contacto se le indica que no tiene
        if (compradoresConf.size() != 0) {
            System.out.println("Eliga la confirmacion que quiere confirmar");
            for (int i = 0; i < compradoresConf.size(); i++) {
                System.out.println(i + ". " + compradoresConf.get(i).GetNombre() + " " + compradoresConf.get(i).GetApellido());
            }
            //Aqui se confirma que el numero entregado por el usuario este dentro del rango de numeros que se entrego con anterioridad
            //Si es asi se cambia el rut del comprador que esta en el apartado confirmaciones añadiendole la palabra "true"
            //Si el numero no entra se le indica
            int eleccion = GetEntero();
            if ((eleccion < compradoresConf.size()) && (eleccion >= 0)) {
                String rut = compradoresConf.get(eleccion).GetRut();
                vendedor.CambiarConfirmacion(rut);
                compradoresConf.get(eleccion).AgregarConfirmacion(vendedor.GetRut());
            } else {
                System.out.println("Debe ingresar un numero que este en la lista entregada");
            }

        } else {
            System.out.println("No tiene confirmaciones que confirmar");
        }

    }
    //Consola
    private static void EvaluarVendedorr(ArrayList<Vendedor> usuarios, Usuario comprador) {
        ArrayList<String> vendedoresStr = comprador.GetConfirmaciones();

        //Aqui se añaden al ArrayList vendedoresConf los vendedores que esten en el apartado confirmaciones del comprador
        ArrayList<Vendedor> vendedoresConf = new ArrayList<>();
        for (int i = 0; i < vendedoresStr.size(); i++) {
            for (int j = 0; j < usuarios.size(); j++) {
                if (vendedoresStr.get(i).equals(usuarios.get(j).GetRut())) {
                    vendedoresConf.add(usuarios.get(j));
                    usuarios.get(j).GetNombre();
                }
            }
        }
        //Si hay vendedores en el ArrayList, se confirma que este el rut+true del comprador
        // en el apartado confirmaciones del vendedor para poder mostrarlo
        if (vendedoresConf.size() != 0) {

            System.out.println("Eliga a quien quiere evaluar");
            String rutConf = comprador.GetRut() + "true";
            for (int i = 0; i < vendedoresConf.size(); i++) {
                if (vendedoresConf.get(i).GetConfirmacion(rutConf) == true) {

                    System.out.println(i + ". " + vendedoresConf.get(i).GetNombre() + " " + vendedoresConf.get(i).GetApellido());
                }
            }

            //Se confirma que la eleccion esta dentro del rango, si no es asi se le avisa
            //si se confirma, le da entrada a darle la cantidad de estrellas y darle un comentario al vendedor
            int eleccion = GetEntero();
            if (eleccion < vendedoresConf.size() && eleccion >= 0) {

                System.out.println("Eliga las estrellas que quiera");
                vendedoresConf.get(eleccion).AgregarEstrellass();
                System.out.println("Escriba el comentario");
                vendedoresConf.get(eleccion).AgregarComentarioo();
                vendedoresConf.get(eleccion).RemoverConfirmacion(rutConf);
                comprador.RemoverConfirmacion(vendedoresConf.get(eleccion).GetRut());
            } else {
                System.out.println("Debe ingresar un numero que este en la lista entregada");
            }

        } else {
            System.out.println("No tiene a nadie para evaluar");
        }
    }

    //Ventana
    public static String EvaluarVendedor(ArrayList<Vendedor> vendedoresConf, Usuario comprador) {
        String texto = "";

        if (vendedoresConf.size() != 0) {

            String rutConf = comprador.GetRut() + "true";
            for (int i = 0; i < vendedoresConf.size(); i++) {
                if (vendedoresConf.get(i).GetConfirmacion(rutConf) == true) {

                    texto = texto + i + ". " + vendedoresConf.get(i).GetNombre() + " " + vendedoresConf.get(i).GetApellido() + ";";
                }
            }
        }
        return texto;
    }

    //Ventana
    public static ArrayList<Vendedor> VendedoresConfirm(ArrayList<Vendedor> usuarios, Usuario comprador) {

        ArrayList<String> vendedoresStr = comprador.GetConfirmaciones();

        ArrayList<Vendedor> vendedoresConf = new ArrayList<>();
        for (int i = 0; i < vendedoresStr.size(); i++) {
            for (int j = 0; j < usuarios.size(); j++) {
                if (vendedoresStr.get(i).equals(usuarios.get(j).GetRut())) {
                    vendedoresConf.add(usuarios.get(j));
                    usuarios.get(j).GetNombre();
                }
            }
        }
        return vendedoresConf;
    }

    //Ventana
    public static ArrayList<Usuario> ConfirmarContacto(Vendedor vendedor, ArrayList<Usuario> compradores) {
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        //Aqui se añaden al ArrayList compradoresConf solo los compradores que tienen su rut en el aparto confirmaciones del vendedor, pero solo su rut
        ArrayList<Usuario> compradoresConf = new ArrayList<>();
        for (int i = 0; i < confirmaciones.size(); i++) {
            for (int j = 0; j < compradores.size(); j++) {
                if (confirmaciones.get(i).equals(compradores.get(j).GetRut())) {
                    compradoresConf.add(compradores.get(j));
                }
            }
        }
        return compradoresConf;
    }

    //Ventana
    public static String StrConfirmarContacto(Vendedor vendedor, ArrayList<Usuario> compradoresConf) {
        String texto = "";
        //Aqui se muestran los compradores que tienen su rut y solo su rut en el apartado confirmaciones del vendedor
        //Si no hay compradores a los que confirmarles el contacto se le indica que no tiene
        if (compradoresConf.size() != 0) {
            for (int i = 0; i < compradoresConf.size(); i++) {
                texto = texto + i + ". " + compradoresConf.get(i).GetNombre() + " " + compradoresConf.get(i).GetApellido() + ";";
            }
        }
        return texto;
    }
}