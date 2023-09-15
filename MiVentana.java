import java.awt.*;


import javax.swing.*;
import java.awt.Container;

public class MiVentana extends JFrame {

    public MiVentana() {

        super("Titulo de ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Obtenemos el panel principale (contentPane) del frame para agregar elementos sobre el

        Container contentPane = getContentPane();

        // Definimos el modelo de layout para posicionar los elementos en el panel principal
        contentPane.setLayout (new FlowLayout ());

        JLabel etiqueta = new JLabel ("Nombre: "); JTextField texto = new JTextField (20);
        JButton boton = new JButton ("Saludar");

        //Agregamos los eleentos al panel principal

        contentPane.add(etiqueta);
        contentPane.add(boton);
        contentPane.add(texto);
        

    }

}
