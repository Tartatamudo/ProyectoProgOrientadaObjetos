package Usuarioss;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correo;
    private String rut;

    protected int numero;
    private String contraseña;

    protected ArrayList<String> confirmaciones = new ArrayList<>();

    private ArrayList<Servicio> servicios = new ArrayList<Servicio>();

    public Usuario(String nombre, String apellido, String correo, String rut, int numero, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.rut = rut;
        this.contraseña = contraseña;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public  String getCorreo(){
        return correo;
    }

        public String getRut(){
        return rut;
    }

    public String getContraseña(){
        return contraseña;
    }

    public int getNumero(){
        return numero;
    }

    public void crearPublicacion() {
        Servicio servicio = new Servicio();

        System.out.println("Escriba el nombre");
        servicio.setNombre();

        System.out.println("Escriba la descripcion");
        servicio.setDescripcion();

        servicios.add(servicio);
    }
    public ArrayList<Servicio> getServicios(){
        return servicios;
    }
    public void ImprimirServicios() {
        for (int i = 0; i < servicios.size(); i++) {
            servicios.get(i).getServicio();
        }
    }

    public void getDatos() {
        System.out.println(getNombre() + " " + apellido + ":");
        System.out.println("    correo: " + correo);
        System.out.println("    numero: +569 " + numero);

    }
    public int GetLargoServicios(){
        return servicios.size();
    }

    public ArrayList<ArrayList> getListaDeListaServicios() {
        ArrayList<ArrayList> lista = new ArrayList<>();
        for (int i = 0; i < servicios.size(); i++) {
            lista.add(servicios.get(i).getLista());
        }
        return lista;
    }

    public void agregarExtServ(ArrayList<Servicio> serv) {
        for (int i = 0; i < serv.size(); i++) {
            servicios.add(serv.get(i));
        }
    }
    public void AgregarConfirmacion(String rut){
        confirmaciones.remove("");
        boolean bool = false;
        for (int i = 0; i < confirmaciones.size(); i++) {
            if(confirmaciones.get(i).equals(rut)){
                bool = true;
            }
        }
        if (bool == false) {
            confirmaciones.add(rut);
        }
    }
    public void RemoverConfirmacion(String rutConf){
        confirmaciones.remove(rutConf);
    }
    public ArrayList<String> GetConfirmaciones(){
        return confirmaciones;
    }
    public void AgregarConfExt(List<String> confirmacionesList){
        for (int i = 0; i < confirmacionesList.size(); i++) {
            confirmaciones.add(confirmacionesList.get(i));
        }
    }
}
