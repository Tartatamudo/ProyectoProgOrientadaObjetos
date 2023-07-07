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


public class EvaluarVendedorVentana extends JFrame implements ActionListener {
    private JFrame jFrame;
    private Usuario comprador;

    private ArrayList<ArrayList> usuarios;

    private ArrayList<Vendedor> vendedores;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedoresConf;
    private JComboBox cBoxVendedores;
    private JLabel lblEleccion;
    private JList vendedoresEvalList;
    private JScrollPane scrollVendEval;
    private JButton btnVendedorElec;
    private JComboBox cBoxEstrellas;
    private JTextField comentarioTF;
    private JPanel evaluacionJpanel;
    private JPanel ventana;
    private JButton btnVolver;

    public EvaluarVendedorVentana(Usuario comprador, ArrayList<ArrayList> usuarios) {
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

        vendedoresEvalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        DefaultListModel modelo = new DefaultListModel();
        if(ListTexto.get(0).equals("") == false){
            for (int i = 0; i < ListTexto.size(); i++) {
                modelo.addElement(ListTexto.get(i));
            }
            vendedoresEvalList.setModel(modelo);
        }else {
            //Si no hay nadie ni siquiera dar opcion a elegir, sacar la ventana y volver atras
            JOptionPane.showMessageDialog(jFrame, "No tiene a nadie para evaluar");
            LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }
    }

    public void SetComboBoxVendedores(int tamaño){
        for (int i = 0; i < tamaño; i++) {
            cBoxVendedores.addItem(i);
        }
    }

    public void SetComboBOxEstrellas(){
        for (int i = 0; i < 6; i++) {
            cBoxEstrellas.addItem(i);
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

        SetComboBOxEstrellas();

        scrollVendEval.setViewportView(vendedoresEvalList);

        setVisible(true);
        SetListaVendedores();
        btnVendedorElec.addActionListener(this);
        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVendedorElec){

            int numeroEleccion= (Integer) cBoxVendedores.getSelectedItem();

            int  numEstrellas = (Integer) cBoxEstrellas.getSelectedItem();

            String comentario = comentarioTF.getText();

            String rutConf = comprador.GetRut() + "true";
            vendedoresConf.get(numeroEleccion).AgregarEstrellas(numEstrellas);
            vendedoresConf.get(numeroEleccion).AgregarComentario(comentario);
            vendedoresConf.get(numeroEleccion).RemoverConfirmacion(rutConf);
            comprador.RemoverConfirmacion(vendedoresConf.get(numeroEleccion).GetRut());
            vendedoresConf.remove(numeroEleccion);
            SetListaVendedores();

            scrollVendEval.revalidate();
            scrollVendEval.repaint();

            GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios);
            gestionUsuarios.ActualizarVendedores();
            gestionUsuarios.ActualizarCompradores();

            LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }else if ( e.getSource() == btnVolver){
            LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
            logueadoCompradorVentana.Pantalla();
            setVisible(false);
        }
    }
}
