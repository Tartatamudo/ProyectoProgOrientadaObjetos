package Usuarioss;

import java.util.ArrayList;

public class Vendedor extends Usuario{

    private ArrayList<Integer> estrellas;
    private ArrayList<String> comentarios;

    public Vendedor(String nombre, String apellido, String correo, String rut, String contraseña, int numero) {
        super(nombre, apellido, correo, rut, contraseña, numero);
    }
    public void getDatos() {
        System.out.println(getNombre() + " " + apellido + ":");
        System.out.println("correo: " + correo);
        System.out.println("numero: " + numero);
        getComentarios();
        getnEstrellas();
    }

    public void AgregarEstrellas(){
        int num = -1;

        while(num>= 0 && num<6){
            System.out.println("Escribir numero entre 0 y 5");
            //num = Proyect.getNumero();
        }
        estrellas.add(num);
    }
    public void AgregarComentario(){
        //String comentario = Proyect.getCadena();
        //comentarios.add(comentario);
    }

    public void getnEstrellas() {
        int acum = 0;
        for (int i = 0; i < estrellas.size(); i++) {
            acum = acum + i;
        }
        acum = acum/ estrellas.size();
        System.out.println("Promedio: " + acum);
        System.out.println("De un total de: " + estrellas.size());
    }

    public void getComentarios() {
        for (int i = 0; i < comentarios.size(); i++) {
            System.out.println(comentarios.get(i));
        }
    }
}
