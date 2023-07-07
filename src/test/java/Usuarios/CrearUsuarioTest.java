package Usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CrearUsuarioTest {
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;
    private CrearUsuario crearUsuario;

    @BeforeEach
    public void setUp() {
        compradores = new ArrayList<>();
        vendedores = new ArrayList<>();
        usuarios = new ArrayList<>();
        usuarios.add(compradores);
        usuarios.add(vendedores);
        crearUsuario = new CrearUsuario(usuarios);
    }

    @Test
    public void testCrearComprador() {
        Usuario comprador = new Usuario("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        crearUsuario.CrearComprador(comprador);

        // Verificar que el comprador se agregó correctamente
        assertEquals(1, compradores.size());
        assertTrue(compradores.contains(comprador));

        // Verificar que el archivo se actualizó correctamente
        // (Puedes agregar aquí la lógica para verificar el archivo)
    }

    @Test
    public void testCrearVendedor() {
        Vendedor vendedor = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        crearUsuario.CrearVendedor(vendedor);

        // Verificar que el vendedor se agregó correctamente
        assertEquals(1, vendedores.size());
        assertTrue(vendedores.contains(vendedor));

        // Verificar que el archivo se actualizó correctamente
        // (Puedes agregar aquí la lógica para verificar el archivo)
    }
}