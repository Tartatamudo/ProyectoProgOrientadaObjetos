package GUI;

import GUI.CompradorVents.LogueadoCompradorVentana;
import GUI.VendedorVents.LogueadoVendedorVentana;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerMisPublicVentana extends JFrame implements ActionListener {
    private ArrayList<ArrayList> usuarios;
    private Usuario comprador;
    private Vendedor vendedor;
    private JList publicacionesList;
    private JScrollPane scrollPublicaciones;
    private JButton btnVolver;
    private JPanel ventana;
    private JLabel lblNombre;
    private JLabel lblServicios;

    public VerMisPublicVentana(Usuario comprador, ArrayList<ArrayList> usuarios){
        this.comprador = comprador;
        this.usuarios = usuarios;
    }
    public VerMisPublicVentana(ArrayList<ArrayList> usuarios, Vendedor vendedor){
        this.vendedor = vendedor;
        this.usuarios = usuarios;
    }
    public void LLenarLista(){
        List<String> ListTexto = new ArrayList<>();
        if ( comprador != null){
            ListTexto = Arrays.asList(comprador.DevolverStrServicos().split(","));
        }else if ( vendedor != null){
            ListTexto = Arrays.asList(vendedor.DevolverStrServicos().split(","));
        }

        publicacionesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        publicacionesList.setModel(modelo);
    }
    public void LLenarBasico(){
        if (comprador != null){
            lblNombre.setText("Nombre: " + comprador.GetNombre() + " " + comprador.GetApellido());
        }else if ( vendedor != null){
            lblNombre.setText("Nombre: " + vendedor.GetNombre() + " " + vendedor.GetApellido());
        }
    }
    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(ventana);

        LLenarBasico();
        LLenarLista();
        scrollPublicaciones.setViewportView(publicacionesList);
        btnVolver.addActionListener(this);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btnVolver){
            if (comprador!= null) {
                LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
                logueadoCompradorVentana.Pantalla();
                setVisible(false);
            } else if (vendedor != null) {
                LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }

        }
    }
}
