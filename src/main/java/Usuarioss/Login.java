package Usuarioss;

import java.util.ArrayList;

import static Menu.MenuLogueado.MenuLogeadoComprador;
import static Menu.MenuLogueado.MenuLogeadoVendedor;

import static Utilidades.Validadores.*;

public class Login {
    public static String ClaseLogin() {
        System.out.println("[1]Ingresar como comprador");
        System.out.println("[2]Ingresar como vendedor");
        System.out.println("Escriba otro numero si desea salir");
        String clase = "";

        int eleccion = GetEntero();
        switch (eleccion) {
            case 1:
                return "Comprador";

            case 2:
                return "Vendedor";
        }
        return clase;
    }

    public static void MenuLogin(ArrayList<ArrayList> usuarios){
        String numLogin;
        String clase = ClaseLogin();

        if (clase.equals("Comprador")){
            numLogin = LoginComprador(usuarios.get(0));

            if(numLogin != ""){
                MenuLogeadoComprador(usuarios, Integer.parseInt(numLogin));
            }

        }else if (clase.equals("Vendedor")){
            numLogin  = LoginVendedor(usuarios.get(1));

            if(numLogin != ""){
                MenuLogeadoVendedor(usuarios, Integer.parseInt(numLogin));
            }
        }

    }

    public static String LoginComprador(ArrayList<Usuario> compradores) {
        String correo = GetCorreo();
        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        for (int i = 0; i < compradores.size(); i++) {
            if ((correo.equals(compradores.get(i).getCorreo())) && (contraseña.equals(compradores.get(i).getContraseña()))) {
                System.out.println("Ingreso como comprador");
                return Integer.toString(i);
            }
        }
        System.out.println("correo y/o contraseña erroneos");
        return "";
    }

    public static String LoginVendedor(ArrayList<Vendedor> vendedores) {
        String correo = GetCorreo();
        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        for (int i = 0; i < vendedores.size(); i++) {
            if ((correo.equals(vendedores.get(i).getCorreo())) && (contraseña.equals(vendedores.get(i).getContraseña()))) {
                System.out.println("Ingreso como vendedor");
                return Integer.toString(i);
            }
        }
        System.out.println("correo y/o contraseña erroneos");
        return "";
    }

}
