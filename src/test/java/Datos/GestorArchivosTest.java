package Datos;

import Usuarios.Usuario;
import Usuarios.Vendedor;
import Utilidades.Validadores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestorArchivosTest {
    private GestorArchivos gestorArchivos;
    private Validadores validadores;
    private GestionArchivos gestionArchivos;

    @BeforeEach
    public void setUp() {
        gestorArchivos = new GestorArchivos();
        validadores = new Validadores();
        gestionArchivos = new GestionArchivos();
    }

    @Test
    public void testVerificarArchivos() {
        // Prueba que se creen los archivos si no existen
        gestorArchivos.VerificarArchivos();
        assertTrue(validadores.ValidarArchivos("Compradores.csv"));
        assertTrue(validadores.ValidarArchivos("vendedores.csv"));

        // Prueba que no se creen los archivos si ya existen
        gestorArchivos.VerificarArchivos();
        assertTrue(validadores.ValidarArchivos("Compradores.csv"));
        assertTrue(validadores.ValidarArchivos("Compradores.csv"));
    }

    @Test
    public void testAñadirCompradoresArchivo() {
        ArrayList<Usuario> compradores = new ArrayList<>();
        Usuario comprador1 = new Usuario("John", "Doe", "john.doe@example.com", "123456789", 123, "password");
        Usuario comprador2 = new Usuario("Jane", "Doe", "jane.doe@example.com", "987654321", 456, "password");
        compradores.add(comprador1);
        compradores.add(comprador2);

        gestorArchivos.AñadirCompradoresArchivo(compradores);

        String contenidoArchivo = gestionArchivos.LeerArchivo("Compradores.csv");
        assertTrue(contenidoArchivo.contains("John;Doe;john.doe@example.com;123456789;123;password;"));
        assertTrue(contenidoArchivo.contains("Jane;Doe;jane.doe@example.com;987654321;456;password;"));
    }

    @Test
    public void testCargarCompradoresAPrograma() {
        //Con este test se testean todos los demas metodos de esta clase que tienen relacion a los compradores
        gestionArchivos.CrearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;confirmaciones;servicios;");
        gestionArchivos.NuevaLinea("Compradores.csv", "John;Doe;john.doe@example.com;123456789;123;password;[conf1,conf2];[serv1,desc1,serv2,desc2];");
        gestionArchivos.NuevaLinea("Compradores.csv", "Jane;Doe;jane.doe@example.com;987654321;456;password;[conf3];[serv3,desc3];");

        ArrayList<Usuario> compradores = new ArrayList<>();
        gestorArchivos.CargarCompradoresAPrograma(compradores);

        assertEquals(2, compradores.size());

        Usuario comprador1 = compradores.get(0);
        assertEquals("John", comprador1.GetNombre());
        assertEquals("Doe", comprador1.GetApellido());
        assertEquals("john.doe@example.com", comprador1.GetCorreo());
        assertEquals("123456789", comprador1.GetRut());
        assertEquals(123, comprador1.GetNumero());
        assertEquals("password", comprador1.GetContraseña());
        assertEquals(2, comprador1.GetConfirmaciones().size());
        assertEquals(2, comprador1.GetListaDeListaServicios().size());

        Usuario comprador2 = compradores.get(1);
        assertEquals("Jane", comprador2.GetNombre());
        assertEquals("Doe", comprador2.GetApellido());
        assertEquals("jane.doe@example.com", comprador2.GetCorreo());
        assertEquals("987654321", comprador2.GetRut());
        assertEquals(456, comprador2.GetNumero());
        assertEquals("password", comprador2.GetContraseña());
        assertEquals(1, comprador2.GetConfirmaciones().size());
        assertEquals(1, comprador2.GetListaDeListaServicios().size());
    }
    @Test
    public void testCargarVendedoresAPrograma() {

        //Con este test se testean todos los demas metodos de esta clase que tienen relacion a los vendedores
        gestionArchivos.CrearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;estrellas;comentarios;confirmaciones;servicios;");
        gestionArchivos.NuevaLinea("vendedores.csv", "John;Doe;john.doe@example.com;123456789;123;password;[1,2,3];[com1,com2];[conf1,conf2];[serv1,desc1,serv2,desc2];");
        gestionArchivos.NuevaLinea("vendedores.csv", "Jane;Doe;jane.doe@example.com;987654321;456;password;[4,5,6];[com3];[conf3];[serv3,desc3];");

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        gestorArchivos.CargarVendedoresAPrograma(vendedores);

        assertEquals(2, vendedores.size());

        Vendedor vendedor1 = vendedores.get(0);
        assertEquals("John", vendedor1.GetNombre());
        assertEquals("Doe", vendedor1.GetApellido());
        assertEquals("john.doe@example.com", vendedor1.GetCorreo());
        assertEquals("123456789", vendedor1.GetRut());
        assertEquals(123, vendedor1.GetNumero());
        assertEquals("password", vendedor1.GetContraseña());
        assertEquals(3, vendedor1.GetEstrellas().size());
        assertEquals(2, vendedor1.GetComentarios().size());
        assertEquals(2, vendedor1.GetConfirmaciones().size());
        assertEquals(2, vendedor1.GetListaDeListaServicios().size());

        Vendedor vendedor2 = vendedores.get(1);
        assertEquals("Jane", vendedor2.GetNombre());
        assertEquals("Doe", vendedor2.GetApellido());
        assertEquals("jane.doe@example.com", vendedor2.GetCorreo());
        assertEquals("987654321", vendedor2.GetRut());
        assertEquals(456, vendedor2.GetNumero());
        assertEquals("password", vendedor2.GetContraseña());
        assertEquals(3, vendedor2.GetEstrellas().size());
        assertEquals(1, vendedor2.GetComentarios().size());
        assertEquals(1, vendedor2.GetConfirmaciones().size());
        assertEquals(1, vendedor2.GetListaDeListaServicios().size());
    }

}