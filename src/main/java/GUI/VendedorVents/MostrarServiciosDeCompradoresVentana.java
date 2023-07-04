package GUI.VendedorVents;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Usuarios.GestionServicios.*;

public class MostrarServiciosDeCompradoresVentana extends JFrame implements ActionListener {
    private Vendedor vendedor;
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Usuario> compradores;
    private JPanel ventana;
    private JComboBox cBoxFiltro;
    private JButton btnFiltrar;
    private JComboBox cBoxComprador;
    private JButton btnPerfilComprador;
    private JButton btnVolver;
    private JList compradoresList;
    private JScrollPane scrollCompradores;

    public MostrarServiciosDeCompradoresVentana(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        this.usuarios = usuarios;
        this.compradores = usuarios.get(0);
        this.vendedor = vendedor;
    }

    public void AñadirLista(){
        List<String> ListTexto = Arrays.asList(DevolverStrServiciosCompra(compradores).split(";"));

        compradoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        compradoresList.setModel(modelo);
    }
    private void SetComboCompradores(){
        for (int i = 0; i < compradores.size(); i++) {
            cBoxComprador.addItem(i);
        }
    }
    private void SetComboFiltro(){
        cBoxFiltro.addItem("No filtro");
        cBoxFiltro.addItem("gasfiter");
        cBoxFiltro.addItem("electricista");
    }
    private void FiltrarLista(){
        String eleccion =(String) cBoxFiltro.getSelectedItem();
        List<String> ListTexto = Arrays.asList(DevolverStrEleccionCompra(eleccion, compradores).split(";"));

        compradoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));

        }

        compradoresList.setModel(modelo);
    }
    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        scrollCompradores.setViewportView(compradoresList);

        AñadirLista();
        SetComboFiltro();
        SetComboCompradores();
        setContentPane(ventana);

        setVisible(true);

        btnPerfilComprador.addActionListener(this);
        btnFiltrar.addActionListener(this);
        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPerfilComprador){
            int numero = (Integer) cBoxComprador.getSelectedItem();
            Usuario comprador = compradores.get(numero);
           MostrarPerfilCompradorVentana mostrarPerfilCompradorVentana = new MostrarPerfilCompradorVentana(usuarios, comprador, vendedor);

            mostrarPerfilCompradorVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == btnFiltrar){
            compradoresList.removeAll();

            String cboxEleccion = (String) cBoxFiltro.getSelectedItem();

            if(cboxEleccion.equals("No filtro")){
                AñadirLista();
            }else {
                FiltrarLista();
            }
            scrollCompradores.revalidate();
            scrollCompradores.repaint();

        } else if (e.getSource() == btnVolver){
            LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
        AñadirCompradoresArchivo(compradores);
    }
}
