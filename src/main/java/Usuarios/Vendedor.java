package Usuarios;

import java.util.ArrayList;
import java.util.List;

import static Utilidades.Validadores.*;

public class Vendedor extends Usuario{

    private ArrayList<Integer> estrellas = new ArrayList<>();
    private ArrayList<String> comentarios = new ArrayList<>();

    public Vendedor(String nombre, String apellido, String correo, String rut, int numero, String contraseña) {
        super(nombre, apellido, correo, rut, numero, contraseña);
    }

    //Consola
    public void ImprimirDatos() {
        System.out.println(GetNombre() + " " + this.apellido + ":");
        System.out.println("    correo: " + this.correo);
        System.out.println("    numero: " + this.numero);
        System.out.println("comentarios:");
        for (int i = 0; i < this.comentarios.size(); i++) {
            System.out.println("    " + this.comentarios.get(i));
        }
        System.out.println("Estrellas:");
        GetnEstrellas();
    }

    //Consola
    public void AgregarEstrellass(){
        int num = -1;

        while(num< 0 || num > 6){
            System.out.println("Escribir numero entre 0 y 5");
            num = GetEntero();
        }
        estrellas.add(num);
    }

    //Consola
    public void AgregarComentarioo(){
        this.comentarios.remove("");
        String comentario = GetCadena();
        this.comentarios.add(comentario);
    }
    //Ventana
    public void AgregarEstrellas(int numero){
        estrellas.add(numero);
    }
    public void AgregarComentario(String comentario){
        this.comentarios.remove("");
        comentarios.add(comentario);
    }
    //Ventana
    public String DevolverStrEstrellas(){
        String texto = "";
        double acum = 0;
        for (int i = 0; i < this.estrellas.size(); i++) {
            acum = acum + this.estrellas.get(i);
        }
        acum = acum/ this.estrellas.size();

        texto = texto + "Promedio: " + String.valueOf(acum) + ";";
        texto = texto + "De un total de: " + String.valueOf(this.estrellas.size()) + " calificaciones";
        return texto;
    }

    //Consola
    public void GetnEstrellas() {
        double acum = 0;
        for (int i = 0; i < this.estrellas.size(); i++) {
            acum = acum + this.estrellas.get(i);
        }
        acum = acum/ this.estrellas.size();
        System.out.print("Promedio: " + acum);
        System.out.println("De un total de: " + this.estrellas.size() + " calificaciones");
    }

    //Ventana y gesstor archivos
    public ArrayList<String> GetComentarios() {
        return this.comentarios;
    }

    //Gestor archivos
    public ArrayList<Integer> GetEstrellas(){
        return this.estrellas;
    }

    //Ventana y consola
    public boolean GetConfirmacion(String rutConf){
        boolean bool = false;
        for (int i = 0; i < this.confirmaciones.size(); i++) {
            if(this.confirmaciones.get(i).equals(rutConf)){
                return true;
            }
        }
        return false;
    }

    //Ventana y consola
    public void CambiarConfirmacion(String rut){
        for (int i = 0; i < this.confirmaciones.size(); i++) {
            if (this.confirmaciones.get(i).contains(rut)){
                String rutConf = rut + "true";
                this.confirmaciones.set(i,rutConf);
            }
        }
    }

    //Gestor archivos
    public void AgregarEstExt(List<Integer> estrellasList){
        for (int i = 0; i < estrellasList.size(); i++) {
            this.estrellas.add(estrellasList.get(i));
        }
    }

    //Gestor archivos
    public void AgregarComExt(List<String> comentariosList){
        for (int i = 0; i < comentariosList.size(); i++) {
            this.comentarios.add(comentariosList.get(i));
        }
    }
}
