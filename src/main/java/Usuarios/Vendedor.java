package Usuarios;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario{

    private ArrayList<Integer> estrellas = new ArrayList<>();
    private ArrayList<String> comentarios = new ArrayList<>();

    public Vendedor(String nombre, String apellido, String correo, String rut, int numero, String contraseña) {
        super(nombre, apellido, correo, rut, numero, contraseña);
    }

    public void AgregarEstrellas(int numero){
        estrellas.add(numero);
    }
    public void AgregarComentario(String comentario){
        this.comentarios.remove("");
        comentarios.add(comentario);
    }

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


    public ArrayList<String> GetComentarios() {
        return this.comentarios;
    }


    public ArrayList<Integer> GetEstrellas(){
        return this.estrellas;
    }


    public boolean GetConfirmacion(String rutConf){
        boolean bool = false;
        for (int i = 0; i < this.confirmaciones.size(); i++) {
            if(this.confirmaciones.get(i).equals(rutConf)){
                return true;
            }
        }
        return false;
    }


    public void CambiarConfirmacion(String rut){
        for (int i = 0; i < this.confirmaciones.size(); i++) {
            if (this.confirmaciones.get(i).contains(rut)){
                String rutConf = rut + "true";
                this.confirmaciones.set(i,rutConf);
            }
        }
    }


    public void AgregarEstExt(List<Integer> estrellasList){
        for (int i = 0; i < estrellasList.size(); i++) {
            this.estrellas.add(estrellasList.get(i));
        }
    }


    public void AgregarComExt(List<String> comentariosList){
        for (int i = 0; i < comentariosList.size(); i++) {
            this.comentarios.add(comentariosList.get(i));
        }
    }
}
