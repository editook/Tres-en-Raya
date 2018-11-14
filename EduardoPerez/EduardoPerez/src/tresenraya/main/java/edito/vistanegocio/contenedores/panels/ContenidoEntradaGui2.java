package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import tresenraya.main.java.edito.vistanegocio.listeners.Escuchador;
import tresenraya.main.java.edito.vistanegocio.listeners.EscuchadorTableroGui2;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ContenidoEntradaGui2 extends JPanel {
  private JComboBox selectorColumna, selectorFila;
  private Escuchador escuchador;

  public ContenidoEntradaGui2(TresEnRayaGui tresEnRayaGui) {
    setLayout(null);
    escuchador = new EscuchadorTableroGui2(this,tresEnRayaGui);
    initComponents();
  }

  private void crearComboBox(JComboBox comboBox, Point ubicacion) {
    comboBox.setBounds(ubicacion.x, ubicacion.y, 100, 50);
    comboBox.setOpaque(true);
    add(comboBox);
  }

  private void initComponents() {
    setBounds(70, 420, 300, 50);
    setFocusable(true);
    String[] columnas = {"","A", "B", "C"};
    String[] filas = {"","1", "2", "3"};
    selectorColumna = new JComboBox(columnas);
    crearComboBox(selectorColumna, new Point(0, 0));
    selectorFila = new JComboBox(filas);
    crearComboBox(selectorFila, new Point(200, 0));
    add(selectorFila);
    crearBoton();
  }

  private void crearBoton() {
    JButton aceptar = new JButton("Go");
    aceptar.setBounds(110, 0, 80, 50);
    aceptar.setActionCommand("Go");
    aceptar.addActionListener(escuchador);
    add(aceptar);
  }

  public String getDatoABC() {
    return String.valueOf(selectorColumna.getSelectedItem());
  }

  public String getDato123() {
    return String.valueOf(selectorFila.getSelectedItem());
  }

  public void reset() {
    selectorColumna.setSelectedIndex(0);
    selectorFila.setSelectedIndex(0);
  }
}
