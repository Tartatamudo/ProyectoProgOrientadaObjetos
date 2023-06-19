package Menu;

import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;

import static Usuarioss.GestionServicios.*;
import static Usuarioss.GestionUsuarios.ImprimirPerfilComprador;
import static Usuarioss.GestionUsuarios.ImprimirPerfilVendedor;
import static Utilidades.Validadores.GetEntero;

public class MenuLogueado {
    public static void MenuLogeadoComprador(ArrayList<ArrayList> usuarios, int numLogin){
        Usuario comprador = (Usuario) usuarios.get(0).get(numLogin);
        int eleccion = 1;
        while(eleccion >0 && eleccion < 5 ) {
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de vendedores");
            System.out.println("[2]Mostrar perfil de vendedor");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("Escriba cualquier otro numero para salir");

            eleccion = GetEntero();
            switch (eleccion) {
                case 1:
                    ImprimirServiciosVenta(usuarios.get(1));
                    break;
                case 2:
                    ImprimirPerfilVendedor(usuarios.get(1));
                    break;
                case 3:
                    CrearServicioCompra(comprador, usuarios.get(0));
                    break;
                case 4:
                    ImprimirNuestrosServiciosCompra(comprador);
                    break;
            }
        }
    }

    public static void MenuLogeadoVendedor(ArrayList<ArrayList> usuarios, int numLogin){
        Vendedor vendedor = (Vendedor) usuarios.get(1).get(numLogin);
        int eleccion = 1;
        while( (eleccion>0) && (eleccion< 5)){
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de compradores");
            System.out.println("[2]Mostrar perfil de comprador");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("Escriba cualquier otro numero para salir");

            eleccion = GetEntero();
            switch (eleccion){
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
            }
        }
    }


}
