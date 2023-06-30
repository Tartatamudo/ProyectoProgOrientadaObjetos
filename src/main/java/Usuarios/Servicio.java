package Usuarios;

import java.util.ArrayList;
import java.util.Locale;

public class Servicio {
    private String nombre;
    private String descripcion;

    //Ventana, consola, gestion de archivos
    public Servicio(String nombre, String descripcion){
        this.nombre = nombre.toLowerCase(Locale.ROOT);
        this.descripcion = descripcion;
    }

    //Ventana y conola

    public String GetNombre(){
        return this.nombre;
    }

    //Ventana y consola
    public String GetDescripcion(){
        return  this.descripcion;
    }

    //Gestor archivos
    public ArrayList<String> GetLista(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add(this.nombre);
        lista.add(this.descripcion);
        return lista;
    }
}
