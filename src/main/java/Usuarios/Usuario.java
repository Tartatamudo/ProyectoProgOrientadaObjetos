package Usuarios;

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

    public String GetNombre() {
        return this.nombre;
    }

    public String GetApellido() {
        return this.apellido;
    }

    public  String GetCorreo(){
        return this.correo;
    }

    public String GetRut(){
        return this.rut;
    }

    public String GetContraseña(){
        return this.contraseña;
    }

    public int GetNumero(){
        return this.numero;
    }

    public void CrearPublicacion() {
        Servicio servicio = new Servicio();

        System.out.println("Escriba el nombre");
        servicio.SetNombre();

        System.out.println("Escriba la descripcion");
        servicio.SetDescripcion();

        this.servicios.add(servicio);
    }
    public ArrayList<Servicio> GetServicios(){
        return this.servicios;
    }
    public void ImprimirServicios() {
        for (int i = 0; i < this.servicios.size(); i++) {
            this.servicios.get(i).GetServicio();
        }
    }

    public void ImprimirDatos() {
        System.out.println(GetNombre() + " " + this.apellido + ":");
        System.out.println("    correo: " + this.correo);
        System.out.println("    numero: +569 " + this.numero);

    }
    public int GetLargoServicios(){
        return this.servicios.size();
    }

    public ArrayList<ArrayList> GetListaDeListaServicios() {
        ArrayList<ArrayList> lista = new ArrayList<>();
        for (int i = 0; i < this.servicios.size(); i++) {
            lista.add(this.servicios.get(i).GetLista());
        }
        return lista;
    }

    public void AgregarExtServ(ArrayList<Servicio> serv) {
        for (int i = 0; i < serv.size(); i++) {
            this.servicios.add(serv.get(i));
        }
    }
    public void AgregarConfirmacion(String rut){
        this.confirmaciones.remove("");
        boolean bool = false;
        for (int i = 0; i < this.confirmaciones.size(); i++) {
            if(this.confirmaciones.get(i).equals(rut)){
                bool = true;
            }
        }
        if (bool == false) {
            this.confirmaciones.add(rut);
        }
    }
    public void RemoverConfirmacion(String rutConf){
        this.confirmaciones.remove(rutConf);
    }
    public ArrayList<String> GetConfirmaciones(){
        return this.confirmaciones;
    }
    public void AgregarConfExt(List<String> confirmacionesList){
        for (int i = 0; i < confirmacionesList.size(); i++) {
            this.confirmaciones.add(confirmacionesList.get(i));
        }
    }
}
