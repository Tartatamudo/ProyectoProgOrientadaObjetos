package Usuarios;

import java.util.ArrayList;
import java.util.Locale;

public class Servicio {
    private String nombre;
    private String descripcion;


    public Servicio(String nombre, String descripcion){
        this.nombre = nombre.toLowerCase(Locale.ROOT);
        this.descripcion = descripcion;
    }



    public String GetNombre(){
        return this.nombre;
    }


    public String GetDescripcion(){
        return  this.descripcion;
    }


    public ArrayList<String> GetLista(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add(this.nombre);
        lista.add(this.descripcion);
        return lista;
    }
}
