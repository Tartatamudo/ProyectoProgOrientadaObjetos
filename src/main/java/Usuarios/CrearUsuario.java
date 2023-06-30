package Usuarios;

import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.AñadirCompradorArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedorArchivo;
import static Utilidades.Validadores.*;

public class CrearUsuario {
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    //Constructor menu consola
    public CrearUsuario(ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
        CrearUsuarioMetod();
    }

    //Constructores Ventanas
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
    //Crear usuarios ventana
    public void CrearComprador(Usuario comprador) {
        this.compradores.add(comprador);
        AñadirCompradorArchivo(comprador);
    }
    private void CrearVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);
        AñadirVendedorArchivo(vendedor);
    }
    //-------------------------------------------------------------
    //Crear usuarios consola
    public  void CrearUsuarioMetod() {
        System.out.println("Que quieres ser:");
        System.out.println("[1]Comprador");
        System.out.println("[2]Vendedor");

        int eleccion = GetEntero();
        switch (eleccion) {
            case 1:
                CrearCompradorr();
                break;
            case 2:
                CrearVendedorr();
                break;
        }
    }

    //Estos son los creadores de usuarios especificos a sus Arraylist
    public void CrearCompradorr() {
        ArrayList<Usuario> compradores = this.usuarios.get(0);

        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut(this.usuarios);

        String correo = GetCorreo(this.usuarios);

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Usuario comprador = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        compradores.add(comprador);
        AñadirCompradorArchivo(comprador);
    }

    public void CrearVendedorr() {
        ArrayList<Vendedor> vendedores = usuarios.get(1);
        System.out.println("Ingrese nombre");
        String nombre = GetCadena();

        System.out.println("Ingrese apellido");
        String apellido = GetCadena();

        String rut = GetRut(this.usuarios);

        String correo = GetCorreo(this.usuarios);

        System.out.println("Ingrese contraseña");
        String contraseña = GetCadena();

        int numero = GetNumero();

        Vendedor ven = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

        vendedores.add(ven);
        AñadirVendedorArchivo(ven);
    }
}
