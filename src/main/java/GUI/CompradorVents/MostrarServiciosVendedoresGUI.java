package GUI.CompradorVents;

import Usuarios.GestionServicios;
import Usuarios.GestionUsuarios;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostrarServiciosVendedoresGUI extends JFrame implements ActionListener {
    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Vendedor> vendedoresConServicios;
    private ArrayList<Vendedor> vendedores;
    private JPanel ventanaPNL;
    private JPanel inferiorPNL;
    private JPanel superiorPNL;
    private JLabel iconLBL;
    private JComboBox filtroCBOX;
    private JButton filtroBTN;
    private JList vendedoresLIST;
    private JButton volverBTN;
    private JComboBox vendedorCBOX;
    private JButton buscarVendedorBTN;
    private JLabel vendeorLBL;
    private JScrollPane vendedoresSCROLL;

    public MostrarServiciosVendedoresGUI(ArrayList<ArrayList> usuarios, Usuario comprador) {
        this.usuarios = usuarios;
        this.vendedores = usuarios.get(1);
        this.comprador = comprador;
    }
    public void AñadirLista(){
        GestionServicios gestionServicios = new GestionServicios(usuarios);
        this.vendedoresConServicios = gestionServicios.DevolverVendedoresConPublicaciones(vendedores);
        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrServiciosVenta(vendedoresConServicios).split(";"));
        SetComboVendedores(vendedoresConServicios.size());
        vendedoresLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        vendedoresLIST.setModel(modelo);
    }
    private void SetComboVendedores(int num){
        vendedorCBOX.removeAllItems();
        for (int i = 0; i < num; i++) {
            vendedorCBOX.addItem(i);
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

        vendedoresConServicios = gestionServicios.DevolverVendedoresFiltro(vendedoresConServicios, eleccion);
        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrEleccionVenta(eleccion, vendedoresConServicios).split(";"));
        SetComboVendedores(ListTexto.size());

        vendedoresLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));

        }

        vendedoresLIST.setModel(modelo);
    }
    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        vendedoresSCROLL.setViewportView(vendedoresLIST);

        AñadirLista();
        SetComboFiltro();
        setContentPane(ventanaPNL);
        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();


        setVisible(true);

        buscarVendedorBTN.addActionListener(this);
        filtroBTN.addActionListener(this);
        volverBTN.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buscarVendedorBTN){
            int numero = (Integer) vendedorCBOX.getSelectedItem();
            Vendedor vendedor = vendedoresConServicios.get(numero);
            MostrarPerfilVendedorGUI mostrarPerfilVendedorVentana = new MostrarPerfilVendedorGUI(usuarios, vendedor, comprador);
            vendedor.AgregarConfirmacion(comprador.GetRut());
            mostrarPerfilVendedorVentana.Pantalla();
            setVisible(false);

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ActualizarVendedores();
        }else if (e.getSource() == filtroBTN){
            vendedoresLIST.removeAll();

            String cboxEleccion = (String) filtroCBOX.getSelectedItem();

            if(cboxEleccion.equals("No filtro")){
                AñadirLista();
            }else {
                FiltrarLista();
            }

            vendedoresSCROLL.revalidate();
            vendedoresSCROLL.repaint();

        } else if (e.getSource() == volverBTN){
            MenuCompradorGUI logueadoCompradorVentana = new MenuCompradorGUI(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }

    }
}
