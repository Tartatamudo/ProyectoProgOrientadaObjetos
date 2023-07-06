package Usuarios;


import Datos.GestorArchivos;

import java.util.ArrayList;

public class GestionUsuarios {
    private ArrayList<ArrayList> usuarios;
    public GestionUsuarios(ArrayList<ArrayList> usuarios){
        this.usuarios = usuarios;
    }

    public void ConfirmarEncuentro(Vendedor vendedor, int numComprador, ArrayList<Usuario> compradoresConf){
        String rut = compradoresConf.get(numComprador).GetRut();
        vendedor.CambiarConfirmacion(rut);
        compradoresConf.get(numComprador).AgregarConfirmacion(vendedor.GetRut());

        GestorArchivos gestorArchivos = new GestorArchivos();

        gestorArchivos.AñadirCompradoresArchivo(usuarios.get(0));
        gestorArchivos.AñadirVendedoresArchivo(usuarios.get(1));
    }
    public void ActualizarVendedores(){
        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.AñadirVendedoresArchivo(usuarios.get(1));
    }
    public void ActualizarCompradores(){
        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.AñadirCompradoresArchivo(usuarios.get(0));
    }
}
