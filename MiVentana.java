import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class EventoBotonPulsado implements ActionListener {
    private JTextField textField;
    private MiVentana ventana; // Agregamos una referencia a la ventana

    public EventoBotonPulsado(JTextField textField, MiVentana ventana) {
        this.textField = textField;
        this.ventana = ventana; // Inicializamos la referencia a la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        System.out.println(buttonText.equals(" 1 "));
        
        if(buttonText.equals(" = ")){
            ventana.resultado = textField.getText();

        }
        else if(buttonText.equals(" 1 ")){
            textField.setText("ya" + ventana.resultado + "ya");
            System.out.println(ventana.resultado);

        }
        else if(buttonText.equals(" 2 ")){
            textField.setText("");

        }
        else{

            textField.setText(textField.getText() + buttonText);
        }

        // Actualizamos el atributo 'resultado' de la ventana
    }
}

public class MiVentana extends JFrame {

    Container contentPane;

    String resultado;

    JTextField textField;

    public MiVentana() {
        super("Titulo de ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtenemos el panel principal (contentPane) del frame para agregar elementos sobre el
        this.contentPane = getContentPane();

        // Definimos el modelo de layout para posicionar los elementos en el panel principal
        contentPane.setLayout(new GridBagLayout());

        // Definimos las restricciones para el posicionamiento de los elementos

        crearCuadroTexto();
        crearBotones();
        crearBotonIgual();

        // No es necesario inicializar 'resultado' aquí

    }

    private void crearBotonIgual() {
        Boton botonIgual = new Boton(" = ", 100, 50, 2, 2);
        contentPane.add(botonIgual, botonIgual.constraintsB);

        botonIgual.addActionListener(new EventoBotonPulsado(textField, this)); // Pasamos la referencia a la ventana
    }

    private void crearBotones() {
        // Creamos un botón personalizado usando la clase Boton
        Boton boton1 = new Boton(" 1 ", 100, 50, 0, 1);
        Boton boton2 = new Boton(" 2 ", 100, 50, 1, 1);

        // Agregamos el botón personalizado al panel principal
        contentPane.add(boton1, boton1.constraintsB);
        contentPane.add(boton2, boton2.constraintsB);

        boton1.addActionListener(new EventoBotonPulsado(textField, this)); // Pasamos la referencia a la ventana
        boton2.addActionListener(new EventoBotonPulsado(textField, this)); // Pasamos la referencia a la ventana

    }

    private void crearCuadroTexto() {

        // Creamos un cuadro de texto con un ancho de 10 caracteres
        textField = new JTextField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1, 1, 10, 1);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3; // Se expande para ocupar 4 columnas
        constraints.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente

        textField.setBorder(new EmptyBorder(10, 20, 30, 40));

        contentPane.add(textField, constraints);
    }
}
