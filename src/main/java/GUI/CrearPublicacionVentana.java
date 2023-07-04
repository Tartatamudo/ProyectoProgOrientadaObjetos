package GUI;

import GUI.CompradorVents.LogueadoCompradorVentana;
import GUI.VendedorVents.LogueadoVendedorVentana;
import Usuarios.Servicio;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.AñadirCompradoresArchivo;
import static Datos.GestorArchivosVendedores.AñadirVendedoresArchivo;

public class CrearPublicacionVentana extends JFrame implements ActionListener  {
    private JFrame jFrame = new JFrame();
    private ArrayList<ArrayList> usuarios;
    private Usuario comprador;
    private Vendedor vendedor;
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private JPanel ventana;
    private JTextField nombreServTF;
    private JLabel lblDescServ;
    private JTextField descripcionTF;
    private JLabel lblNomPublicacion;
    private JButton btnCrearPublicacion;
    private JButton btnVolver;

    public CrearPublicacionVentana(Usuario comprador, ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.comprador = comprador;
        this.compradores = usuarios.get(0);
    }
    public CrearPublicacionVentana(ArrayList<ArrayList> usuarios, Vendedor vendedor) {
        this.usuarios = usuarios;

        this.vendedor = vendedor;
        this.vendedores = usuarios.get(1);
    }

    public void Pantalla(){
        // Configuramos la ventana
        setTitle("Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(ventana);

        btnCrearPublicacion.addActionListener(this);
        btnVolver.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btnCrearPublicacion){
            String nombre = nombreServTF.getText();
            String descripcion = descripcionTF.getText();
            Servicio servicio = new Servicio(nombre, descripcion);
            if ( comprador != null){
                comprador.CrearPublicacion(servicio);
                AñadirCompradoresArchivo(compradores);
                JOptionPane.showMessageDialog(jFrame, "publicaCion COMPRA creada exitosamente");

                LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
                logueadoCompradorVentana.Pantalla();
                setVisible(false);

            } else if (vendedor != null) {
                vendedor.CrearPublicacion(servicio);

                AñadirVendedoresArchivo(this.vendedores);
                JOptionPane.showMessageDialog(jFrame, "publicacion VENTA creada exitosamente");

                LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }

        }else if(e.getSource() == btnVolver){
            if (comprador != null){
                LogueadoCompradorVentana logueadoCompradorVentana = new LogueadoCompradorVentana(usuarios, comprador);
                logueadoCompradorVentana.Pantalla();
                setVisible(false);
            }else if ( vendedor != null){
                LogueadoVendedorVentana logueadoVendedorVentana = new LogueadoVendedorVentana(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }
        }
    }
}
