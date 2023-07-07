package GUI;

import GUI.CompradorVents.MenuCompradorGUI;
import GUI.VendedorVents.LogueadoVendedorVentana;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerPublicacionesGUI extends JFrame implements ActionListener {

    private ArrayList<ArrayList> usuarios;
    private Usuario comprador;
    private Vendedor vendedor;

    private JPanel ventanaPNL;
    private JPanel todoPNL;
    private JLabel iconLBL;
    private JLabel nombreLBL;
    private JList publicacionesLIST;
    private JButton volverBTN;
    private JScrollPane publicacionesSCROLL;

    public VerPublicacionesGUI(Usuario comprador, ArrayList<ArrayList> usuarios){
        this.comprador = comprador;
        this.usuarios = usuarios;
    }
    public VerPublicacionesGUI(ArrayList<ArrayList> usuarios, Vendedor vendedor){
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

        publicacionesLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < ListTexto.size(); i++) {
            modelo.addElement(ListTexto.get(i));
        }

        publicacionesLIST.setModel(modelo);
    }
    public void LLenarBasico(){
        if (comprador != null){
            nombreLBL.setText("Nombre: " + comprador.GetNombre() + " " + comprador.GetApellido());
        }else if ( vendedor != null){
            nombreLBL.setText("Nombre: " + vendedor.GetNombre() + " " + vendedor.GetApellido());
        }
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


        LLenarBasico();
        LLenarLista();
        publicacionesSCROLL.setViewportView(publicacionesLIST);
        volverBTN.addActionListener(this);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == volverBTN){
            if (comprador!= null) {
                MenuCompradorGUI logueadoCompradorVentana = new MenuCompradorGUI(usuarios, comprador);
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
