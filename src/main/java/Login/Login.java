package Login;

import Datos.GestorArchivos;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;


public class Login {
    private ArrayList<Usuario> compradores = new ArrayList<>();

    private ArrayList<Vendedor> vendedores = new ArrayList<>();

    private ArrayList<ArrayList> usuarios = new ArrayList<>();

    public Login(ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);

    }
    public Login() {
        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.VerificarArchivos();
        gestorArchivos.CargarCompradoresAPrograma(this.compradores);
        gestorArchivos.CargarVendedoresAPrograma(vendedores);

        usuarios.add(compradores);
        usuarios.add(vendedores);
    }

    public ArrayList<ArrayList> DevolverUsuarios(){
        return this.usuarios;
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
