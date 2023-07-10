package Usuarios;

import Datos.GestorArchivos;

import java.util.ArrayList;

public class CrearUsuario {
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    public CrearUsuario(ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
    }
    public void CrearComprador(Usuario comprador) {
        this.compradores.add(comprador);
    }
    public void CrearVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);
    }

}
