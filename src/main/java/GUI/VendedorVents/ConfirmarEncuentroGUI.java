package GUI.VendedorVents;

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

public class ConfirmarEncuentroGUI extends JFrame implements ActionListener {
    private JFrame jFrame = new JFrame();
    private Vendedor vendedor;
    private ArrayList<ArrayList> usuarios;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Usuario> compradores;

    private ArrayList<Usuario> compradoresConf;
    private JPanel ventanaPNL;
    private JComboBox compradorCBOX;
    private JButton confirmarBTN;
    private JButton volverBTN;
    private JList compradoresLIST;
    private JLabel icon;
    private JLabel tituloLBL;
    private JScrollPane compradoresSCROLL;

    public ConfirmarEncuentroGUI(Vendedor vendedor, ArrayList<ArrayList> usuarios) {
        this.vendedor = vendedor;
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
        this.vendedores = usuarios.get(1);
    }

    public void SetListaCompradores(){
        MenuLogueado menuLogueado = new MenuLogueado();

        this.compradoresConf = menuLogueado.ConfirmarContacto(vendedor, compradores);

        List<String> ListTexto = Arrays.asList(menuLogueado.StrConfirmarContacto(compradoresConf).split(";"));
        SetComboBoxCompradores(ListTexto.size());

        compradoresLIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();
        if(ListTexto.get(0).equals("") == false){
            for (int i = 0; i < ListTexto.size(); i++) {
                modelo.addElement(ListTexto.get(i));
            }
            compradoresLIST.setModel(modelo);
        }else {
            //Si no hay nadie ni siquiera dar opcion a elegir, sacar la ventana y volver atras
            JOptionPane.showMessageDialog(jFrame, "No tiene a nadie para evaluar");
            MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
    }
    public void SetComboBoxCompradores(int tamaño){
        for (int i = 0; i < tamaño; i++) {
            compradorCBOX.addItem(i);
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



        compradoresSCROLL.setViewportView(compradoresLIST);

        setVisible(true);

        SetListaCompradores();
        confirmarBTN.addActionListener(this);
        volverBTN.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == confirmarBTN){
            int numComprador = (Integer)compradorCBOX.getSelectedItem();

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ConfirmarEncuentro(vendedor, numComprador, compradoresConf);

            compradoresConf.remove(numComprador);
            SetListaCompradores();

            compradoresSCROLL.revalidate();
            compradoresSCROLL.repaint();

        }else if (e.getSource() == volverBTN){
            MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
    }
}


