package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;


public class Login {
    private ArrayList<Usuario> compradores;

    private ArrayList<Vendedor> vendedores;

    private ArrayList<ArrayList> usuarios;

    public Login(ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);

    }


    public Vendedor LoginVendedor(String correo, String contraseña){
        for (Vendedor vendedor : this.vendedores){
            if (vendedor.GetCorreo().equals(correo) && vendedor.GetContraseña().equals(contraseña)){
                return vendedor;
            }
        }
        return null;
    }

    public Usuario LoginComprador(String correo, String contraseña){
        for (Usuario comprador : this.compradores){
            if (comprador.GetCorreo().equals(correo) && comprador.GetContraseña().equals(contraseña)){
                return comprador;
            }
        }
        return null;
    }
}
