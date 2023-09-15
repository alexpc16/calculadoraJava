import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class Boton extends JButton {
    public GridBagConstraints constraintsB; 

    public Boton(String contenido, int ancho, int alto, int columna, int fila) {
        super(contenido);
        setPreferredSize(new Dimension(ancho, alto));
        constraintsB = new GridBagConstraints(); 
        constraintsB.gridx = columna;
        constraintsB.gridy = fila;
    }


}
