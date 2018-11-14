package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import tresenraya.main.java.edito.vistanegocio.listeners.Escuchador;
import tresenraya.main.java.edito.vistanegocio.listeners.EscuchadorGui3;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ContenidoEntradaGui3 extends JPanel {
  private JButton botonJugdaorX, botonJugdaorO;
  private static final Font FONT = new Font("MS Reference Sans Serif", 3, 18);
  private Escuchador escuchador;

  public ContenidoEntradaGui3(TresEnRayaGui tresEnRayaGui) {
    setLayout(null);
    escuchador = new EscuchadorGui3(tresEnRayaGui);
    initComponents();
  }

  private void initComponents() {
    setBounds(70, 420, 300, 50);
    cargarPrimerBoton();
    cargarSegundoBoton();
  }

  private void cargarSegundoBoton() {
    botonJugdaorO = new JButton("O");
    botonJugdaorO.setFont(FONT);
    botonJugdaorO.setBounds(200, 0, 100, 50);
    botonJugdaorO.setText("O");
    botonJugdaorO.addActionListener(escuchador);
    botonJugdaorO.setEnabled(false);
    add(botonJugdaorO);
  }

  private void cargarPrimerBoton() {
    botonJugdaorX = new JButton("X");
    botonJugdaorX.setFont(FONT);
    botonJugdaorX.setBounds(0, 0, 100, 50);
    botonJugdaorX.setText("X");
    botonJugdaorX.addActionListener(escuchador);
    add(botonJugdaorX);
  }

  public void marcado(String marca) {
    if (botonJugdaorX.getActionCommand().equals(marca)) {
      botonJugdaorX.setEnabled(true);
      botonJugdaorO.setEnabled(false);
    } else {
      botonJugdaorX.setEnabled(false);
      botonJugdaorO.setEnabled(true);
    }
  }

}
