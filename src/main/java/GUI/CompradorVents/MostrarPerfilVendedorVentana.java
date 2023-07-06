package GUI.CompradorVents;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostrarPerfilVendedorVentana extends JFrame implements ActionListener {
    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private Vendedor vendedor;
    private JLabel nombreLabel;
    private JList comentariosList;
    private JList estrellasList;
    private JLabel correoLabel;
    private JLabel numeroLabel;
    private JPanel ventana;
    private JScrollPane scrollComentarios;
    private JScrollPane scrollEstrellas;
    private JButton btnVolver;

    public MostrarPerfilVendedorVentana(ArrayList<ArrayList> usuarios, Vendedor vendedor, Usuario comprador) {
        this.vendedor = vendedor;
        this.usuarios = usuarios;
        this.comprador = comprador;
    }
    public void SetDatosBasicos(){
        String nombre = "Nombre: " + vendedor.GetNombre() + " " + vendedor.GetApellido();
        nombreLabel.setText(nombre);

        correoLabel.setText("Correo: " + vendedor.GetCorreo());
        numeroLabel.setText("Numero telefonico: +56 9 " + vendedor.GetNumero());
    }
    public void SetComentarios(){
        ArrayList<String> comentarios = vendedor.GetComentarios();

        comentariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < comentarios.size(); i++) {
            modelo.addElement(comentarios.get(i));
        }

        comentariosList.setModel(modelo);
    }
    public void SetEstrrellas(){
        List<String> ListTexto = Arrays.asList(vendedor.DevolverStrEstrellas().split(";"));

        estrellasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }
        estrellasList.setModel(modelo);
    }
    public void Pantalla(){

        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        SetComentarios();
        SetEstrrellas();
        scrollComentarios.setViewportView(comentariosList);
        scrollEstrellas.setViewportView(estrellasList);

        SetDatosBasicos();

        setContentPane(ventana);

        setVisible(true);

        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver){
            MostrarServiciosDeVendedoresVentana mostrarServiciosDeVendedoresVentana = new MostrarServiciosDeVendedoresVentana(usuarios, comprador);
            mostrarServiciosDeVendedoresVentana.Pantalla();
            setVisible(false);
        }
    }
}
