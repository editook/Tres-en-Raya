package tresenraya.main.java.edito.vistanegocio.botones;

import tresenraya.main.java.edito.vistanegocio.listeners.Escuchador;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class Boton extends JButton {
  public Boton() {
    setFocusPainted(false);
    setFont(new Font("Dialog", 0, 48));
    setPreferredSize(new Dimension(100, 100));
  }

  public void nombreAccion(String identificadorBoton, Escuchador escuchador) {
    setActionCommand(identificadorBoton);
    addActionListener(escuchador);
  }
}
