package GUI.VendedorVents;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MostrarPerfilCompradorVentana extends JFrame implements ActionListener {

    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private Vendedor vendedor;

    private JButton btnVolver;
    private JLabel lblNombre;
    private JLabel lblCorreo;
    private JLabel lblNumero;
    private JPanel ventana;

    public MostrarPerfilCompradorVentana(ArrayList<ArrayList> usuarios, Usuario comprador, Vendedor vendedor) {
        this.comprador = comprador;
        this.usuarios = usuarios;
        this.vendedor = vendedor;
    }
    public void SetDatosBasicos(){
        String nombre = "Nombre: " + comprador.GetNombre() + " " + comprador.GetApellido();
        lblNombre.setText(nombre);

        lblCorreo.setText("Correo: " + comprador.GetCorreo());
        lblNumero.setText("Numero telefonico: +56 9 " + comprador.GetNumero());
    }

    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        SetDatosBasicos();

        setContentPane(ventana);

        setVisible(true);

        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver){
            MostrarServiciosDeCompradoresVentana mostrarServiciosDeCompradoresVentana = new MostrarServiciosDeCompradoresVentana(usuarios, vendedor);
            mostrarServiciosDeCompradoresVentana.Pantalla();
            setVisible(false);
        }
    }
}
