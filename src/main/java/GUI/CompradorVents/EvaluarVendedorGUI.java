package GUI.CompradorVents;

import Login.MenuLogueado;
import Usuarios.GestionUsuarios;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluarVendedorGUI extends  JFrame implements ActionListener {

    private JFrame jFrame;
    private Usuario comprador;

    private ArrayList<ArrayList> usuarios;

    private ArrayList<Vendedor> vendedores;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedoresConf;
    private JPanel ventanaPNL;
    private JList vendedorLIST;
    private JComboBox vendedorCBOX;
    private JTextField comentarioTF;
    private JButton calificarBTN;
    private JButton volverBTN;
    private JLabel iconLBL;
    private JScrollPane vendedorSCROLL;
    private JLabel vendedorLBL;
    private JLabel comentarioLBL;
    private JComboBox estrellasCBOX;
    private JLabel estrellasLBL;

    public EvaluarVendedorGUI(Usuario comprador, ArrayList<ArrayList> usuarios) {
        this.comprador = comprador;
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
    }

    public void SetListaVendedores(){
        MenuLogueado menuLogueado = new MenuLogueado();
        this.vendedoresConf = menuLogueado.VendedoresConfirm(vendedores, comprador);
        List<String> ListTexto = Arrays.asList(menuLogueado.DevolverStrEvaluarVendedor(vendedoresConf, comprador).split(";"));
        SetComboBoxVendedores(ListTexto.size());

        vendedorLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();
        if(ListTexto.get(0).equals("") == false){
            for (int i = 0; i < ListTexto.size(); i++) {
                modelo.addElement(ListTexto.get(i));
            }
            vendedorLIST.setModel(modelo);
        }else {
            //Si no hay nadie ni siquiera dar opcion a elegir, sacar la ventana y volver atras
            JOptionPane.showMessageDialog(jFrame, "No tiene a nadie para evaluar");
            MenuCompradorGUI logueadoCompradorVentana = new MenuCompradorGUI(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }
    }

    public void SetComboBoxVendedores(int tamaño){
        for (int i = 0; i < tamaño; i++) {
            vendedorCBOX.addItem(i);
        }
    }

    public void SetComboBOxEstrellas(){
        for (int i = 0; i < 6; i++) {
            estrellasCBOX.addItem(i);
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
        SetComboBOxEstrellas();

        vendedorSCROLL.setViewportView(vendedorLIST);

        setVisible(true);
        SetListaVendedores();
        calificarBTN.addActionListener(this);
        volverBTN.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calificarBTN){

            int numeroEleccion= (Integer) vendedorCBOX.getSelectedItem();

            int  numEstrellas = (Integer) estrellasCBOX.getSelectedItem();

            String comentario = comentarioTF.getText();

            String rutConf = comprador.GetRut() + "true";
            vendedoresConf.get(numeroEleccion).AgregarEstrellas(numEstrellas);
            vendedoresConf.get(numeroEleccion).AgregarComentario(comentario);
            vendedoresConf.get(numeroEleccion).RemoverConfirmacion(rutConf);
            comprador.RemoverConfirmacion(vendedoresConf.get(numeroEleccion).GetRut());
            vendedoresConf.remove(numeroEleccion);
            SetListaVendedores();

            vendedorSCROLL.revalidate();
            vendedorSCROLL.repaint();

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ActualizarVendedores();
            gestionUsuarios.ActualizarCompradores();

            MenuCompradorGUI menuCompradorGUI = new MenuCompradorGUI(usuarios, comprador);
            menuCompradorGUI.Pantalla();
            setVisible(false);
        }else if ( e.getSource() == volverBTN){
            MenuCompradorGUI menuCompradorGUI = new MenuCompradorGUI(usuarios, comprador);
            menuCompradorGUI.Pantalla();
            setVisible(false);
        }
    }
}
