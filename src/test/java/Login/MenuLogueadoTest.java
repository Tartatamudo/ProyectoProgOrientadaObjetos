package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MenuLogueadoTest {
    private MenuLogueado menuLogueado;
    private ArrayList<Vendedor> vendedoresConf;
    private ArrayList<Usuario> compradoresConf;

    @BeforeEach
    public void setUp() {
        menuLogueado = new MenuLogueado();

        // Preparación de datos de prueba para los métodos
        vendedoresConf = new ArrayList<>();
        compradoresConf = new ArrayList<>();

        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1","rut1", 1, "contraseña1");
        vendedoresConf.add(vendedor1);

        Vendedor vendedor2 = new Vendedor("nombre2", "apellido2", "correo2","rut2", 2, "contraseña2");

        vendedoresConf.add(vendedor2);
        Usuario comprador1 = new Usuario("nombre3", "apellido3", "correo3","rut3", 3, "contraseña3");
        compradoresConf.add(comprador1);

        Usuario comprador2 = new Usuario("nombre4", "apellido4", "correo4","rut4", 4, "contraseña4");
        compradoresConf.add(comprador2);
    }

    @Test
    public void testDevolverStrEvaluarVendedor() {
        vendedoresConf.get(0).AgregarConfirmacion("rut3true");
        String resultado = menuLogueado.DevolverStrEvaluarVendedor(vendedoresConf, compradoresConf.get(0));

        // Verificar que el resultado es el esperado
        assertEquals("0. nombre1 apellido1;", resultado);
        vendedoresConf.get(0).RemoverConfirmacion("rut3true");
    }

    @Test
    public void testVendedoresConfirm() {
        vendedoresConf.get(0).AgregarConfirmacion("rut3true");
        compradoresConf.get(0).AgregarConfirmacion("rut1");

        ArrayList<Vendedor> resultado = menuLogueado.VendedoresConfirm(vendedoresConf, compradoresConf.get(0));

        // Verificar que el resultado contiene los vendedores esperados
        assertEquals(1, resultado.size());

        //Confirma que el ArrayList resultado es igual al arrray
        ArrayList<Vendedor> expect = new ArrayList<>();
        expect.add(vendedoresConf.get(0));

        assertEquals(expect, resultado);
    }

    //////////////////////////////
    @Test
    public void testConfirmarContacto() {
        Vendedor vendedor = new Vendedor("nombre4", "apellido4", "correo4","rut4", 4, "contraseña4");
        vendedor.AgregarConfirmacion(compradoresConf.get(0).GetRut());
        vendedor.AgregarConfirmacion(compradoresConf.get(1).GetRut());

        ArrayList<Usuario> resultado = menuLogueado.DevolverListaRutAConfirmar(vendedor, compradoresConf);

        // Verificar que el resultado contiene los compradores esperados
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(compradoresConf.get(0)));
        assertTrue(resultado.contains(compradoresConf.get(1)));
    }

    @Test
    public void testStrConfirmarContacto() {
        String resultado = menuLogueado.DevolverStrCompradoresConfirmados(compradoresConf);

        // Verificar que el resultado es el esperado
        assertEquals("0. nombre3 apellido3;1. nombre4 apellido4;", resultado);
    }
}