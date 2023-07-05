package GUI;

import Login.Login;
import Usuarios.CrearUsuario;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import Utilidades.Validadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class CrearUsuarioGUI extends JFrame implements ActionListener , FocusListener {
    Validadores validador = new Validadores();
    private JFrame jFrame = new JFrame();
    private ArrayList<Usuario> compradores;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<ArrayList> usuarios;

    private JPanel ventanaPNL;
    private JPanel todoPNL;
    private JTextField nombreTF;
    private JTextField apellidoTF;
    private JTextField correoTF;
    private JTextField rutTF;
    private JTextField claveTF;
    private JTextField telefonoTF;
    private JButton vendedorBTN;
    private JButton volverBTN;
    private JButton compradorBTN;
    private JLabel iconLBL;


    public CrearUsuarioGUI(ArrayList<ArrayList> usuarios) {
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
        setContentPane(ventanaPNL);

        ventanaPNL.setFocusable(true);
        ventanaPNL.requestFocusInWindow();

        compradorBTN.addActionListener(this);
        vendedorBTN.addActionListener(this);
        volverBTN.addActionListener(this);

        nombreTF.addFocusListener(this);
        apellidoTF.addFocusListener(this);
        rutTF.addFocusListener(this);
        telefonoTF.addFocusListener(this);
        correoTF.addFocusListener(this);
        claveTF.addFocusListener(this);

        setVisible(true);
    }

    public boolean ValidarEntradas(){
        if (!validador.ValidarRutChileno(rutTF.getText(), usuarios)){
            return false;
        }
        if (!validador.ValidarCorreoElectronico(correoTF.getText(), usuarios)){
            return false;
        }
        if (!validador.ValidarNumero(telefonoTF.getText())){
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == compradorBTN){
            if (ValidarEntradas() == true){
                String nombre = nombreTF.getText();
                String apellido = apellidoTF.getText();
                String correo = correoTF.getText();
                String rut = rutTF.getText();
                rut = rut.replace(".", "").replace("-", "");
                int numero = Integer.parseInt(telefonoTF.getText());
                String contraseña = String.valueOf(claveTF.getText());

                Usuario com = new Usuario(nombre, apellido, correo, rut, numero, contraseña);

                CrearUsuario crearUsuario = new CrearUsuario(com, usuarios);
                JOptionPane.showMessageDialog(jFrame, "Comoprador creado exitosamente");

                LoginGUI loginVentana = new LoginGUI();
                loginVentana.Pantalla();
                setVisible(false);
            }
        } else if (e.getSource() == vendedorBTN) {
            if (ValidarEntradas() == true){
                String nombre = nombreTF.getText();
                String apellido = apellidoTF.getText();
                String correo = correoTF.getText();
                String rut = rutTF.getText();
                int numero = Integer.parseInt(telefonoTF.getText());
                String contraseña = String.valueOf(claveTF.getText());

                Vendedor vendedor = new Vendedor(nombre, apellido, correo, rut, numero, contraseña);

                CrearUsuario crearUsuario = new CrearUsuario(vendedor, usuarios);
                JOptionPane.showMessageDialog(jFrame, "Vendedor creado exitosamente");

                LoginGUI loginVentana = new LoginGUI();
                loginVentana.Pantalla();
                setVisible(false);
            }

        } else if (e.getSource() == volverBTN) {
            LoginGUI loginVentana = new LoginGUI();
            loginVentana.Pantalla();
            setVisible(false);
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == nombreTF) {
            if (nombreTF.getText().equals("Ingrese Nombre")) {
                nombreTF.setText("");
            }
        }
        else if (e.getSource() == apellidoTF) {
            if (apellidoTF.getText().equals("Ingrese Apellido")) {
                apellidoTF.setText("");
            }
        }
        else if (e.getSource() == rutTF) {
            if (rutTF.getText().equals("Ingrese Rut")) {
                rutTF.setText("");
            }
        }
        else if (e.getSource() == correoTF) {
            if (correoTF.getText().equals("Ingrese Correo Electrónico")) {
                correoTF.setText("");
            }
        }
        else if (e.getSource() == telefonoTF) {
            if (telefonoTF.getText().equals("Ingrese Número telefónico")) {
                telefonoTF.setText("");
            }
        }
        else if (e.getSource() == claveTF) {
            if (claveTF.getText().equals("Ingrese Contraseña")) {
                claveTF.setText("");
            }
        }


    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
