package GUI.VendedorVents;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MostrarPerfilCompradorGUI extends JFrame implements ActionListener {

    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private Vendedor vendedor;
    private JPanel ventanaPNL;
    private JButton volverBTN;
    private JLabel iconLBL;
    private JLabel nombreLBL;
    private JLabel correoLBL;
    private JLabel numeroLBL;

    public MostrarPerfilCompradorGUI(ArrayList<ArrayList> usuarios, Usuario comprador, Vendedor vendedor) {
        this.comprador = comprador;
        this.usuarios = usuarios;
        this.vendedor = vendedor;
    }
    public void SetDatosBasicos(){
        String nombre = "Nombre: " + comprador.GetNombre() + " " + comprador.GetApellido();
        nombreLBL.setText(nombre);

        correoLBL.setText("Correo: " + comprador.GetCorreo());
        numeroLBL.setText("Numero telefonico: +56 9 " + comprador.GetNumero());
    }

    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        SetDatosBasicos();

        setContentPane(ventanaPNL);
        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();


        setVisible(true);

        volverBTN.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volverBTN){
            MostrarServicioCompradoresGUI mostrarServiciosDeCompradoresVentana = new MostrarServicioCompradoresGUI(usuarios, vendedor);
            mostrarServiciosDeCompradoresVentana.Pantalla();
            setVisible(false);
        }
    }
}


