package Utilidades;

import Datos.GestionArchivos;

import Usuarios.Usuario;
import Usuarios.Vendedor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadoresTest {

    @Test
    public void testValidarRut() {
        Validadores validadores = new Validadores();

        ArrayList<ArrayList> usuarios = new ArrayList<>();
        ArrayList<Usuario> compradores = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        usuarios.add(compradores);
        usuarios.add(vendedores);

        Usuario comprador1 = new Usuario("John", "Doe", "john@example.com", "123456789", 123456789, "password");
        Usuario comprador2 = new Usuario("Jane", "Smith", "jane@example.com", "987654321", 987654321, "password");
        compradores.add(comprador1);
        compradores.add(comprador2);

        assertTrue(validadores.ValidarRut("21079526k", usuarios));
        assertFalse(validadores.ValidarRut("987..65..432-1", usuarios));
        assertFalse(validadores.ValidarRut("12.345.678-0", usuarios));
        assertFalse(validadores.ValidarRut("98765432-0", usuarios));
        assertFalse(validadores.ValidarRut("12.345.678-9", usuarios));
    }

    @Test
    public void testValidarCorreoElectronico() {
        Validadores validadores = new Validadores();

        ArrayList<ArrayList> usuarios = new ArrayList<>();
        ArrayList<Usuario> compradores = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        usuarios.add(compradores);
        usuarios.add(vendedores);

        Usuario comprador1 = new Usuario("John", "Doe", "john@example.com", "12345678-9", 123456789, "password");
        Usuario comprador2 = new Usuario("Jane", "Smith", "jane@example.com", "98765432-1", 987654321, "password");
        compradores.add(comprador1);
        compradores.add(comprador2);

        assertTrue(validadores.ValidarCorreoElectronico("example@example.com", usuarios));
        assertFalse(validadores.ValidarCorreoElectronico("johnexample.com", usuarios));
        assertFalse(validadores.ValidarCorreoElectronico("jane@example.com", usuarios));
        assertFalse(validadores.ValidarCorreoElectronico("john@example.com", usuarios));
        assertFalse(validadores.ValidarCorreoElectronico("john.doe@example", usuarios));
        assertFalse(validadores.ValidarCorreoElectronico("jane.smith@example.", usuarios));
    }

    @Test
    public void testValidarNumero() {
        Validadores validadores = new Validadores();

        assertTrue(validadores.ValidarNumero("12345678"));
        assertFalse(validadores.ValidarNumero("23456781-6"));
        assertFalse(validadores.ValidarNumero("1234567-89"));
        assertFalse(validadores.ValidarNumero("1234567"));
        assertFalse(validadores.ValidarNumero("123456789"));
    }

    @Test
    public void testValidarArchivos() {
        Validadores validadores = new Validadores();
        GestionArchivos gestionArchivos = new GestionArchivos();
        String ruta1 = "archivoPrueba1.csv";
        String ruta2 = "archivoPrueba2.csv";
        String ruta3 = "archivoPrueba3.csv";
        gestionArchivos.CrearArchivo(ruta1, "");
        gestionArchivos.CrearArchivo(ruta2, "");
        validadores.ValidarArchivos(ruta1);

        assertTrue(validadores.ValidarArchivos(ruta1));
        assertTrue(validadores.ValidarArchivos(ruta2));
        assertFalse(validadores.ValidarArchivos(ruta3));
    }
}