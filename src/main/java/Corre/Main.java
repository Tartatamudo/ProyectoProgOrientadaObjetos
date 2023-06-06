package Corre;

import Usuarioss.Usuario;
import Usuarioss.Vendedor;

import java.util.ArrayList;

import static Usuarioss.CrearUsuario.CrearComprador;
import static Utilidades.GestorArchivosCompradores.CargarCompradoresAPrograma;
import static Utilidades.GestorArchivosVendedores.CargarVendedoresAPrograma;

public class Main {
    public static void main(String[] args) {
        ArrayList<Usuario> compradores = new ArrayList<Usuario>();
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();


        CrearComprador(compradores);
        //CargarCompradoresAPrograma(compradores);
        //CargarVendedoresAPrograma(vendedores);
    }
}
