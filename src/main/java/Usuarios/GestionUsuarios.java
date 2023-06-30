package Usuarios;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;

import static Utilidades.Validadores.GetEntero;

public class GestionUsuarios {
    //impresion de los perfiles cuando se pida. Se pide el numero de la posicin del usuario en su respectivo Arraylist

    //Consola
    public static void ImprimirPerfilVendedor(ArrayList<Vendedor> vendedores, Usuario comprador){
        System.out.println("Eliga el numer del vendedor que desea ver");
        int num = GetEntero();
        if (vendedores.get(num) != null){
            vendedores.get(num).ImprimirDatos();

            vendedores.get(num).AgregarConfirmacion(comprador.GetRut());
        }else{
            System.out.println("El vendedor que eligio no existe");
        }
    }

    //Consola
    public static void ImprimirPerfilComprador(ArrayList<Usuario> compradores){
        System.out.println("Eliga el numer del vendedor que desea ver");

        int num = GetEntero();
        if (compradores.get(num) != null){
            compradores.get(num).ImprimirDatos();
        }else{
            System.out.println("El vendedor que eligio no existe");
        }
    }
}
