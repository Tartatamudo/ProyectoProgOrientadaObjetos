package Usuarioss;

import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;

public class GestionServicios {
    //Imprimir servicios que se buscan
    public static void ImprimirServiciosCompra(ArrayList<Usuario> compradores){
        for (int i = 0; i <compradores.size() ; i++) {
            if(compradores.get(i).GetLargoServicios() != 0){
                System.out.println("Comprador " + i);
                compradores.get(i).getServicios();
            }
        }
    }
    public static void ImprimirServiciosVenta(ArrayList<Vendedor> vendedores){
        for (int i = 0; i <vendedores.size() ; i++) {
            if(vendedores.get(i).GetLargoServicios() != 0){
                System.out.println("Comprador " + i);
                vendedores.get(i).getServicios();
            }
        }
    }
    //Impresion de los servicios propios
    public static void ImprimirNuestrosServiciosVenta(Vendedor vendedor){
        vendedor.getServicios();
    }
    public static void ImprimirNuestrosServiciosCompra(Usuario comprador){
        comprador.getServicios();
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
}
