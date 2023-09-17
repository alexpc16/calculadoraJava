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
            ResolverExpresion resolver = new ResolverExpresion();
            double r = ResolverExpresion.evaluar(textField.getText());
            System.out.println(r);
            ventana.resultado = Double.toString(r);
            textField.setText(ventana.resultado);
        }
        else if(buttonText.equals("raiz")){
            double ra = ventana.calcularRaiz(textField.getText());
            ventana.resultado = Double.toString(ra);
            textField.setText(ventana.resultado);
        }

        else if(buttonText.equals("ANS")){
            textField.setText(textField.getText() +  ventana.resultado );
            System.out.println(ventana.resultado);

        }

        else if(buttonText.equals("CE")){
            String text = textField.getText();
            String correccion = text.substring(0,text.length()-1);

            textField.setText(correccion);


        }


        else if(buttonText.equals("CA")){
            textField.setText("");
            ventana.resultado = "";



        }

       else if(buttonText.equals("C")){
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

    String resultado = "";

    JTextField textField;

    public MiVentana() {
        super("Titulo de ventana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtenemos el panel principal (contentPane) del frame para agregar elementos sobre el
        this.contentPane = getContentPane();

        // Definimos el modelo de layout para posicionar los elementos en el panel principal
        contentPane.setLayout(new GridBagLayout());

        crearCuadroTexto();
        crearBotones();

    }

    private void crearBotones() {

        String[] numeros = {".","0", " = ", "*", "1", "2", "3", "/", "4", "5", "6", "-",  "7", "8","9","+", "(", ")", "^", "ANS", "CA", "CE","C","raiz"};
        for (int i = 0; i < numeros.length; i++) {
            
        int col =  i % 4 ;
        int row =  6 - i / 4;
         
        // Creamos un botón personalizado usando la clase Boton
        Boton boton1 = new Boton(numeros[i], 100, 50, col , row );


        // Agregamos el botón personalizado al panel principal
        contentPane.add(boton1, boton1.constraintsB);

        boton1.addActionListener(new EventoBotonPulsado(textField, this)); // Pasamos la referencia a la ventana
        }// Pasamos la referencia a la ventana

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

        textField.setBorder(new EmptyBorder(10, 10, 10, 10));

        contentPane.add(textField, constraints);
    }

    public double calcularRaiz(String expresion){

        ResolverExpresion resolver = new ResolverExpresion();
        double r = resolver.evaluar(expresion);
    

        double raiz = Math.sqrt(r);

        return raiz;

    }
}
