package Usuarioss;

import java.util.ArrayList;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correo;
    private String rut;
    private String contraseña;
    protected int numero;

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

    public void getServicios() {
        for (int i = 0; i < servicios.size(); i++) {
            servicios.get(i).getServicio();
        }
    }

    public void getDatos() {
        System.out.println(getNombre() + " " + apellido + ":");
        System.out.println("correo: " + correo);
        System.out.println("numero: " + numero);

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
}
