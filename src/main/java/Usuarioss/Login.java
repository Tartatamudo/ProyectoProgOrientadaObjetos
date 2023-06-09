package Usuarioss;

import java.util.ArrayList;

import static Utilidades.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Utilidades.GestorArchivosVendedores.AñadirVendedoresArchivo;
import static Utilidades.Validadores.*;

public class Login {
    public static String ClaseLogin() {
        System.out.println("[1]Ingresar como comprador");
        System.out.println("[2]Ingresar como vendedor");
        String clase = "";
        int num;

        int eleccion = GetEntero();
        switch (eleccion) {
            case 1:
                return "Comprador";

            case 2:
                return "Vendedeor";
        }
        return clase;
    }

    public static void MenuLogin(ArrayList<Usuario> compradores, ArrayList<Vendedor> vendedores){
        int numLogin;
        String clase = ClaseLogin();

        ArrayList<ArrayList> usuarios = new ArrayList<>();

        usuarios.add(compradores);
        usuarios.add(vendedores);
        if (clase.equals("Comprador")){
            numLogin = LoginComprador(compradores);
            MenuLogeadoComprador(usuarios, numLogin);
        }else{
            numLogin  = LoginVendedor(vendedores);
            MenuLogeadoVendedor(usuarios, numLogin);
        }

    }

    public static int LoginComprador(ArrayList<Usuario> compradores) {
        System.out.println("Ingrese correo");
        String correo = GetCorreo();
        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        for (int i = 0; i < compradores.size(); i++) {
            if ((correo.equals(compradores.get(i).getCorreo())) && (contraseña.equals(compradores.get(i).getContraseña()))) {
                System.out.println("Ingreso como comprador");
                return i;
            }
        }
        System.out.println("correo o contraseña erroneos");
        return 0;
    }

    public static int LoginVendedor(ArrayList<Vendedor> vendedores) {
        System.out.println("Ingrese correo");
        String correo = GetCorreo();
        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        for (int i = 0; i < vendedores.size(); i++) {
            if ((correo.equals(vendedores.get(i).getCorreo())) && (contraseña.equals(vendedores.get(i).getContraseña()))) {
                System.out.println("Ingreso como vendedor");
                return i;
            }
        }
        return 0;
    }

    public static void MenuLogeadoComprador(ArrayList<ArrayList> usuarios, int numLogin){
        int eleccion = 1;
        while(eleccion >0 && eleccion <4 ) {
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de vendedores");
            System.out.println("[2]Mostrar perfil de vendedor");
            System.out.println("[3]Crear publicacion");
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
                    CrearPublicacionCompra(usuarios.get(0), numLogin);
            }
        }
    }

    public static void MenuLogeadoVendedor(ArrayList<ArrayList> usuarios, int numLogin){
        int eleccion = 1;
        while( (eleccion>0) && (eleccion<4)){
            System.out.println("Que desea hacer:");
            System.out.println("[1]Mostrar servicios de compradores");
            System.out.println("[2]Mostrar perfil de vendedor");
            System.out.println("[3]Crear publicacion");
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
                    CrearPublicacionVenta(usuarios.get(1), numLogin);
            }
        }
    }

    //Imprime todos los servicios de compradores o vendedores
    public static void ImprimirServiciosCompra(ArrayList<Usuario> compradores){
        for (int i = 0; i <compradores.size() ; i++) {
            System.out.println("Comprador " + i);
            compradores.get(i).getServicios();
        }
    }
    public static void ImprimirServiciosVenta(ArrayList<Vendedor> vendedores){
        for (int i = 0; i <vendedores.size() ; i++) {
            System.out.println("Vendedor " + i);
            vendedores.get(i).getServicios();
        }
    }

    //impresion de los perfiles cuando se pida. Se pide el numero de la posicin del usuario en su respectivo Arraylist
    public static void ImprimirPerfilVendedor(ArrayList<Vendedor> vendedores){
        System.out.println("Eliga el numer del vendedor que desea ver");
        int num = GetEntero();
        if (vendedores.get(num) != null){
            vendedores.get(num).getDatos();
        }else{
            System.out.println("El vendedor que eligio no existe");
        }
    }
    public static void ImprimirPerfilComprador(ArrayList<Usuario> compradores){
        System.out.println("Eliga el numer del vendedor que desea ver");

        int num = GetEntero();
        if (compradores.get(num) != null){
            compradores.get(num).getDatos();
        }else{
            System.out.println("El vendedor que eligio no existe");
        }
    }

    //Crear publicacion
    private static void CrearPublicacionCompra(ArrayList<Usuario> compradores, int numLogin) {
        compradores.get(numLogin).crearPublicacion();
        AñadirCompradoresArchivo(compradores);
    }
    private static void CrearPublicacionVenta(ArrayList<Vendedor> vendedores, int numLogin) {
        vendedores.get(numLogin).crearPublicacion();
        AñadirVendedoresArchivo(vendedores);
    }

}
