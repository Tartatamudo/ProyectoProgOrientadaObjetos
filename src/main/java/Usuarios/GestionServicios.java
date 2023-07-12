package Usuarios;

import java.util.ArrayList;
import java.util.Locale;

public class GestionServicios {

    public  GestionServicios(){
    }

    public String DevolverStrServiciosCompra(ArrayList<Usuario> compradores){
        String texto = "";
        for (int i = 0; i <compradores.size() ; i++) {
            if(compradores.get(i).GetLargoServicios() != 0){
                texto = texto + "Comprador " + String.valueOf(i) + ". "+ compradores.get(i).DevolverStrServicos() +";";

            }
        }
        return texto;
    }

    public String DevolverStrServiciosVenta(ArrayList<Vendedor> vendedores){
        String texto = "";
        for (int i = 0; i <vendedores.size() ; i++) {
            if(vendedores.get(i).GetLargoServicios() != 0){
                texto = texto+ "Vendedor " + String.valueOf(i) + ". " + vendedores.get(i).DevolverStrServicos() +";";
            }
        }
        return texto;
    }

    public String DevolverStrEleccionVenta(String strEleccion, ArrayList<Vendedor> vendedores) {
        strEleccion = strEleccion.toLowerCase(Locale.ROOT);
        String texto = "";
        ArrayList<Servicio> servicios;

        for (int i = 0; i < vendedores.size(); i++) {
            servicios = vendedores.get(i).GetServicios();

            for (Servicio servicio : servicios) {
                String strNomServ = servicio.GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    texto = texto +"Vendedor " + i + ": ";
                    texto = texto + servicio.GetNombre() + ": ";
                    texto = texto + servicio.GetDescripcion() + ";";
                }
            }
        }
        return texto;
    }

    public String DevolverStrEleccionCompra(String strEleccion, ArrayList<Usuario> compradores) {
        strEleccion = strEleccion.toLowerCase(Locale.ROOT);
        String texto = "";
        ArrayList<Servicio> servicios;

        for (int i = 0; i < compradores.size(); i++) {
            servicios = compradores.get(i).GetServicios();

            for (Servicio servicio: servicios) {
                String strNomServ = servicio.GetNombre().toLowerCase(Locale.ROOT);
                if ( strEleccion.equals(strNomServ)){
                    texto = texto +"Comprador " + i + ": ";
                    texto = texto + servicio.GetNombre() + ": ";
                    texto = texto + servicio.GetDescripcion() + ";";
                }
            }
        }
        return texto;
    }

    public void AgregarServicioComprador(Servicio servicio, Usuario comprador){
        comprador.CrearPublicacion(servicio);

    }

    public void AgregarServicioVendedor(Servicio servicio, Vendedor vendedor){
        vendedor.CrearPublicacion(servicio);
    }
    public ArrayList<Vendedor> DevolverVendedoresConPublicaciones(ArrayList<Vendedor> vendedores){
        ArrayList<Vendedor> vendedoresConf = new ArrayList<>();
        for (Vendedor vendedor: vendedores) {
            if( vendedor.GetLargoServicios() != 0){
                vendedoresConf.add(vendedor);
            }
        }return vendedoresConf;
    }

    public ArrayList<Usuario> DevolverCompradoresConPublicaciones(ArrayList<Usuario> compradores){
        ArrayList<Usuario> compradoresConf = new ArrayList<>();
        for (Usuario comprador: compradores) {
            if( comprador.GetLargoServicios() != 0){
                compradoresConf.add(comprador);
            }
        }return compradoresConf;
    }

    public ArrayList<Vendedor> DevolverVendedoresFiltro(ArrayList<Vendedor> vendedores, String filtro){
        ArrayList<Vendedor> vendedoresConf = new ArrayList<>();

        for (Vendedor vendedor: vendedores) {
            ArrayList<Servicio> servicios = vendedor.GetServicios();
            for(Servicio servicio: servicios){
                if (servicio.GetNombre().equals(filtro)){
                    vendedoresConf.add(vendedor);
                }
            }
        }
        return vendedoresConf;
    }

    public ArrayList<Usuario> DevolverCompradoresFiltro(ArrayList<Usuario> compradores, String filtro){
        ArrayList<Usuario> compradoresConf = new ArrayList<>();

        for (Usuario comprador: compradores) {
            ArrayList<Servicio> servicios = comprador.GetServicios();
            for(Servicio servicio: servicios){
                if (servicio.GetNombre().equals(filtro)){
                    compradoresConf.add(comprador);
                }
            }
        }
        return compradoresConf;
    }
}
