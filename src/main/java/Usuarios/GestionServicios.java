package Usuarios;

import java.util.ArrayList;
import java.util.Locale;

public class GestionServicios {
    //Imprimir servicios que se buscan

    //Ventana
    public static String DevolverStrServiciosCompra(ArrayList<Usuario> compradores){
        String texto = "";
        for (int i = 0; i <compradores.size() ; i++) {
            if(compradores.get(i).GetLargoServicios() != 0){
                texto = texto+ "Comprador " + String.valueOf(i) + ". "+ compradores.get(i).DevolverStrServicos() +";";

            }
        }
        return texto;
    }
    //Ventana
    public static String DevolverStrServiciosVenta(ArrayList<Vendedor> vendedores){
        String texto = "";
        for (int i = 0; i <vendedores.size() ; i++) {
            if(vendedores.get(i).GetLargoServicios() != 0){
                texto = texto+ "Vendedor " + String.valueOf(i) + ". " + vendedores.get(i).DevolverStrServicos() +";";
            }
        }
        return texto;
    }

    //Impresion de los servicios propios


    //Crear publicacion


    //Filtrar servicios

    //Ventana
    public static String DevolverStrEleccionVenta(String strEleccion, ArrayList<Vendedor> vendedores) {
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

    //Ventana
    public static String DevolverStrEleccionCompra(String strEleccion, ArrayList<Usuario> compradores) {
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

}
