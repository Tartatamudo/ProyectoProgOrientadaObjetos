package Usuarioss;

import java.util.ArrayList;

import static Utilidades.ScannerTeclado.getCadena;

public class Servicio {
    private String nombre;
    private String descripcion;

    public Servicio() {
    }
    public void setNombre(){
        nombre = getCadena();
    }
    public void setDescripcion(){
      descripcion = getCadena();
    }
    public void getNombre(){
        System.out.println(nombre);
    }
    public void getDescripcion(){
        System.out.println(descripcion);
    }

    public void getServicio(){
        System.out.println(nombre + ": ");
        System.out.println("    " + descripcion);
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
