package GUIS.VendedorVents;

import GUIS.CrearPublicacionGUI;
import GUIS.LoginGUI;
import GUIS.VerPublicacionesGUI;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuVendedorGUI extends JFrame implements ActionListener {
    private Vendedor vendedor;//Este es el usuario que se logueo con antelacion, estara en todas las ventanas
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;
    private JPanel ventanaPNL;
    private JButton mostrarServiciosBTN;
    private JButton crearPublicacionBTN;
    private JButton verPublicacionesBTN;
    private JButton confirmarBTN;
    private JButton cerrarBTN;
    private JLabel icon;

    public MenuVendedorGUI(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
        this.vendedor = vendedor;
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
        confirmarBTN.addActionListener(this);
        cerrarBTN.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarServiciosBTN) {
            MostrarServicioCompradoresGUI mostrarServiciosDeCompradoresVentana = new MostrarServicioCompradoresGUI(usuarios, vendedor);
            mostrarServiciosDeCompradoresVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == crearPublicacionBTN){
            CrearPublicacionGUI crearPublicacionVentana = new CrearPublicacionGUI(usuarios, vendedor);
            crearPublicacionVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == verPublicacionesBTN){
            VerPublicacionesGUI verMisPublicVentana = new VerPublicacionesGUI(usuarios, vendedor);
            verMisPublicVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == confirmarBTN){
            ConfirmarEncuentroGUI confirmarEncuentroVentana = new ConfirmarEncuentroGUI(vendedor, usuarios);
            confirmarEncuentroVentana.Pantalla();
            setVisible(false);
        } else if (e.getSource() == cerrarBTN) {
            LoginGUI loginVentana = new LoginGUI();
            loginVentana.Pantalla();
            setVisible(false);
        }
    }
}
