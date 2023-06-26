package Menu;

import Usuarios.CrearUsuario;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.CargarCompradoresAPrograma;
import static Datos.GestorArchivosVendedores.CargarVendedoresAPrograma;
import static Login.Login.MenuLogin;
import static Utilidades.Validadores.GetEntero;
import static Utilidades.Validadores.ValidarArchivos;

public class Menu {
    private ArrayList<Usuario> compradores = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<ArrayList> usuarios = new ArrayList<>();

    public Menu() {
        CargarCompradoresAPrograma(this.compradores);
        CargarVendedoresAPrograma(this.vendedores);

        this.usuarios.add(this.compradores);
        this.usuarios.add(this.vendedores);

        MenuIni();
    }

    public void MenuIni(){

        ValidarArchivos();

        int eleccion = 1;
        while(eleccion > 0 && eleccion < 3){
        System.out.println("[1] Crear Usuario");
        System.out.println("[2] Iniciar sesion");
        System.out.println("Eliga cualquier otro numero para salir");

        eleccion = GetEntero();

        switch (eleccion) {
            case 1:
                CrearUsuario crearUsuario = new CrearUsuario(this.usuarios);
                break;
            case 2:
                MenuLogin(this.usuarios);
                break;
            default:
                Salir();
            }
        }
    }
    public void Salir(){
        System.exit(1);
    }

}
