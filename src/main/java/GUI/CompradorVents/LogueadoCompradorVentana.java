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

public class LogueadoCompradorVentana extends JFrame implements ActionListener {

    private Usuario comprador;//Este es el usuario que se logueo con antelacion, estara en todas las ventanas
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    private JPanel ventana;
    private JButton mostrarServicioDeVendedoresButton;
    private JButton crearPublicacionButton;
    private JButton verMisPublicacionesButton;
    private JButton evaluarVendedorButton;
    private JLabel bienvenidoLabel;
    private JButton volverButton;

    public LogueadoCompradorVentana(ArrayList<ArrayList> usuarios, Usuario comprador) {
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
        setContentPane(ventana);

        mostrarServicioDeVendedoresButton.addActionListener(this);
        crearPublicacionButton.addActionListener(this);
        verMisPublicacionesButton.addActionListener(this);
        evaluarVendedorButton.addActionListener(this);
        volverButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarServicioDeVendedoresButton) {
            MostrarServiciosDeVendedoresVentana mostrarServiciosDeVendedoresVentana = new MostrarServiciosDeVendedoresVentana(usuarios, comprador);
            mostrarServiciosDeVendedoresVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == crearPublicacionButton){
            CrearPublicacionGUI crearPublicacionVentana = new CrearPublicacionGUI(comprador, usuarios);
            crearPublicacionVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == verMisPublicacionesButton){
            VerPublicacionesGUI verMisPublicVentana = new VerPublicacionesGUI(comprador, usuarios);
            verMisPublicVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == evaluarVendedorButton){
            EvaluarVendedorVentana evaluarVendedorVentana = new EvaluarVendedorVentana(comprador, usuarios);
            evaluarVendedorVentana.Pantalla();
            setVisible(false);
        } else if (e.getSource() == volverButton) {
            LoginGUI loginVentana = new LoginGUI();
            loginVentana.Pantalla();
            setVisible(false);
        }
    }
}
