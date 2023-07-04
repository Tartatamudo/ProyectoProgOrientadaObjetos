package GUI.VendedorVents;

import GUI.CrearPublicacionVentana;
import GUI.LoginVentana;
import GUI.VerMisPublicVentana;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogueadoVendedorVentana extends JFrame implements ActionListener {

    private Vendedor vendedor;//Este es el usuario que se logueo con antelacion, estara en todas las ventanas
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;
    private JPanel ventana;
    private JButton mostrarServiciosDeCompradoresButton;
    private JButton crearPublicacionButton;
    private JButton verMisPublicacionesButton;
    private JButton confirmarEncuentrosButton;
    private JButton volverButton;

    public LogueadoVendedorVentana(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
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
        setContentPane(ventana);

        mostrarServiciosDeCompradoresButton.addActionListener(this);
        crearPublicacionButton.addActionListener(this);
        verMisPublicacionesButton.addActionListener(this);
        confirmarEncuentrosButton.addActionListener(this);
        volverButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarServiciosDeCompradoresButton) {
            MostrarServiciosDeCompradoresVentana mostrarServiciosDeCompradoresVentana = new MostrarServiciosDeCompradoresVentana(usuarios, vendedor);
            mostrarServiciosDeCompradoresVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == crearPublicacionButton){
            CrearPublicacionVentana crearPublicacionVentana = new CrearPublicacionVentana(usuarios, vendedor);
            crearPublicacionVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == verMisPublicacionesButton){
            VerMisPublicVentana verMisPublicVentana = new VerMisPublicVentana(usuarios, vendedor);
            verMisPublicVentana.Pantalla();
            setVisible(false);
        }else if (e.getSource() == confirmarEncuentrosButton){
            ConfirmarEncuentroVentana confirmarEncuentroVentana = new ConfirmarEncuentroVentana(vendedor, usuarios);
            confirmarEncuentroVentana.Pantalla();
            setVisible(false);
        } else if (e.getSource() == volverButton) {
            LoginVentana loginVentana = new LoginVentana();
            loginVentana.Pantalla();
            setVisible(false);
        }
    }
}
