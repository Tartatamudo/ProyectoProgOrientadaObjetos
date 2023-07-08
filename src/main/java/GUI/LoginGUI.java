package GUI;

import Datos.GestorArchivos;
import GUI.CompradorVents.MenuCompradorGUI;
import GUI.VendedorVents.MenuVendedorGUI;
import Login.Login;

import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class LoginGUI extends JFrame implements ActionListener, FocusListener {
    private JFrame jFrame = new JFrame();
    private ArrayList<Usuario> compradores = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<ArrayList> usuarios = new ArrayList<>();

    private JPanel ventanaPNL;
    private JPanel todoPNL;
    private JLabel iconLBL;
    private JTextField correoTF;
    private JPasswordField clavePF;
    private JButton registrarseBTN;
    private JButton ingresarBTN;
    private JButton salirBTN;


    public LoginGUI() {
        GestorArchivos gestorArchivos = new GestorArchivos();
        gestorArchivos.VerificarArchivos();
        gestorArchivos.CargarCompradoresAPrograma(compradores);
        gestorArchivos.CargarVendedoresAPrograma(vendedores);

        usuarios.add(compradores);
        usuarios.add(vendedores);
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

        correoTF.addFocusListener(this);
        clavePF.addFocusListener(this);

        ingresarBTN.addActionListener(this);
        registrarseBTN.addActionListener(this);
        salirBTN.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresarBTN ){
            String correo = correoTF.getText();
            String contraseña = String.valueOf(clavePF.getPassword());

            Login login = new Login(usuarios);

            Vendedor vendedor = login.LoginVendedor(correo, contraseña);
            Usuario comprador = login.LoginComprador(correo, contraseña);
            if (correo.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(jFrame, "Por favor, complete todos los campos");
                return;
            }
            if(comprador != null){
                JOptionPane.showMessageDialog(jFrame, "Entro como comprador");
                MenuCompradorGUI menuCompradorGUI = new MenuCompradorGUI(usuarios, comprador);
                menuCompradorGUI.Pantalla();
                setVisible(false);
            } else if (vendedor != null) {
                JOptionPane.showMessageDialog(jFrame, "Entro como vendedor");
                MenuVendedorGUI logueadoVendedorVentana = new MenuVendedorGUI(usuarios, vendedor);
                logueadoVendedorVentana.Pantalla();
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(jFrame, "No se encontro cuenta asociada al correo y contraseña entregados");
            }

        }else if ( e.getSource() == registrarseBTN){
            CrearUsuarioGUI crearUsuarioVentana = new CrearUsuarioGUI(usuarios);
            crearUsuarioVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == salirBTN){
            System.exit(WIDTH);
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == correoTF) {
            if (correoTF.getText().equals("Ingrese su correo electrónico")) {
                correoTF.setText("");
            }
        } else if (e.getSource() == clavePF) {
            if (clavePF.getText().equals("123456789")) {
                clavePF.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // No se realiza ninguna acción al perder el foco de los campos de texto
    }
}

