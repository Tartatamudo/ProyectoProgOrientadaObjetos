package Usuarioss;

import java.util.ArrayList;

import static Utilidades.Validadores.GetCadena;

public class Servicio {
    private String nombre;
    private String descripcion;
    
    public Servicio() {
    }
    public void setNombre(){
        nombre = GetCadena();
    }
    public void setDescripcion(){
      descripcion = GetCadena();
    }
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return  descripcion;
    }

    public void getServicio(){
        System.out.println("    " + nombre + ": ");
        System.out.println("        " + descripcion);
    }
    public ArrayList<String> getLista(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add(nombre);
        lista.add(descripcion);
        return lista;
    }
    public void setExtNombre(String nom){
        nombre = nom;
    }
    public void setExtDescricion(String desc){
        descripcion = desc;
    }
}
