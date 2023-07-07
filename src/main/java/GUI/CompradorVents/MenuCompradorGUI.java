package GUI.CompradorVents;

import GUI.CrearPublicacionGUI;
import GUI.LoginGUI;
import GUI.VerPublicacionesGUI;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuCompradorGUI extends JFrame implements ActionListener {


    private Usuario comprador;//Este es el usuario que se logueo con antelacion, estara en todas las ventanas
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    private JPanel ventanaPNL;
    private JButton mostrarServiciosBTN;
    private JButton crearPublicacionBTN;
    private JButton verPublicacionesBTN;
    private JButton evaluarBTN;
    private JButton cerrarBTN;
    private JLabel icon;

    public MenuCompradorGUI(ArrayList<ArrayList> usuarios, Usuario comprador) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
        this.comprador = comprador;
    }

    public void Pantalla() {
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(ventanaPNL);

        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();

        mostrarServiciosBTN.addActionListener(this);
        crearPublicacionBTN.addActionListener(this);
        verPublicacionesBTN.addActionListener(this);
        evaluarBTN.addActionListener(this);
        cerrarBTN.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarServiciosBTN) {
            MostrarServiciosVendedoresGUI mostrarServiciosDeVendedoresVentana = new MostrarServiciosVendedoresGUI(usuarios, comprador);
            mostrarServiciosDeVendedoresVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == crearPublicacionBTN){
            CrearPublicacionGUI crearPublicacionVentana = new CrearPublicacionGUI(comprador, usuarios);
            crearPublicacionVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == verPublicacionesBTN){
            VerPublicacionesGUI verMisPublicVentana = new VerPublicacionesGUI(comprador, usuarios);
            verMisPublicVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == evaluarBTN){
            EvaluarVendedorGUI evaluarVendedorVentana = new EvaluarVendedorGUI(comprador, usuarios);
            evaluarVendedorVentana.Pantalla();
            setVisible(false);
        } else if (e.getSource() == cerrarBTN) {
            LoginGUI loginVentana = new LoginGUI();
            loginVentana.Pantalla();
            setVisible(false);
        }
    }
}
