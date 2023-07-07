package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {
    ArrayList<ArrayList> usuarios;
    ArrayList<Usuario> compradores;
    ArrayList<Vendedor> vendedores;

    @BeforeEach
    void SetUp(){
        usuarios= new ArrayList<ArrayList>();
        compradores = new ArrayList<Usuario>();
        vendedores = new ArrayList<Vendedor>();
        usuarios.add(compradores);
        usuarios.add(vendedores);

        Vendedor vendedor1 = new Vendedor("nombre1", "apellido1", "correo1", "rut1", 2, "contraseña1");
        vendedores.add(vendedor1);

        Usuario comprador1 = new Usuario("nombre1", "apellido1", "correo1", "rut1", 2, "contraseña1");
        compradores.add(comprador1);
    }

    @Test
    void loginVendedorTest() {
        Login login = new Login(usuarios);

        //Prueba con vendedor y correo y contraseñas correctos para hacer login
        Vendedor resultado1 = login.LoginVendedor("correo1", "contraseña1");


        assertNotNull(resultado1);
        assertTrue(vendedores.contains(resultado1));

        //Prueba con correo y contraseña que no tiene ningun vendedor

        Vendedor resultado2 = login.LoginVendedor("correo de ningun vendedor", "contraseña de ningun vendedor");

        assertNull(resultado2);

        assertFalse(vendedores.contains(resultado2));
    }

    @Test
    void loginCompradorTest() {
        Login login = new Login(usuarios);

        //Prueba con vendedor y correo y contraseñas correctos para hacer login
        Usuario resultado1 = login.LoginComprador("correo1", "contraseña1");


        assertNotNull(resultado1);
        assertTrue(compradores.contains(resultado1));

        //Prueba con correo y contraseña que no tiene ningun vendedor

        Usuario resultado2 = login.LoginComprador("correo de ningun vendedor", "contraseña de ningun vendedor");

        assertNull(resultado2);

        assertFalse(compradores.contains(resultado2));

    }
}