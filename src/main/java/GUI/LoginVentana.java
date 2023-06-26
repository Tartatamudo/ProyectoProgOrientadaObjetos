package GUI;

import Login.Login;
import Usuarios.Usuario;
import Usuarios.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Datos.GestorArchivosCompradores.CargarCompradoresAPrograma;
import static Datos.GestorArchivosVendedores.CargarVendedoresAPrograma;
import static Utilidades.Validadores.ValidarArchivos;

public class LoginVentana extends JFrame implements ActionListener {
    private JFrame jFrame = new JFrame();
    private ArrayList<Usuario> compradores = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<ArrayList> usuarios = new ArrayList<>();

    private JPanel ventana;
    private JTextField correoTF;
    private JPasswordField contraseñaTF;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JPanel SSSS;
    private JLabel SSSSlabel;
    private JLabel correoLbl;
    private JLabel contraseñaLbl;
    private JButton btnSalir;

    public LoginVentana() {

        ValidarArchivos();

        CargarCompradoresAPrograma(compradores);
        CargarVendedoresAPrograma(vendedores);

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

        setContentPane(ventana);


        btnIniciarSesion.addActionListener(this);
        btnRegistrarse.addActionListener(this);
        btnSalir.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion ){
            String correo = correoTF.getText();
            String contraseña = String.valueOf(contraseñaTF.getPassword());

            Login login = new Login(correo, contraseña, usuarios);

            Vendedor vendedor = login.LoginVendedor();
            Usuario comprador = login.LoginUsuario();

            if(comprador != null){
                JOptionPane.showMessageDialog(jFrame, "Entro como comprador");
            } else if (vendedor != null) {
                JOptionPane.showMessageDialog(jFrame, "Entro como vendedor");
            }else{
                JOptionPane.showMessageDialog(jFrame, "No se encontro cuenta asociada al correo y contraseña entregados");
            }

        }else if ( e.getSource() == btnRegistrarse){
            CrearUsuarioVentana crearUsuarioVentana = new CrearUsuarioVentana(usuarios);
            crearUsuarioVentana.Pantalla();
            setVisible(false);

        }else if (e.getSource() == btnSalir){
            System.exit(WIDTH);
        }
    }
}
