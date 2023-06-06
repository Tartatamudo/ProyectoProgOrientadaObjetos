package Usuarioss;

import java.util.ArrayList;

import static Utilidades.GestorArchivosCompradores.AñadirCompradorArchivo;
import static Utilidades.GestorArchivosVendedores.AñadirVendedorArchivo;
import static Utilidades.ScannerTeclado.getCadena;
import static Utilidades.ScannerTeclado.getNumero;

public class CrearUsuario {

    public static void CrearUsuario(ArrayList<Usuario> compradores, ArrayList<Vendedor> vendedores) {
        System.out.println("Que quieres ser:");
        System.out.println("[1]Comprador");
        System.out.println("[2]Vendedor");

        int eleccion = getNumero();
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
        String nombre = getCadena();

        System.out.println("Ingrese apellido");
        String apellido = getCadena();

        System.out.println("Ingrese rut");
        String rut = getCadena();

        System.out.println("Ingrese correo");
        String correo = getCadena();

        System.out.println("Ingrese contraseña");
        String contraseña = getCadena();

        System.out.println("Ingrese su numero");
        int numero = getNumero();

        Usuario com = new Usuario(nombre, apellido, rut, correo, contraseña, numero);

        compradores.add(com);
        AñadirCompradorArchivo(compradores);
    }

    public static void CrearVendedor(ArrayList<Vendedor> vendedores) {
        System.out.println("Ingrese nombre");
        String nombre = getCadena();

        System.out.println("Ingrese apellido");
        String apellido = getCadena();

        System.out.println("Ingrese rut");
        String rut = getCadena();

        System.out.println("Ingrese correo");
        String correo = getCadena();

        System.out.println("Ingrese contraseña");
        String contraseña = getCadena();

        System.out.println("Ingrese numero");
        int numero = getNumero();

        Vendedor ven = new Vendedor(nombre, apellido, rut, correo, contraseña, numero);

        vendedores.add(ven);
        AñadirVendedorArchivo(vendedores);
    }
}
