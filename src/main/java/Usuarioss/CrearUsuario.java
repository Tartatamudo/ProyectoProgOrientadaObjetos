package Usuarioss;

import java.util.ArrayList;

import static Utilidades.GestorArchivosCompradores.AñadirCompradorArchivo;
import static Utilidades.GestorArchivosVendedores.AñadirVendedorArchivo;
import static Utilidades.Validadores.*;

public class CrearUsuario {

    public static void CrearUsuario(ArrayList<Usuario> compradores, ArrayList<Vendedor> vendedores) {
        System.out.println("Que quieres ser:");
        System.out.println("[1]Comprador");
        System.out.println("[2]Vendedor");

        int eleccion = GetEntero();
        switch (eleccion) {
            case 1:
                CrearComprador(compradores);
                break;
            case 2:
                CrearVendedor(vendedores);
                break;
        }
    }

    //Estos son los creadores de usuarios especificos a sus Arraylist
    public static void CrearComprador(ArrayList<Usuario> compradores) {
        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut();

        String correo = GetCorreo();

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Usuario com = new Usuario(nombre, apellido, rut, correo, numero, contraseña);

        compradores.add(com);
        AñadirCompradorArchivo(compradores);
    }

    public static void CrearVendedor(ArrayList<Vendedor> vendedores) {
        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut();

        String correo = GetCorreo();

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Vendedor ven = new Vendedor(nombre, apellido, rut, correo, numero, contraseña);

        vendedores.add(ven);
        AñadirVendedorArchivo(vendedores);
    }
}
