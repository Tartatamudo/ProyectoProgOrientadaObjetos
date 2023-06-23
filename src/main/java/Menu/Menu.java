package Menu;

import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;

import static Usuarioss.CrearUsuario.CrearUsuario;
import static Usuarioss.Login.MenuLogin;
import static Datos.GestorArchivosCompradores.CargarCompradoresAPrograma;
import static Datos.GestorArchivosVendedores.CargarVendedoresAPrograma;
import static Utilidades.Validadores.GetEntero;
import static Utilidades.Validadores.ValidarArchivos;

public class Menu {
    public static void menu(){
        ArrayList<Usuario> compradores = new ArrayList<Usuario>();
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        ArrayList<ArrayList> usuarios = new ArrayList<ArrayList>();

        ValidarArchivos();

        CargarCompradoresAPrograma(compradores);
        CargarVendedoresAPrograma(vendedores);
        usuarios.add(compradores);
        usuarios.add(vendedores);
        int eleccion = 1;
        while(eleccion > 0 && eleccion < 3){
        System.out.println("[1] Crear Usuario");
        System.out.println("[2] Iniciar sesion");
        System.out.println("Eliga cualquier otro numero para salir");

        eleccion = GetEntero();

        switch (eleccion) {
            case 1:
                CrearUsuario(usuarios);
                break;
            case 2:
                MenuLogin(usuarios);
                break;
            }
        }
    }

}
