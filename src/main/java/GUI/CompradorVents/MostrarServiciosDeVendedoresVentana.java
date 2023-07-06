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

public class MostrarServiciosDeVendedoresVentana extends JFrame implements ActionListener {
    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Vendedor> vendedores;
    private JList vendedoresList;
    private JPanel ventana;
    private JScrollPane scrollVendedores;
    private JButton btnPerfilVendedor;
    private JComboBox cBoxVendedor;
    private JButton btnVolver;
    private JComboBox cBoxFiltro;
    private JButton btnFiltrar;

    public MostrarServiciosDeVendedoresVentana(ArrayList<ArrayList> usuarios, Usuario comprador) {
        this.usuarios = usuarios;
        this.vendedores = usuarios.get(1);
        this.comprador = comprador;
    }
    public void AñadirLista(){
        GestionServicios gestionServicios = new GestionServicios(usuarios);

        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrServiciosVenta(vendedores).split(";"));

        vendedoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        vendedoresList.setModel(modelo);
    }
    private void SetComboVendedores(){
        for (int i = 0; i < vendedores.size(); i++) {
            cBoxVendedor.addItem(i);
        }
    }
    private void SetComboFiltro(){
        cBoxFiltro.addItem("No filtro");
        cBoxFiltro.addItem("gasfiter");
        cBoxFiltro.addItem("electricista");
    }
    private void FiltrarLista(){
        GestionServicios gestionServicios = new GestionServicios(usuarios);

        String eleccion =(String) cBoxFiltro.getSelectedItem();
        List<String> ListTexto = Arrays.asList(gestionServicios.DevolverStrEleccionVenta(eleccion, vendedores).split(";"));

        vendedoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));

        }

        vendedoresList.setModel(modelo);
    }
    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        scrollVendedores.setViewportView(vendedoresList);

        AñadirLista();
        SetComboFiltro();
        SetComboVendedores();
        setContentPane(ventana);

        setVisible(true);

        btnPerfilVendedor.addActionListener(this);
        btnFiltrar.addActionListener(this);
        btnVolver.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPerfilVendedor){
            int numero = (Integer) cBoxVendedor.getSelectedItem();
            Vendedor vendedor = vendedores.get(numero);
            MostrarPerfilVendedorVentana mostrarPerfilVendedorVentana = new MostrarPerfilVendedorVentana(usuarios, vendedor, comprador);
            vendedor.AgregarConfirmacion(comprador.GetRut());
            mostrarPerfilVendedorVentana.Pantalla();
            setVisible(false);

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ActualizarVendedores();
        }else if (e.getSource() == btnFiltrar){
            vendedoresList.removeAll();

            String cboxEleccion = (String) cBoxFiltro.getSelectedItem();

            if(cboxEleccion.equals("No filtro")){
                AñadirLista();
            }else {
                FiltrarLista();
            }

            scrollVendedores.revalidate();
            scrollVendedores.repaint();

        } else if (e.getSource() == btnVolver){
            LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }

    }
}
