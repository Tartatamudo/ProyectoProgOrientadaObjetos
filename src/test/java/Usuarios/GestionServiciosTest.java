package Usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestionServiciosTest {
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private GestionServicios gestionServicios;

    @BeforeEach
    public void setUp() {
        compradores = new ArrayList<>();
        vendedores = new ArrayList<>();
        usuarios = new ArrayList<>();
        usuarios.add(compradores);
        usuarios.add(vendedores);
        gestionServicios = new GestionServicios(usuarios);
    }

    @Test
    public void testDevolverStrServiciosCompra() {
        Usuario comprador1 = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Usuario comprador2 = new Usuario("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        comprador1.CrearPublicacion(new Servicio("Servicio 1", "Descripcion 1"));
        comprador2.CrearPublicacion(new Servicio("Servicio 2", "Descripcion 2"));
        compradores.add(comprador1);
        compradores.add(comprador2);

        String resultado = gestionServicios.DevolverStrServiciosCompra(compradores);

        String expected = "Comprador 0. Servicio 1: Descripcion 1,;"
                + "Comprador 1. Servicio 2: Descripcion 2,;";
        assertEquals(expected, resultado);
    }

    @Test
    public void testDevolverStrServiciosVenta() {
        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        vendedor1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        vendedor2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        String resultado = gestionServicios.DevolverStrServiciosVenta(vendedores);

        String expected = "Vendedor 0. Servicio 1: Descripción 1,;"
                + "Vendedor 1. Servicio 2: Descripción 2,;";
        assertEquals(expected, resultado);
    }

    @Test
    public void testDevolverStrEleccionVenta() {
        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        vendedor1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        vendedor2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        String resultado = gestionServicios.DevolverStrEleccionVenta("servicio 1", vendedores);

        String expected = "Vendedor 0: Servicio 1: Descripción 1;";
        assertEquals(expected, resultado);
    }

    @Test
    public void testDevolverStrEleccionCompra() {
        Usuario comprador1 = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Usuario comprador2 = new Usuario("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        comprador1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        comprador2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        compradores.add(comprador1);
        compradores.add(comprador2);

        String resultado = gestionServicios.DevolverStrEleccionCompra("Servicio 2", compradores);

        String expected = "Comprador 1: Servicio 2: Descripción 2;";
        assertEquals(expected, resultado);
    }

    @Test
    public void testAgregarServicioComprador() {
        Usuario comprador = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Servicio servicio = new Servicio("Servicio 1", "Descripción 1");

        gestionServicios.AgregarServicioComprador(servicio, comprador);

        assertTrue(comprador.GetServicios().contains(servicio));

        // Verificar que se haya llamado al método ActualizarCompradores en la clase GestionUsuarios
        // (Puedes agregar aquí la lógica para verificar si se llamó al método)
    }

    @Test
    public void testAgregarServicioVendedor() {
        Vendedor vendedor = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Servicio servicio = new Servicio("Servicio 2", "Descripción 2");

        gestionServicios.AgregarServicioVendedor(servicio, vendedor);

        assertTrue(vendedor.GetServicios().contains(servicio));

        // Verificar que se haya llamado al método ActualizarVendedores en la clase GestionUsuarios
        // (Puedes agregar aquí la lógica para verificar si se llamó al método)
    }

    @Test
    public void testDevolverVendedoresConPublicaciones() {
        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        vendedor1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        vendedor2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        //Comprobar si no se añaden los compradores si no tienen servicios
        ArrayList<Vendedor> resultado = gestionServicios.DevolverVendedoresFiltro(vendedores, "Servicio 1");

        assertNotEquals(vendedores, resultado);

        //ahora se les agragan servicios y se ve si son iguales
        vendedor1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        vendedor1.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));

        resultado = gestionServicios.DevolverVendedoresConPublicaciones(vendedores);
        assertEquals(vendedores, resultado);
    }

    @Test
    public void testDevolverCompradoresConPublicaciones() {
        Usuario comprador1 = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Usuario comprador2 = new Usuario("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        compradores.add(comprador1);
        compradores.add(comprador2);

        //Comprobar si no se añaden los compradores si no tienen servicios
        ArrayList<Usuario> resultado = gestionServicios.DevolverCompradoresConPublicaciones(compradores);

        assertNotEquals(compradores, resultado);

        //ahora se les agragan servicios y se ve si son iguales
        comprador1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        comprador2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));

        resultado = gestionServicios.DevolverCompradoresConPublicaciones(compradores);
        assertEquals(compradores, resultado);
    }

    @Test
    public void testDevolverVendedoresFiltro() {
        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        vendedor1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        vendedor2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        ArrayList<Vendedor> resultado = gestionServicios.DevolverVendedoresFiltro(vendedores, "Servicio 1");

        ArrayList<Vendedor> expected = new ArrayList<>();
        expected.add(vendedor1);
        assertEquals(expected, resultado);
    }

    @Test
    public void testDevolverCompradoresFiltro() {
        Usuario comprador1 = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Usuario comprador2 = new Usuario("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");

        comprador1.CrearPublicacion(new Servicio("Servicio 1", "Descripción 1"));
        comprador2.CrearPublicacion(new Servicio("Servicio 2", "Descripción 2"));
        compradores.add(comprador1);
        compradores.add(comprador2);

        ArrayList<Usuario> resultado = gestionServicios.DevolverCompradoresFiltro(compradores, "Servicio 2");

        ArrayList<Usuario> expected = new ArrayList<>();
        expected.add(comprador2);
        assertEquals(expected, resultado);
    }
}