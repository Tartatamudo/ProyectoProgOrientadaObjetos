package Menu;

import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;

import static Usuarioss.CrearUsuario.CrearUsuario;
import static Usuarioss.Login.MenuLogin;
import static Utilidades.GestorArchivosCompradores.CargarCompradoresAPrograma;
import static Utilidades.GestorArchivosVendedores.CargarVendedoresAPrograma;
import static Utilidades.ScannerTeclado.getNumero;

public class Menu {
    public static void menu(){
        ArrayList<Usuario> compradores = new ArrayList<Usuario>();
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

        CargarCompradoresAPrograma(compradores);
        CargarVendedoresAPrograma(vendedores);

        int eleccion =1;
        while(eleccion > 0 && eleccion < 3){
        System.out.println("[1] Crear Usuario");
        System.out.println("[2] Iniciar sesion");

        eleccion = getNumero();

        switch (eleccion) {
            case 1:
                CrearUsuario(compradores, vendedores);
                break;
            case 2:
                MenuLogin(compradores, vendedores);
                break;
            }
        }
    }
}
