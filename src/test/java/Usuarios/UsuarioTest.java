package Usuarios;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testGetNombre() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String resultado = usuario.GetNombre();

        assertEquals(nombre, resultado);
    }

    @Test
    public void testGetApellido() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String resultado = usuario.GetApellido();

        assertEquals(apellido, resultado);
    }

    @Test
    public void testGetCorreo() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String resultado = usuario.GetCorreo();

        assertEquals(correo, resultado);
    }

    @Test
    public void testGetRut() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String resultado = usuario.GetRut();

        assertEquals(rut, resultado);
    }

    @Test
    public void testGetContraseña() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String resultado = usuario.GetContraseña();

        assertEquals(contraseña, resultado);
    }

    @Test
    public void testGetNumero() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        int resultado = usuario.GetNumero();

        assertEquals(numero, resultado);
    }

    @Test
    public void testCrearPublicacion() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        Servicio servicio = new Servicio("Limpieza", "Servicio de limpieza a domicilio");
        usuario.CrearPublicacion(servicio);

        ArrayList<Servicio> servicios = usuario.GetServicios();

        assertEquals(1, servicios.size());
        assertEquals(servicio, servicios.get(0));
    }

    @Test
    public void testDevolverStrServicos() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        Servicio servicio1 = new Servicio("Limpieza", "Servicio de limpieza a domicilio");
        Servicio servicio2 = new Servicio("Electricidad", "Instalación y reparación de sistemas eléctricos");
        usuario.CrearPublicacion(servicio1);
        usuario.CrearPublicacion(servicio2);

        String resultado = usuario.DevolverStrServicos();

        String expected = "Limpieza: Servicio de limpieza a domicilio,Electricidad: Instalación y reparación de sistemas eléctricos,";
        assertEquals(expected, resultado);
    }

    @Test
    public void testGetLargoServicios() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        assertEquals(0, usuario.GetLargoServicios());

        Servicio servicio = new Servicio("Limpieza", "Servicio de limpieza a domicilio");
        usuario.CrearPublicacion(servicio);

        assertEquals(1, usuario.GetLargoServicios());
    }

    @Test
    public void testGetListaDeListaServicios() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        Servicio servicio1 = new Servicio("Limpieza", "Servicio de limpieza a domicilio");
        Servicio servicio2 = new Servicio("Electricidad", "Instalación y reparación de sistemas eléctricos");
        usuario.CrearPublicacion(servicio1);
        usuario.CrearPublicacion(servicio2);

        ArrayList<ArrayList> lista = usuario.GetListaDeListaServicios();

        assertEquals(2, lista.size());
        assertEquals(Arrays.asList("Limpieza", "Servicio de limpieza a domicilio"), lista.get(0));
        assertEquals(Arrays.asList("Electricidad", "Instalación y reparación de sistemas eléctricos"), lista.get(1));
    }

    @Test
    public void testAgregarExtServ() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        ArrayList<Servicio> serviciosExternos = new ArrayList<>();
        serviciosExternos.add(new Servicio("Limpieza", "Servicio de limpieza a domicilio"));
        serviciosExternos.add(new Servicio("Electricidad", "Instalación y reparación de sistemas eléctricos"));

        usuario.AgregarExtServ(serviciosExternos);

        ArrayList<Servicio> servicios = usuario.GetServicios();

        assertEquals(2, servicios.size());
        assertEquals(serviciosExternos.get(0), servicios.get(0));
        assertEquals(serviciosExternos.get(1), servicios.get(1));
    }

    @Test
    public void testAgregarConfirmacion() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String rutConfirmacion = "987654321";
        usuario.AgregarConfirmacion(rutConfirmacion);

        ArrayList<String> confirmaciones = usuario.GetConfirmaciones();

        assertEquals(1, confirmaciones.size());
        assertEquals(rutConfirmacion, confirmaciones.get(0));
    }

    @Test
    public void testAgregarConfirmacion_Duplicado() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String rutConfirmacion = "987654321";
        usuario.AgregarConfirmacion(rutConfirmacion);
        usuario.AgregarConfirmacion(rutConfirmacion);

        ArrayList<String> confirmaciones = usuario.GetConfirmaciones();

        assertEquals(1, confirmaciones.size());
        assertEquals(rutConfirmacion, confirmaciones.get(0));
    }

    @Test
    public void testRemoverConfirmacion() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String rutConfirmacion = "987654321";
        usuario.AgregarConfirmacion(rutConfirmacion);

        usuario.RemoverConfirmacion(rutConfirmacion);

        ArrayList<String> confirmaciones = usuario.GetConfirmaciones();

        assertEquals(0, confirmaciones.size());
    }

    @Test
    public void testGetConfirmaciones() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        String rutConfirmacion1 = "987654321";
        String rutConfirmacion2 = "456789123";
        usuario.AgregarConfirmacion(rutConfirmacion1);
        usuario.AgregarConfirmacion(rutConfirmacion2);

        ArrayList<String> confirmaciones = usuario.GetConfirmaciones();

        assertEquals(2, confirmaciones.size());
        assertEquals(rutConfirmacion1, confirmaciones.get(0));
        assertEquals(rutConfirmacion2, confirmaciones.get(1));
    }

    @Test
    public void testAgregarConfExt() {
        String nombre = "John";
        String apellido = "Doe";
        String correo = "john@example.com";
        String rut = "123456789";
        int numero = 123456789;
        String contraseña = "password";
        Usuario usuario = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

        List<String> confirmacionesExternas = Arrays.asList("987654321", "456789123");
        usuario.AgregarConfExt(confirmacionesExternas);

        ArrayList<String> confirmaciones = usuario.GetConfirmaciones();

        assertEquals(2, confirmaciones.size());
        assertEquals(confirmacionesExternas.get(0), confirmaciones.get(0));
        assertEquals(confirmacionesExternas.get(1), confirmaciones.get(1));
    }
}