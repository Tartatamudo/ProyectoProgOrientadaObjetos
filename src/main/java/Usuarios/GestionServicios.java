package Usuarios;

import java.util.ArrayList;
import java.util.Locale;

import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;
import static Utilidades.Validadores.GetEntero;

public class GestionServicios {
    //Imprimir servicios que se buscan

    //Ventana
    public static String DevolverStrServiciosCompra(ArrayList<Usuario> compradores){
        String texto = "";
        for (int i = 0; i <compradores.size() ; i++) {
            if(compradores.get(i).GetLargoServicios() != 0){
                texto = texto+ "Comprador " + String.valueOf(i) + ". "+ compradores.get(i).DevolverStrServicos() +";";

            }
        }
        return texto;
    }
    //Ventana
    public static String DevolverStrServiciosVenta(ArrayList<Vendedor> vendedores){
        String texto = "";
        for (int i = 0; i <vendedores.size() ; i++) {
            if(vendedores.get(i).GetLargoServicios() != 0){
                texto = texto+ "Vendedor " + String.valueOf(i) + ". " + vendedores.get(i).DevolverStrServicos() +";";
            }
        }
        return texto;
    }

    //Consola
    public static void ImprimirServiciosCompra(ArrayList<Usuario> compradores){
        System.out.println("--------------------------------");
        for (int i = 0; i <compradores.size() ; i++) {
            if(compradores.get(i).GetLargoServicios() != 0){
                System.out.println("Comprador " + i);
                compradores.get(i).ImprimirServicios();
                System.out.println("--------------------------------");
            }
        }
    }

    //Consola
    public static void ImprimirServiciosVenta(ArrayList<Vendedor> vendedores){
        System.out.println("--------------------------------");
        for (int i = 0; i <vendedores.size() ; i++) {
            if(vendedores.get(i).GetLargoServicios() != 0){
                System.out.println("Comprador " + i);
                vendedores.get(i).ImprimirServicios();
                System.out.println("--------------------------------");
            }
        }
    }
    //Impresion de los servicios propios
    //Consola
    public static void ImprimirNuestrosServiciosVenta(Vendedor vendedor){
        vendedor.ImprimirServicios();
    }

    //Consola
    public static void ImprimirNuestrosServiciosCompra(Usuario comprador){
        comprador.ImprimirServicios();
    }

    //Crear publicacion
    //Consola
    public static void CrearServicioCompra(Usuario comprador, ArrayList<Usuario> compradores) {
        comprador.CrearPublicacionn();
        AñadirCompradoresArchivo(compradores);
        System.out.println("Servicio añadido con exito");
    }

    //Consola
    public static void CrearServicioVenta(Vendedor vendedor, ArrayList<Vendedor> vendedores) {
        vendedor.CrearPublicacionn();
        AñadirVendedoresArchivo(vendedores);
        System.out.println("Servicio añadido con exito");
    }

    //Filtrar servicios
    //Consola
    public static void FiltrarServiciosVenta(ArrayList<Vendedor> vendedores){
        int eleccion = 0;
        String strEleccion = "";
        System.out.println("Eliga una de las opciones:");
        System.out.println("[1]Electricista");
        System.out.println("[2]Gasfiter");
        eleccion = GetEntero();

        switch (eleccion){
            case 1:
                strEleccion = "electricista";
                ImprimirEleccionVenta(strEleccion, vendedores);
                break;
            case 2:
                strEleccion = "gasfiter";
                ImprimirEleccionVenta(strEleccion, vendedores);
        }

    }
    //Consola
    public static void FiltrarServiciosCompra(ArrayList<Usuario> compradores){
        int eleccion = 0;
        String strEleccion = "";
        System.out.println("Eliga una de las opciones:");
        System.out.println("[1]Electricista");
        System.out.println("[2]Gasfiter");
        eleccion = GetEntero();

        switch (eleccion){
            case 1:
                strEleccion = "electricista";
                ImprimirEleccionCompra(strEleccion, compradores);
                break;
            case 2:
                strEleccion = "gasfiter";
                ImprimirEleccionCompra(strEleccion, compradores);
        }
    }

    //Impresion del filtro consola
    private static void ImprimirEleccionCompra(String strEleccion, ArrayList<Usuario> compradores) {
        ArrayList<Servicio> servicios;

        System.out.println("--------------------------------");
        for (int i = 0; i < compradores.size(); i++) {
            servicios = compradores.get(i).GetServicios();

            for (Servicio servicio: servicios) {
                String strNomServ = servicio.GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    System.out.println("Comprador " + i);
                    System.out.println("    " + servicio.GetNombre() + ":");
                    System.out.println("        " + servicio.GetDescripcion());
                    System.out.println("--------------------------------");
                }
            }
        }
    }

    //Consola
    private static void ImprimirEleccionVenta(String strEleccion, ArrayList<Vendedor> vendedores) {
        ArrayList<Servicio> servicios;

        System.out.println("--------------------------------");
        for (int i = 0; i < vendedores.size(); i++) {
            servicios = vendedores.get(i).GetServicios();

            for (int j = 0; j <servicios.size() ; j++) {
                String strNomServ = servicios.get(j).GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    System.out.println("Vendedor " + i);
                    System.out.println("    " + servicios.get(j).GetNombre() + ":");
                    System.out.println("        " + servicios.get(j).GetDescripcion());
                    System.out.println("--------------------------------");
                }
            }
        }
    }

    //Ventana
    public static String DevolverStrEleccionVenta(String strEleccion, ArrayList<Vendedor> vendedores) {
        String texto = "";
        ArrayList<Servicio> servicios;

        for (int i = 0; i < vendedores.size(); i++) {
            servicios = vendedores.get(i).GetServicios();

            for (Servicio servicio : servicios) {
                String strNomServ = servicio.GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    texto = texto +"Vendedor " + i + ": ";
                    texto = texto + servicio.GetNombre() + ": ";
                    texto = texto + servicio.GetDescripcion() + ";";
                }
            }
        }
        return texto;
    }

    //Ventana
    public static String DevolverStrEleccionCompra(String strEleccion, ArrayList<Usuario> compradores) {
        String texto = "";
        ArrayList<Servicio> servicios;

        for (int i = 0; i < compradores.size(); i++) {
            servicios = compradores.get(i).GetServicios();

            for (Servicio servicio: servicios) {
                String strNomServ = servicio.GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    texto = texto +"Comprador " + i + ": ";
                    texto = texto + servicio.GetNombre() + ": ";
                    texto = texto + servicio.GetDescripcion() + ";";
                }
            }
        }
        return texto;
    }

}
