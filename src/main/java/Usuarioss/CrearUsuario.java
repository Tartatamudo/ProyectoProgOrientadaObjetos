package Usuarioss;

import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.AñadirCompradorArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedorArchivo;
import static Utilidades.Validadores.*;

public class CrearUsuario {

    public static void CrearUsuario(ArrayList<ArrayList> usuarios) {
        System.out.println("Que quieres ser:");
        System.out.println("[1]Comprador");
        System.out.println("[2]Vendedor");

        int eleccion = GetEntero();
        switch (eleccion) {
            case 1:
                CrearComprador(usuarios);
                break;
            case 2:
                CrearVendedor(usuarios);
                break;
        }
    }

    //Estos son los creadores de usuarios especificos a sus Arraylist
    public static void CrearComprador(ArrayList<ArrayList> usuarios) {
        ArrayList<Usuario> compradores = usuarios.get(0);

        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut(usuarios);

        String correo = GetCorreo();

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Usuario com = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        compradores.add(com);
        AñadirCompradorArchivo(com);
    }

    public static void CrearVendedor(ArrayList<ArrayList> usuarios) {
        ArrayList<Vendedor> vendedores = usuarios.get(1);
        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut(usuarios);

        String correo = GetCorreo();

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Vendedor ven = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

        vendedores.add(ven);
        AñadirVendedorArchivo(ven);
    }
}
