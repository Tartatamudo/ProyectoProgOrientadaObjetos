package Usuarioss;

import java.util.ArrayList;
import java.util.List;

import static Utilidades.Validadores.*;

public class Vendedor extends Usuario{

    private ArrayList<Integer> estrellas = new ArrayList<>();
    private ArrayList<String> comentarios = new ArrayList<>();

    public Vendedor(String nombre, String apellido, String correo, String rut, int numero, String contraseña) {
        super(nombre, apellido, correo, rut, numero, contraseña);
    }
    public void getDatos() {
        System.out.println(getNombre() + " " + apellido + ":");
        System.out.println("    correo: " + correo);
        System.out.println("    numero: " + numero);
        System.out.println("comentarios:");
        for (int i = 0; i < comentarios.size(); i++) {
            System.out.println("    " + comentarios.get(i));
        }
        System.out.println("Estrellas:");
        getnEstrellas();
    }

    public void AgregarEstrellas(){
        int num = -1;

        while(num< 0 || num > 6){
            System.out.println("Escribir numero entre 0 y 5");
            num = GetEntero();
        }
        estrellas.add(num);
    }
    public void AgregarComentario(){
        comentarios.remove("");
        String comentario = GetCadena();
        comentarios.add(comentario);
    }

    public void getnEstrellas() {
        double acum = 0;
        for (int i = 0; i < estrellas.size(); i++) {
            acum = acum + estrellas.get(i);
        }
        acum = acum/ estrellas.size();
        System.out.print("Promedio: " + acum);
        System.out.println("De un total de: " + estrellas.size() + " calificaciones");
    }

    public ArrayList<String> GetComentarios() {
        return comentarios;
    }
    public ArrayList<Integer> GetEstrellas(){
        return estrellas;
    }
    public boolean GetConfirmacion(String rutConf){
        boolean bool = false;
        for (int i = 0; i < confirmaciones.size(); i++) {
            if(confirmaciones.get(i).equals(rutConf)){
                return true;
            }
        }
        return false;
    }
    public void CambiarConfirmacion(String rut){
        for (int i = 0; i < confirmaciones.size(); i++) {
            if (confirmaciones.get(i).contains(rut)){
                System.out.println(confirmaciones.get(i));
                String rutConf = rut + "true";
                confirmaciones.set(i,rutConf);
            }
        }
    }

    public void AgregarEstExt(List<Integer> estrellasList){
        for (int i = 0; i < estrellasList.size(); i++) {
            estrellas.add(estrellasList.get(i));
        }
    }

    public void AgregarComExt(List<String> comentariosList){
        for (int i = 0; i < comentariosList.size(); i++) {
            comentarios.add(comentariosList.get(i));
        }
    }
}
