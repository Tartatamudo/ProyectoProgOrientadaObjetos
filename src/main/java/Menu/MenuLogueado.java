package Menu;

import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;


import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;
import static Usuarioss.GestionServicios.*;
import static Usuarioss.GestionUsuarios.ImprimirPerfilComprador;
import static Usuarioss.GestionUsuarios.ImprimirPerfilVendedor;
import static Utilidades.Validadores.GetEntero;

public class MenuLogueado {
    public static void MenuLogeadoComprador(ArrayList<ArrayList> usuarios, int numLogin){
        Usuario comprador = (Usuario) usuarios.get(0).get(numLogin);
        int eleccion = 1;
        while(eleccion >0 && eleccion < 6 ) {
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de vendedores");
            System.out.println("[2]Mostrar perfil de vendedor");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("[5]Evaluar vendedor");
            System.out.println("Escriba cualquier otro numero para salir");

            eleccion = GetEntero();
            switch (eleccion) {
                case 1:
                    ImprimirServiciosVenta(usuarios.get(1));
                    break;
                case 2:
                    ImprimirPerfilVendedor(usuarios.get(1), comprador);

                    AñadirVendedoresArchivo(usuarios.get(1));
                    AñadirCompradoresArchivo(usuarios.get(0));
                    break;
                case 3:
                    CrearServicioCompra(comprador, usuarios.get(0));
                    break;
                case 4:
                    ImprimirNuestrosServiciosCompra(comprador);
                    break;
                case 5:
                    EvaluarVendedor(usuarios.get(1), comprador);
                    AñadirCompradoresArchivo(usuarios.get(0));
                    AñadirVendedoresArchivo(usuarios.get(1));
            }
        }
    }

    public static void MenuLogeadoVendedor(ArrayList<ArrayList> usuarios, int numLogin){
        Vendedor vendedor = (Vendedor) usuarios.get(1).get(numLogin);
        int eleccion = 1;
        while( (eleccion>0) && (eleccion< 6)){
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de compradores");
            System.out.println("[2]Mostrar perfil de comprador");
            System.out.println("[3]Crear publicacion");
            System.out.println("[4]Mostrar mis publicaciones");
            System.out.println("[5]Confirmar contacto");
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
                case 5:
                    ConfirmarContacto(vendedor, usuarios.get(0));

                    AñadirVendedoresArchivo(usuarios.get(1));
                    AñadirCompradoresArchivo(usuarios.get(0));
            }
        }
    }

    private static void ConfirmarContacto(Vendedor vendedor, ArrayList<Usuario> compradores) {
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        ArrayList<Usuario> compradoresConf = new ArrayList<>();
        for (int i = 0; i < confirmaciones.size(); i++) {
            for (int j = 0; j < compradores.size(); j++) {
                if (confirmaciones.get(i).equals(compradores.get(j).getRut())){
                    compradoresConf.add(compradores.get(j));
                }
            }
        }
        if (compradoresConf.size() != 0){
            System.out.println("Eliga la confirmacion que quiere confirmar");
            for (int i = 0; i < compradoresConf.size(); i++) {
                System.out.println(i + ". " + compradoresConf.get(i).getNombre() + " " + compradoresConf.get(i).getApellido());
            }
            int eleccion = GetEntero();
            String rut = compradoresConf.get(eleccion).getRut();
            vendedor.CambiarConfirmacion(rut);
        }else{
            System.out.println("No tiene confirmaciones que confirmar");
        }


    }

    private static void EvaluarVendedor(ArrayList<Vendedor> usuarios, Usuario comprador) {
        ArrayList<String> vendedoresStr = comprador.GetConfirmaciones();
        System.out.println(vendedoresStr.get(0));
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        for (int i = 0; i < vendedoresStr.size(); i++) {
            for (int j = 0; j < usuarios.size(); j++) {
                if (vendedoresStr.get(i).equals(usuarios.get(j).getRut())) {
                    vendedores.add(usuarios.get(j));
                    usuarios.get(j).getNombre();
                }
            }
        }
        System.out.println(vendedores.size());
        if(vendedores.size() != 0  ) {

            System.out.println("Eliga a quien quiere evaluar");
            String rutConf = comprador.getRut() + "true";
            for (int i = 0; i < vendedores.size(); i++) {
                if(vendedores.get(i).GetConfirmacion(rutConf) == true){

                    System.out.println(i + ". " + vendedores.get(i).getNombre() + " " + vendedores.get(i).getApellido());
                }
            }
            int eleccion = GetEntero();
            if(vendedores.get(eleccion).GetConfirmacion(rutConf) == true) {
                System.out.println("Eliga las estrellas que quiera");
                vendedores.get(eleccion).AgregarEstrellas();
                System.out.println("Escriba el comentario");
                vendedores.get(eleccion).AgregarComentario();
                vendedores.get(eleccion).RemoverConfirmacion(rutConf);

            }
        }else{
            System.out.println("No tiene a nadie para evaluar");
        }
    }


}
