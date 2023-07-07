package GUI.VendedorVents;

import Usuarios.GestionServicios;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostrarServicioCompradoresGUI extends JFrame implements ActionListener {

    private Vendedor vendedor;
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Usuario> compradores;
    private ArrayList<Usuario> compradoresConServicios;
    private JPanel superiorPNL;
    private JScrollPane compradoresSCROLL;
    private JList compradoresLIST;
    private JLabel iconLBL;
    private JComboBox filtroCBOX;
    private JButton filtroBTN;
    private JPanel inferiorPNL;
    private JComboBox compradorCBOX;
    private JButton buscarCompradorBTN;
    private JButton volverBTN;
    private JLabel compradorLBL;
    private JPanel ventanaPNL;

    public MostrarServicioCompradoresGUI(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        this.usuarios = usuarios;
        this.compradores = usuarios.get(0);
        this.vendedor = vendedor;
    }
    public void AñadirLista(){
        GestionServicios gestionServicios = new GestionServicios(usuarios);

        this.compradoresConServicios = gestionServicios.DevolverCompradoresConPublicaciones(compradores);
        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrServiciosCompra(compradoresConServicios).split(";"));

        SetComboCompradores(compradoresConServicios.size());

        compradoresLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        compradoresLIST.setModel(modelo);
    }
    private void SetComboCompradores(int num){
        compradorCBOX.removeAllItems();
        for (int i = 0; i < num; i++) {
            compradorCBOX.addItem(i);
        }
    }
    private void SetComboFiltro(){
        filtroCBOX.addItem("No filtro");
        filtroCBOX.addItem("gasfiter");
        filtroCBOX.addItem("electricista");
    }
    private void FiltrarLista(){
        GestionServicios gestionServicios = new GestionServicios(usuarios);

        String eleccion =(String) filtroCBOX.getSelectedItem();

        compradoresConServicios = gestionServicios.DevolverCompradoresFiltro(compradoresConServicios, eleccion);
        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrEleccionCompra(eleccion, compradoresConServicios).split(";"));
        SetComboCompradores(compradoresConServicios.size());

        compradoresLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));

        }

        compradoresLIST.setModel(modelo);
    }
    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        compradoresSCROLL.setViewportView(compradoresLIST);

        AñadirLista();
        SetComboFiltro();

        setContentPane(ventanaPNL);
        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();


        setVisible(true);

        buscarCompradorBTN.addActionListener(this);
        filtroBTN.addActionListener(this);
        volverBTN.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buscarCompradorBTN){
            int numero = (Integer) compradorCBOX.getSelectedItem();
            Usuario comprador = compradoresConServicios.get(numero);
            MostrarPerfilCompradorGUI mostrarPerfilCompradorVentana = new MostrarPerfilCompradorGUI(usuarios, comprador, vendedor);

            mostrarPerfilCompradorVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == filtroBTN){
            compradoresLIST.removeAll();

            String cboxEleccion = (String) filtroCBOX.getSelectedItem();

            if(cboxEleccion.equals("No filtro")){
                AñadirLista();
            }else {
                FiltrarLista();
            }
            compradoresSCROLL.revalidate();
            compradoresSCROLL.repaint();

        } else if (e.getSource() == volverBTN){
            MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
    }

}
