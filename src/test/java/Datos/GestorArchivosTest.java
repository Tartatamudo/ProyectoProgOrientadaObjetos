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

        // Prueba que no se creen los archivos si ya existen
        gestorArchivos.VerificarArchivos();
        assertTrue(validadores.ValidarArchivos("Compradores.csv"));
    }
}