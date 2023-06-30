package GUI;

import Usuarios.CrearUsuario;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import Utilidades.Validadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Utilidades.Validadores.*;

public class CrearUsuarioVentana extends JFrame implements ActionListener {
    Validadores validador = new Validadores();
    private JFrame jFrame = new JFrame();
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;
    private JPanel ventana;
    private JButton btnCrearComp;
    private JButton btnCrearVend;
    private JTextField nombreTF;
    private JTextField apellidoTF;
    private JTextField correoTF;
    private JTextField rutTF;
    private JTextField numeroTF;
    private JPasswordField contraseñaTF;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;
    private JLabel correoLabel;
    private JLabel rutLabel;
    private JLabel numeroLabel;
    private JLabel contraseñaLabel;
    private JButton btnVolver;

    public CrearUsuarioVentana(ArrayList<ArrayList> usuarios) {
        this.usuarios = usuarios;

        this.compradores = usuarios.get(0);
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

        btnCrearComp.addActionListener(this);
        btnCrearVend.addActionListener(this);
        btnVolver.addActionListener(this);
        setVisible(true);
    }
    public boolean ValidarEntradas(){
        if (!validador.ValidarRutChileno(rutTF.getText(), usuarios)){
            return false;
        }
        if (!validador.ValidarCorreoElectronico(correoTF.getText(), usuarios)){
            return false;
        }
        if (!validador.ValidarNumero(numeroTF.getText())){
            return false;
        }
       return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCrearComp){
            if (ValidarEntradas() == true){
                String nombre = nombreTF.getText();
                String apellido = apellidoTF.getText();
                String correo = correoTF.getText();
                String rut = rutTF.getText();
                rut = rut.replace(".", "").replace("-", "");
                int numero = Integer.parseInt(numeroTF.getText());
                String contraseña = String.valueOf(contraseñaTF.getPassword());

                Usuario com = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

                CrearUsuario crearUsuario = new CrearUsuario(com, usuarios);
                JOptionPane.showMessageDialog(jFrame, "Comoprador creado exitosamente");

                LoginVentana loginVentana = new LoginVentana();
                loginVentana.Pantalla();
                setVisible(false);
            }
        } else if (e.getSource() == btnCrearVend) {
            if (ValidarEntradas() == true){
                String nombre = nombreTF.getText();
                String apellido = apellidoTF.getText();
                String correo = correoTF.getText();
                String rut = rutTF.getText();
                int numero = Integer.parseInt(numeroTF.getText());
                String contraseña = String.valueOf(contraseñaTF.getPassword());

                Vendedor vendedor = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

                CrearUsuario crearUsuario = new CrearUsuario(vendedor, usuarios);
                JOptionPane.showMessageDialog(jFrame, "Vendedor creado exitosamente");

                LoginVentana loginVentana = new LoginVentana();
                loginVentana.Pantalla();
                setVisible(false);
            }

        } else if (e.getSource() == btnVolver) {
            LoginVentana loginVentana = new LoginVentana();
            loginVentana.Pantalla();
            setVisible(false);
        }
    }
}
