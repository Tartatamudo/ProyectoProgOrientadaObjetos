package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;


public class Login {
    private ArrayList<Usuario> compradores;

    private ArrayList<Vendedor> vendedores;

    private ArrayList<ArrayList> usuarios;
    private String correo;
    private String contraseña;

    public Login(String correo, String contraseña, ArrayList<ArrayList> usuarios) {
        this.correo = correo;
        this.contraseña = contraseña;

        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);

    }


    public Vendedor LoginVendedor(){
        for (Vendedor vendedor : this.vendedores){
            if (vendedor.GetCorreo().equals(this.correo) && vendedor.GetContraseña().equals(this.contraseña)){
                return vendedor;
            }
        }
        return null;
    }

    public Usuario LoginUsuario(){
        for (Usuario comprador : this.compradores){
            if (comprador.GetCorreo().equals(this.correo) && comprador.GetContraseña().equals(this.contraseña)){
                return comprador;
            }
        }
        return null;
    }

}
