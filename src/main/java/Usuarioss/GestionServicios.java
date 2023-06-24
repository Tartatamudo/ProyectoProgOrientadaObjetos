package Usuarioss;

import java.util.ArrayList;
import java.util.Locale;

import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;
import static Utilidades.Validadores.GetEntero;

public class GestionServicios {
    //Imprimir servicios que se buscan
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
    public static void ImprimirNuestrosServiciosVenta(Vendedor vendedor){
        vendedor.ImprimirServicios();
    }
    public static void ImprimirNuestrosServiciosCompra(Usuario comprador){
        comprador.ImprimirServicios();
    }

    //Crear publicacion
    public static void CrearServicioCompra(Usuario comprador, ArrayList<Usuario> compradores) {
        comprador.crearPublicacion();
        AñadirCompradoresArchivo(compradores);
        System.out.println("Servicio añadido con exito");
    }
    public static void CrearServicioVenta(Vendedor vendedor, ArrayList<Vendedor> vendedores) {
        vendedor.crearPublicacion();
        AñadirVendedoresArchivo(vendedores);
        System.out.println("Servicio añadido con exito");
    }

    //Filtrar servicios
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

    private static void ImprimirEleccionCompra(String strEleccion, ArrayList<Usuario> compradores) {
        ArrayList<Servicio> servicios;

        System.out.println("--------------------------------");
        for (int i = 0; i < compradores.size(); i++) {
            servicios = compradores.get(i).getServicios();

            for (int j = 0; j <servicios.size() ; j++) {
                String strNomServ = servicios.get(j).getNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    System.out.println("Comprador " + i);
                    System.out.println("    " + servicios.get(j).getNombre() + ":");
                    System.out.println("        " + servicios.get(j).getDescripcion());
                    System.out.println("--------------------------------");
                }
            }
        }
    }

    private static void ImprimirEleccionVenta(String strEleccion, ArrayList<Vendedor> vendedores) {
        ArrayList<Servicio> servicios;

        System.out.println("--------------------------------");
        for (int i = 0; i < vendedores.size(); i++) {
            servicios = vendedores.get(i).getServicios();

            for (int j = 0; j <servicios.size() ; j++) {
                String strNomServ = servicios.get(j).getNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    System.out.println("Vendedor " + i);
                    System.out.println("    " + servicios.get(j).getNombre() + ":");
                    System.out.println("        " + servicios.get(j).getDescripcion());
                    System.out.println("--------------------------------");
                }
            }
        }
    }

}
