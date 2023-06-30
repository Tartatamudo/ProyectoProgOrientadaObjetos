package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;

import static Login.MenuLogueado.*;
import static Utilidades.Validadores.*;

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

    //Ventana
    public Vendedor LoginVendedor(){
        for (Vendedor vendedor : this.vendedores){
            if (vendedor.GetCorreo().equals(this.correo) && vendedor.GetContraseña().equals(this.contraseña)){
                return vendedor;
            }
        }
        return null;
    }
    //Ventana
    public Usuario LoginUsuario(){
        for (Usuario comprador : this.compradores){
            if (comprador.GetCorreo().equals(this.correo) && comprador.GetContraseña().equals(this.contraseña)){
                return comprador;
            }
        }
        return null;
    }
    public static void MenuLogin(ArrayList<ArrayList> usuarios){//Consola
        ArrayList<Usuario> compradores = usuarios.get(0);
        ArrayList<Vendedor> vendedores = usuarios.get(1);

        String correo = GetCorreo(usuarios);
        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        boolean bool = false;
        for (Usuario comprador : compradores){
            if (comprador.GetCorreo().equals(correo) && comprador.GetContraseña().equals(contraseña)){
                System.out.println("Inicio como comprador");
                MenuLogeadoComprador(usuarios, comprador);
                bool =  true;
            }
        }
        if (bool == false){
            for (Vendedor vendedor : vendedores){
                if (vendedor.GetCorreo().equals(correo) && vendedor.GetContraseña().equals(contraseña)){
                    System.out.println("Inicio como vendedor");
                    MenuLogeadoVendedor(usuarios, vendedor);
                    bool =  true;
                }
            }
        }
        if (bool == false){
            System.out.println("No se encontro una cuenta asociada al correo y contraseña entregados");
        }
    }

}
