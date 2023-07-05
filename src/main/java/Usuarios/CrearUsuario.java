package Usuarios;

import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.AñadirCompradorArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedorArchivo;
import static Utilidades.Validadores.*;

public class CrearUsuario {
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    //Constructores Ventanas
    public CrearUsuario(Usuario comprador, ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
        CrearComprador(comprador);
    }
    public CrearUsuario(Vendedor vendedor, ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
        CrearVendedor(vendedor);
    }
    //Crear usuarios ventana
    public void CrearComprador(Usuario comprador) {
        this.compradores.add(comprador);
        AñadirCompradorArchivo(comprador);
    }
    private void CrearVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);
        AñadirVendedorArchivo(vendedor);
    }

}
