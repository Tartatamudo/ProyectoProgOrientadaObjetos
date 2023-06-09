package GUIS;

import GUIS.CompradorVents.MenuCompradorGUI;
import GUIS.VendedorVents.MenuVendedorGUI;
import Usuarios.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrearPublicacionGUI extends JFrame implements ActionListener {
    private JFrame jFrame = new JFrame();
    private ArrayList<ArrayList> usuarios;
    private Usuario comprador;
    private Vendedor vendedor;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;

    private JPanel ventanaPNL;
    private JPanel todoPNL;
    private JLabel iconLBL;
    private JTextField tituloTF;
    private JTextField descripcionTF;
    private JButton crearPublicacionBTN;
    private JButton volverBTN;
    private JLabel tituloLBL;
    private JLabel descripcionLBL;


    public CrearPublicacionGUI(Usuario comprador, ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;
        this.comprador = comprador;
        this.compradores = usuarios.get(0);
    }
    public CrearPublicacionGUI(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        this.usuarios = usuarios;
        this.vendedor = vendedor;
        this.vendedores = usuarios.get(1);
    }

    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(ventanaPNL);

        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();

        crearPublicacionBTN.addActionListener(this);
        volverBTN.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( e.getSource() == crearPublicacionBTN){
            String nombre = tituloTF.getText();
            String descripcion = descripcionTF.getText();
            Servicio servicio = new Servicio(nombre, descripcion);
            if ( comprador != null){
                GestionServicios gestionServicios = new GestionServicios();
                gestionServicios.AgregarServicioComprador(servicio, comprador);

                GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
                gestionUsuarios.ActualizarCompradores();
                JOptionPane.showMessageDialog(jFrame, "publicación COMPRA creada exitosamente");

                MenuCompradorGUI logueadoCompradorVentana = new MenuCompradorGUI(usuarios, comprador);
                logueadoCompradorVentana.Pantalla();
                setVisible(false);

            } else if (vendedor != null) {
                GestionServicios gestionServicios = new GestionServicios();
                gestionServicios.AgregarServicioVendedor(servicio, vendedor);

                GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
                gestionUsuarios.ActualizarVendedores();
                JOptionPane.showMessageDialog(jFrame, "publicacion VENTA creada exitosamente");

                MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }

        }else if(e.getSource() == volverBTN){
            if (comprador != null){
                MenuCompradorGUI logueadoCompradorVentana = new MenuCompradorGUI(usuarios, comprador);
                logueadoCompradorVentana.Pantalla();
                setVisible(false);
            }else if ( vendedor != null){
                MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }
        }

    }
}