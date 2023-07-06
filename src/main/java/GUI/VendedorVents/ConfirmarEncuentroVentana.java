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

import static Login.MenuLogueado.*;

public class ConfirmarEncuentroVentana extends JFrame implements ActionListener {

    private JFrame jFrame = new JFrame();
    private Vendedor vendedor;

    private ArrayList<ArrayList> usuarios;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Usuario> compradores;

    private ArrayList<Usuario> compradoresConf;
    private JList compradoresConfList;
    private JPanel ventana;
    private JScrollPane scroollCompradoresList;
    private JComboBox cBoxCompradoresConf;
    private JButton btnConfirmar;
    private JButton btnVolver;

    public ConfirmarEncuentroVentana(Vendedor vendedor, ArrayList<ArrayList> usuarios) {
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

        compradoresConfList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();
        if(ListTexto.get(0).equals("") == false){
            for (int i = 0; i < ListTexto.size(); i++) {
                modelo.addElement(ListTexto.get(i));
            }
            compradoresConfList.setModel(modelo);
        }else {
            //Si no hay nadie ni siquiera dar opcion a elegir, sacar la ventana y volver atras
            JOptionPane.showMessageDialog(jFrame, "No tiene a nadie para evaluar");
            LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
    }
    public void SetComboBoxCompradores(int tamaño){
        for (int i = 0; i < tamaño; i++) {
            cBoxCompradoresConf.addItem(i);
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


        scroollCompradoresList.setViewportView(compradoresConfList);

        setVisible(true);

        SetListaCompradores();
        btnConfirmar.addActionListener(this);
        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btnConfirmar){
            int numComprador = (Integer)cBoxCompradoresConf.getSelectedItem();

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ConfirmarEncuentro(vendedor, numComprador, compradoresConf);

            compradoresConf.remove(numComprador);
            SetListaCompradores();

            scroollCompradoresList.revalidate();
            scroollCompradoresList.repaint();

        }else if (e.getSource() == btnVolver){
            LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
            logueadoVendedorVentana.Pantalla();
            setVisible(false);
        }
    }
}
