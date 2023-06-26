package Usuarios;

import java.util.ArrayList;

import static Utilidades.Validadores.GetCadena;

public class Servicio {
    private String nombre;
    private String descripcion;
    
    public Servicio() {
    }
    public void SetNombre(){
        this.nombre = GetCadena();
    }
    public void SetDescripcion(){
        this.descripcion = GetCadena();
    }
    public String GetNombre(){
        return this.nombre;
    }
    public String GetDescripcion(){
        return  this.descripcion;
    }

    public void GetServicio(){
        System.out.println("    " + this.nombre + ": ");
        System.out.println("        " + this.descripcion);
    }
    public ArrayList<String> GetLista(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add(this.nombre);
        lista.add(this.descripcion);
        return lista;
    }
    public void SetExtNombre(String nom){
        this.nombre = nom;
    }
    public void SetExtDescricion(String desc){
        this.descripcion = desc;
    }
}
