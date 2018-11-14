package tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame;

import tresenraya.main.java.edito.persistencia.acceso.Conexion;
import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloPuestoJugador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class TablaResultadosGui extends Frame {
  private DefaultTableModel modeloContenido;
  private TresEnRayaGui tresEnRayaGui;

  TablaResultadosGui(TresEnRayaGui tresEnRayaGui){
    this.tresEnRayaGui = tresEnRayaGui;
    iniComponents();
  }

  private void iniComponents() {
    setLayout(new BorderLayout());
    JTable tabla = new JTable(crearModelo());
    tabla.getColumn("Standing").setMaxWidth(60);
    tabla.getColumn("Player").setMaxWidth(140);
    tabla.getColumn("Winner").setMaxWidth(60);
    tabla.setPreferredScrollableViewportSize(new Dimension(240, 150));
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    tabla.setDefaultRenderer(Object.class, centerRenderer);
    JScrollPane scrollPane = new JScrollPane(tabla);
    add(scrollPane);
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent evt) {
            minimizar();

          }
        });

    pack();
  }
  private void minimizar(){
    dispose();
  }
  private DefaultTableModel crearModelo(){
    modeloContenido = new DefaultTableModel();
    modeloContenido.addColumn("Standing");
    modeloContenido.addColumn("Player");
    modeloContenido.addColumn("Winner");
    return modeloContenido;
  }

  void refrescarPuntuacion() {
    Object[] data;
    int cont = 1;
    List<ModeloPuestoJugador> contenido = Conexion.getInputStream().getListaJugadores();
    contenido.sort(Collections.reverseOrder());
    modeloContenido.setRowCount(0);
    String[] nombre = (tresEnRayaGui.toString()).split(" ");
    String jugadorX = nombre[0];
    String jugadorO = nombre.length>1?nombre[1]:"";
    for (ModeloPuestoJugador puesto : contenido) {
      if (cont <= 10) {
        String puntero =jugadorX.equals(puesto.nombreJugador)?"  (*)":
            jugadorO.equals(puesto.nombreJugador)?"  (*)":"";
        data = new Object[] {cont, puesto.nombreJugador+puntero, puesto.posicionRange};
        modeloContenido.addRow(data);
        cont++;
      }
    }
  }

}
