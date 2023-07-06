package Usuarios;

import Datos.GestorArchivos;

import java.util.ArrayList;

public class CrearUsuario {
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;
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

    public void CrearComprador(Usuario comprador) {
        this.compradores.add(comprador);

        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.AñadirCompradorArchivo(comprador);
    }
    private void CrearVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);

        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.AñadirVendedorArchivo(vendedor);
    }

}
