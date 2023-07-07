package Datos;

import Usuarios.Servicio;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import Utilidades.Validadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorArchivos {
    private GestionArchivos gestionArchivos = new GestionArchivos();
    public GestorArchivos(){
    }
    public void VerificarArchivos(){
        Validadores validadores = new Validadores();

        if(!validadores.ValidarArchivos("compradores.csv")){
            this.gestionArchivos.CrearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;confirmaciones;servicios;");
        }
        if(!validadores.ValidarArchivos(("vendedores.csv"))){
            this.gestionArchivos.CrearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;estrellas;comentarios;confirmaciones;servicios;");
        }
    }
    //------------------Archivo compradores------------------------------------------------------------
    public void AñadirCompradoresArchivo(ArrayList<Usuario> compradores){
        //Reinicia archivo y mete todoo lo que tiene el programa
        this.gestionArchivos.CrearArchivo("Compradores.csv", "nombre;apellido;correo;rut;numero;contrasena;confirmaciones;servicios;");
        for (int i = 0; i < compradores.size(); i++) {
            Usuario comprador = compradores.get(i);
            AñadirCompradorArchivo(comprador);
        }
    }
    public void AñadirCompradorArchivo(Usuario comprador){
        String nombre = comprador.GetNombre();
        String apellido = comprador.GetApellido();
        String correo = comprador.GetCorreo();
        String rut = comprador.GetRut();
        int numero = comprador.GetNumero();
        String contraseña = comprador.GetContraseña();
        ArrayList<String> confirmaciones = comprador.GetConfirmaciones();
        ArrayList<ArrayList> serv = comprador.GetListaDeListaServicios();

        this.gestionArchivos.NuevaLinea("Compradores.csv",nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + confirmaciones + ";" + serv + ";");
    }
    public void CargarCompradoresAPrograma(ArrayList<Usuario> compradores){
        String strCompradores =  this.gestionArchivos.LeerArchivo("Compradores.csv");

        //Quita las lineas
        strCompradores = strCompradores.replaceAll("\\n", "");

        //Quito los datos innecesarios, que son en el archivo las "categorias" ejemplo: nombre
        ArrayList<String> compraIdoneo = CrearArrayIdoneoCompradores(strCompradores);


        AñadirAListaCompradores(compradores, compraIdoneo);

    }
    private void AñadirAListaCompradores(ArrayList<Usuario> compradores, ArrayList<String> compraIdoneo){

        for (int i = 0; i < compraIdoneo.size() - 1; i += 8) {
            String nombre = compraIdoneo.get(i);
            String apellido = compraIdoneo.get(i+1);
            String correo = compraIdoneo.get(i+2);
            String rut = compraIdoneo.get(i+3);
            int numero = Integer.parseInt(compraIdoneo.get(i+4));
            String contraseña = compraIdoneo.get(i+5);

            Usuario comprador = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

            //A partir de aqui se agregan los atributos que son arrays y que no estan en el constructor
            String strConfirmaciones = compraIdoneo.get(i+6);
            AgregarConfirmacionesComprador(strConfirmaciones, comprador);

            String strObjetos = compraIdoneo.get(i+7);
            AgregarServiciosComprador(strObjetos, comprador);

            compradores.add(comprador);
        }
    }
    private void AgregarConfirmacionesComprador(String strConfirmaciones, Usuario comprador){
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionesList = Arrays.asList(strConfirmaciones.split(","));

        comprador.AgregarConfExt(confirmacionesList);
    }
    private static void AgregarServiciosComprador(String strObjetos, Usuario comprador){

        strObjetos = strObjetos.replaceAll("\\[", "");
        strObjetos = strObjetos.replaceAll("\\]", "");

        List<String> listObjetos = Arrays.asList(strObjetos.split(","));

        ArrayList<Servicio> serv = new ArrayList<>();

        if (strObjetos != "") {
            for (int j = 0; j < listObjetos.size() ; j+= 2) {
                String nombre = listObjetos.get(j);
                String descripcion = listObjetos.get(j+1);

                Servicio servicio = new Servicio(nombre, descripcion);

                serv.add(servicio);
            }
            comprador.AgregarExtServ(serv);
        }
    }
    private ArrayList<String> CrearArrayIdoneoCompradores(String strCompradores){
        List<String> compradoresList = Arrays.asList(strCompradores.split(";"));
        ArrayList<String> compraDefList = new ArrayList<>(compradoresList.subList(8, compradoresList.size()));
        return compraDefList;
    }
    //------------------Archivo vendedores------------------------------------------------------------
    public void AñadirVendedoresArchivo(ArrayList<Vendedor> vendedores) {
        //Reinicia archivo y mete todoo lo que tiene el programa
        this.gestionArchivos.CrearArchivo("vendedores.csv", "nombre;apellido;correo;rut;numero;contrasena;estrellas;comentarios;confirmaciones;servicios;");
        for (int i = 0; i < vendedores.size(); i++) {
            Vendedor vendedor = vendedores.get(i);
            AñadirVendedorArchivo(vendedor);
        }
    }
    public void AñadirVendedorArchivo(Vendedor vendedor) {
        String nombre = vendedor.GetNombre();
        String apellido = vendedor.GetApellido();
        String correo = vendedor.GetCorreo();
        String rut = vendedor.GetRut();
        int numero = vendedor.GetNumero();
        String contraseña = vendedor.GetContraseña();
        ArrayList<Integer> estrellas = vendedor.GetEstrellas();
        ArrayList<String> comentarios = vendedor.GetComentarios();
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        ArrayList<ArrayList> serv = vendedor.GetListaDeListaServicios();

        this.gestionArchivos.NuevaLinea("vendedores.csv", nombre + ";" + apellido + ";" + correo + ";" + rut + ";" + numero + ";" + contraseña + ";" + estrellas + ";" + comentarios + ";" + confirmaciones + ";" + serv + ";");
    }
    public void CargarVendedoresAPrograma(ArrayList<Vendedor> vendedores) {
        String strVendedores = this.gestionArchivos.LeerArchivo("vendedores.csv");
        strVendedores = strVendedores.replaceAll("\\n", "");
        ArrayList<String> vendeIdoneo = CrearArrayIdoneoVendedores(strVendedores);
        AñadirAListaVendedores(vendedores, vendeIdoneo);
    }
    private void AñadirAListaVendedores(ArrayList<Vendedor> vendedores, ArrayList<String> vendeIdoneo) {
        for (int i = 0; i < vendeIdoneo.size() - 1; i += 10) {
            String nombre = vendeIdoneo.get(i);
            String apellido = vendeIdoneo.get(i + 1);
            String correo = vendeIdoneo.get(i + 2);
            String rut = vendeIdoneo.get(i + 3);
            int numero = Integer.parseInt(vendeIdoneo.get(i + 4));
            String contraseña = vendeIdoneo.get(i + 5);

            Vendedor vendedor = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

            String strEstrellas = vendeIdoneo.get(i + 6);
            AgregarEstrellasVendedor(strEstrellas, vendedor);

            String strComentarios = vendeIdoneo.get(i + 7);
            AgregarComenentariosVendedor(strComentarios, vendedor);

            String strConfirmaciones = vendeIdoneo.get(i + 8);
            AgregarConfirmacionesVendedor(strConfirmaciones, vendedor);

            String strObjetos = vendeIdoneo.get(i + 9);
            AgregarServiciosVendedor(strObjetos, vendedor);

            vendedores.add(vendedor);
        }
    }
    private void AgregarConfirmacionesVendedor(String strConfirmaciones, Vendedor vendedor) {
        strConfirmaciones = strConfirmaciones.replaceAll("\\[", "");
        strConfirmaciones = strConfirmaciones.replaceAll("\\]", "");
        strConfirmaciones = strConfirmaciones.replaceAll(" ", "");

        List<String> confirmacionesList = Arrays.asList(strConfirmaciones.split(","));

        vendedor.AgregarConfExt(confirmacionesList);
    }
    private void AgregarComenentariosVendedor(String strComentarios, Vendedor vendedor) {
        strComentarios = strComentarios.replaceAll("\\[", "");
        strComentarios = strComentarios.replaceAll("\\]", "");

        List<String> comentariosList = Arrays.asList(strComentarios.split(","));

        vendedor.AgregarComExt(comentariosList);
    }
    private void AgregarEstrellasVendedor(String strEstrellas, Vendedor vendedor) {
        strEstrellas = strEstrellas.replaceAll("\\[", "");
        strEstrellas = strEstrellas.replaceAll("\\]", "");
        strEstrellas = strEstrellas.replaceAll(" ", "");

        List<String> estrellasList = Arrays.asList(strEstrellas.split(","));

        ArrayList<Integer> estrellasListInt = new ArrayList<>();
        for (String estrella : estrellasList) {
            if (!estrella.isEmpty()) {
                estrellasListInt.add(Integer.parseInt(estrella));
            }
        }
        vendedor.AgregarEstExt(estrellasListInt);
    }
    private void AgregarServiciosVendedor(String strObjetos, Vendedor ven) {
        strObjetos = strObjetos.replaceAll("\\[", "");
        strObjetos = strObjetos.replaceAll("\\]", "");


        List<String> listObjetos = Arrays.asList(strObjetos.split(","));

        ArrayList<Servicio> serv = new ArrayList<>();

        if (!strObjetos.isEmpty()) {
            for (int j = 0; j < listObjetos.size(); j += 2) {
                String nombre = listObjetos.get(j);
                String descripcion = listObjetos.get(j + 1);

                Servicio servicio = new Servicio(nombre, descripcion);

                serv.add(servicio);
            }
            ven.AgregarExtServ(serv);
        }
    }
    private ArrayList<String> CrearArrayIdoneoVendedores(String strVendedores) {
        List<String> vendedoresList = Arrays.asList(strVendedores.split(";"));
        ArrayList<String> vendeDefList = new ArrayList<>(vendedoresList.subList(10, vendedoresList.size()));
        return vendeDefList;
    }
}
