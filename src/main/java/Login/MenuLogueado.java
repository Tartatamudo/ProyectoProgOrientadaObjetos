package Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import java.util.ArrayList;

public class MenuLogueado {
    public MenuLogueado(){
    }

    public String DevolverStrEvaluarVendedor(ArrayList<Vendedor> vendedoresConf, Usuario comprador) {
        String texto = "";

        if (vendedoresConf.size() != 0) {

            String rutConf = comprador.GetRut() + "true";
            for (int i = 0; i < vendedoresConf.size(); i++) {
                if (vendedoresConf.get(i).ValidarConfirmacion(rutConf) == true) {

                    texto = texto + i + ". " + vendedoresConf.get(i).GetNombre() + " " + vendedoresConf.get(i).GetApellido() + ";";
                }
            }
        }
        return texto;
    }

    public ArrayList<Vendedor> VendedoresConfirm(ArrayList<Vendedor> vendedores, Usuario comprador) {

        ArrayList<String> vendedoresStr = comprador.GetConfirmaciones();

        ArrayList<Vendedor> vendedoresConf = new ArrayList<>();
        for (int i = 0; i < vendedoresStr.size(); i++) {
            for (int j = 0; j < vendedores.size(); j++) {
                if (vendedoresStr.get(i).equals(vendedores.get(j).GetRut())) {
                    vendedoresConf.add(vendedores.get(j));
                    vendedores.get(j).GetNombre();
                }
            }
        }
        return vendedoresConf;
    }


    public ArrayList<Usuario> DevolverListaRutAConfirmar(Vendedor vendedor, ArrayList<Usuario> compradores) {
        ArrayList<String> confirmaciones = vendedor.GetConfirmaciones();

        //Aqui se añaden al ArrayList compradoresConf solo los compradores que tienen su rut en el aparto confirmaciones del vendedor, pero solo su rut
        ArrayList<Usuario> compradoresConf = new ArrayList<>();
        for (int i = 0; i < confirmaciones.size(); i++) {
            for (int j = 0; j < compradores.size(); j++) {
                if (confirmaciones.get(i).equals(compradores.get(j).GetRut())) {
                    compradoresConf.add(compradores.get(j));
                }
            }
        }
        return compradoresConf;
    }

    public String DevolverStrCompradoresConfirmados(ArrayList<Usuario> compradoresConf) {
        String texto = "";
        //Aqui se muestran los compradores que tienen su rut y solo su rut en el apartado confirmaciones del vendedor
        //Si no hay compradores a los que confirmarles el contacto se le indica que no tiene
        if (compradoresConf.size() != 0) {
            for (int i = 0; i < compradoresConf.size(); i++) {
                texto = texto + i + ". " + compradoresConf.get(i).GetNombre() + " " + compradoresConf.get(i).GetApellido() + ";";
            }
        }
        return texto;
    }
}