package GUI.CompradorVents;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostrarPerfilVendedorGUI extends JFrame implements ActionListener {
    private Usuario comprador;
    private ArrayList<ArrayList> usuarios;
    private Vendedor vendedor;
    private JPanel ventanaPNL;
    private JList comentariosLIST;
    private JList estrellasLIST;
    private JLabel icon;
    private JButton volverBTN;
    private JLabel nombreLBL;
    private JLabel correoLBL;
    private JLabel numeroLBL;
    private JLabel comentariosLBL;
    private JLabel estrellasLBL;
    private JScrollPane estrellasSCROLL;
    private JScrollPane comentariosSCROLL;


    public MostrarPerfilVendedorGUI(ArrayList<ArrayList> usuarios, Vendedor vendedor, Usuario comprador) {
        this.vendedor = vendedor;
        this.usuarios = usuarios;
        this.comprador = comprador;
    }
    public void SetDatosBasicos(){
        String nombre = "Nombre: " + vendedor.GetNombre() + " " + vendedor.GetApellido();
        nombreLBL.setText(nombre);

        correoLBL.setText("Correo: " + vendedor.GetCorreo());
        numeroLBL.setText("Numero telefonico: +56 9 " + vendedor.GetNumero());
    }
    public void SetComentarios(){
        ArrayList<String> comentarios = vendedor.GetComentarios();

        comentariosLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < comentarios.size(); i++) {
            modelo.addElement(comentarios.get(i));
        }

        comentariosLIST.setModel(modelo);
    }
    public void SetEstrrellas(){
        DefaultListModel modelo = new DefaultListModel();
        if (vendedor.GetEstrellas().isEmpty()){
            modelo.addElement("No hay calificaciones");
        }else{
            List<String> ListTexto = Arrays.asList(vendedor.DevolverStrEstrellas().split(";"));

            estrellasLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

            for (int i = 0; i < ListTexto.size(); i++) {
                modelo.addElement(ListTexto.get(i));
            }
            estrellasLIST.setModel(modelo);
        }
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
        comentariosSCROLL.setViewportView(comentariosLIST);
        estrellasSCROLL.setViewportView(estrellasLIST);

        SetDatosBasicos();

        setContentPane(ventanaPNL);

        setVisible(true);

        volverBTN.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volverBTN){
            MostrarServiciosVendedoresGUI mostrarServiciosDeVendedoresVentana = new MostrarServiciosVendedoresGUI(usuarios, comprador);
            mostrarServiciosDeVendedoresVentana.Pantalla();
            setVisible(false);
        }
    }
}
