import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MiVentana extends JFrame {

    public MiVentana() {
        super("Titulo de ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtenemos el panel principal (contentPane) del frame para agregar elementos sobre el
        Container contentPane = getContentPane();

        // Definimos el modelo de layout para posicionar los elementos en el panel principal
        contentPane.setLayout(new GridBagLayout());

        // Definimos las restricciones para el posicionamiento de los elementos
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1, 1, 10, 1);

        // Creamos un cuadro de texto con un ancho de 10 caracteres
        JTextField textField = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3; // Se expande para ocupar 4 columnas
        constraints.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        
        textField.setBorder(new EmptyBorder(10,20,30,40));
        
        contentPane.add(textField, constraints);


        // Creamos un botón personalizado usando la clase BotonNumero
        Boton boton1 = new Boton(" 1 ", 100, 50, 0, 1);
        Boton boton2 = new Boton(" 2 ", 100, 50, 1, 1);
        Boton boton3 = new Boton(" 3 ", 100, 50, 2, 2);


        // Agregamos el botón personalizado al panel principal
        contentPane.add(boton1, boton1.constraintsB);
        contentPane.add(boton2, boton2.constraintsB);
        contentPane.add(boton3, boton3.constraintsB);


    }


}
