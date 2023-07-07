package Usuarios;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioTest {

    @Test
    public void testGetNombre() {
        String nombre = "Servicio de plomería";
        String descripcion = "Reparación de tuberías y grifos";
        Servicio servicio = new Servicio(nombre, descripcion);

        String resultado = servicio.GetNombre();

        assertEquals(nombre, resultado);
    }

    @Test
    public void testGetDescripcion() {
        String nombre = "Servicio de electricidad";
        String descripcion = "Instalación y reparación de sistemas eléctricos";
        Servicio servicio = new Servicio(nombre, descripcion);

        String resultado = servicio.GetDescripcion();

        assertEquals(descripcion, resultado);
    }

    @Test
    public void testGetLista() {
        String nombre = "Servicio de pintura";
        String descripcion = "Pintado de interiores y exteriores";
        Servicio servicio = new Servicio(nombre, descripcion);

        ArrayList<String> lista = servicio.GetLista();
        ArrayList<String> expect = new ArrayList<>();
        expect.add("Servicio de pintura");
        expect.add("Pintado de interiores y exteriores");

        assertEquals(expect, lista);
    }
}