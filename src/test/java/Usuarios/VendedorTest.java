package Usuarios;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendedorTest {

    @Test
    public void testAgregarEstrellas() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        vendedor.AgregarEstrellas(4);

        ArrayList<Integer> estrellas = vendedor.GetEstrellas();

        assertEquals(1, estrellas.size());
        assertEquals(Integer.valueOf(4), estrellas.get(0));
    }

    @Test
    public void testAgregarComentario() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        vendedor.AgregarComentario("Buen vendedor");
        vendedor.AgregarComentario("Excelente servicio");

        ArrayList<String> comentarios = vendedor.GetComentarios();

        assertEquals(2, comentarios.size());
        assertEquals("Buen vendedor", comentarios.get(0));
        assertEquals("Excelente servicio", comentarios.get(1));
    }

    @Test
    public void testDevolverStrEstrellas() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        vendedor.AgregarEstrellas(4);
        vendedor.AgregarEstrellas(5);
        vendedor.AgregarEstrellas(3);

        String resultado = vendedor.DevolverStrEstrellas();

        String expected = "Promedio: 4.0;De un total de: 3 calificaciones";
        assertEquals(expected, resultado);
    }

    @Test
    public void testValidarConfirmacion() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        vendedor.AgregarConfirmacion("987654321true");
        vendedor.AgregarConfirmacion("456789123false");

        assertTrue(vendedor.ValidarConfirmacion("987654321true"));
        assertFalse(vendedor.ValidarConfirmacion("123456789true"));
        assertFalse(vendedor.ValidarConfirmacion("456789123true"));
    }

    @Test
    public void testCambiarConfirmacion() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        vendedor.AgregarConfirmacion("987654321true");
        vendedor.AgregarConfirmacion("456789123false");

        vendedor.CambiarConfirmacion("987654321");

        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        assertEquals(2, confirmaciones.size());
        assertEquals("987654321true", confirmaciones.get(0));
        assertEquals("456789123false", confirmaciones.get(1));

        vendedor.CambiarConfirmacion("456789123");

        confirmaciones = vendedor.GetConfirmaciones();

        assertEquals(2, confirmaciones.size());
        assertEquals("987654321true", confirmaciones.get(0));
        assertEquals("456789123true", confirmaciones.get(1));
    }

    @Test
    public void testAgregarEstExt() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        List<Integer> estrellasExternas = Arrays.asList(4, 5, 3);
        vendedor.AgregarEstExt(estrellasExternas);

        ArrayList<Integer> estrellas = vendedor.GetEstrellas();

        assertEquals(3, estrellas.size());
        assertEquals(Integer.valueOf(4), estrellas.get(0));
        assertEquals(Integer.valueOf(5), estrellas.get(1));
        assertEquals(Integer.valueOf(3), estrellas.get(2));
    }

    @Test
    public void testAgregarComExt() {
        Vendedor vendedor = new Vendedor("John", "Doe", "john@example.com", "123456789", 123456789, "password");

        List<String> comentariosExternos = Arrays.asList("Buen vendedor", "Excelente servicio");
        vendedor.AgregarComExt(comentariosExternos);

        ArrayList<String> comentarios = vendedor.GetComentarios();

        assertEquals(2, comentarios.size());
        assertEquals("Buen vendedor", comentarios.get(0));
        assertEquals("Excelente servicio", comentarios.get(1));
    }
}