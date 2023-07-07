package Usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuariosTest {
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    public void setUp() {
        compradores = new ArrayList<>();
        vendedores = new ArrayList<>();
        usuarios = new ArrayList<>();
        usuarios.add(compradores);
        usuarios.add(vendedores);
        gestionUsuarios = new GestionUsuarios(usuarios);
    }

    @Test
    public void testConfirmarEncuentro() {
        Vendedor vendedor = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Usuario comprador1 = new Usuario("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        Usuario comprador2 = new Usuario("nombre3", "apellido3", "correo3","rut3", 3, "contraseña3");

        vendedor.AgregarConfirmacion("rut2");
        compradores.add(comprador1);
        compradores.add(comprador2);

        gestionUsuarios.ConfirmarEncuentro(vendedor, comprador1);

        assertEquals(true, vendedor.ValidarConfirmacion(comprador1.GetRut()+"true"));
        assertEquals(vendedor.GetRut(), comprador1.GetConfirmaciones().get(0));

    }

    @Test
    public void testActualizarVendedores() {
        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        gestionUsuarios.ActualizarVendedores();

    }
}