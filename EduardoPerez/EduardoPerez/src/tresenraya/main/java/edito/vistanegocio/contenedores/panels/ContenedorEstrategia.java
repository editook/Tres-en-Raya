package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ContenedorEstrategia extends JPanel {
  private Ficha ficha;
  private JPanel pGrid;
  private JTextField nombreField;
  private String []datos;
  private JLabel logArchivo;
  public ContenedorEstrategia(String[] datos, Ficha ficha){
    this.ficha = ficha;
    inicomponents();
    this.datos = datos;
  }

  private void inicomponents() {
    setLayout(new BorderLayout());
    setSize(500,400);
    JPanel p = new JPanel();
    pGrid = new JPanel();
    this.setComponentZOrder(p,0);
    p.add(pGrid);
    p.setLayout(new GridLayout(1, 1));
    pGrid.setLayout(new GridLayout(8, 2));
    pGrid.add(new JLabel());
    pGrid.add(new JLabel());
    cargarDatos();
    botonAceptar();
  }

  private void botonAceptar() {
    JButton aceptar = new JButton("Cargar IEstrategia");
    aceptar.addActionListener(e -> {
      String nombre = nombreField.getText();
      if(nombre!=null){
        datos[2]=nombre;
        logArchivo.setText("IEstrategia Preparada");
      }
      else{
        JOptionPane.showMessageDialog(null,"error falta nombre");
      }
    });
    JButton cancelar = new JButton("Cancelar");
    cancelar.addActionListener(e -> {
      //dispose();
    });
    pGrid.add(new JLabel());
    logArchivo= new JLabel("Sin IEstrategia");
    pGrid.add(logArchivo);
    pGrid.add(new JLabel());
    pGrid.add(aceptar);
  }

  private void cargarDatos() {
    JLabel informacionText = new JLabel("Ficha asignada: ");
    JTextField informacionField = new JTextField(ficha+"");
    informacionField.setEnabled(false);
    JLabel nombreText = new JLabel("Nombre: ");
    nombreField = new JTextField("");
    JLabel estrategiaText = new JLabel("estrategia :");
    JButton estrategiaFile = new JButton("Buscar Archivo");
    estrategiaFile.addActionListener(e -> {
      String []date = obtenerEstrategia();
      datos[0] = date[0];
      datos[1] = date[1];
    });
    pGrid.add(informacionText);
    pGrid.add(informacionField);
    pGrid.add(new JLabel());
    pGrid.add(new JLabel());
    pGrid.add(nombreText);
    pGrid.add(nombreField);
    pGrid.add(new JLabel());
    pGrid.add(new JLabel());
    pGrid.add(estrategiaText);
    pGrid.add(estrategiaFile);
  }
  private String[] obtenerEstrategia(){
    String[] direcciones = new String[2];
    JFileChooser file = new JFileChooser(".");
    FileFilter filtro = new FileNameExtensionFilter(("Solo Archivos java (.class)"), "class");
    file.setFileFilter(filtro);
    file.setFileSelectionMode(JFileChooser.FILES_ONLY);
    try {
      file.showOpenDialog(this);
      String direccion = file.getCurrentDirectory().toURI().toURL().toString();
      File archivo = file.getSelectedFile();
      direcciones[1] = direccion;
      if (archivo != null) {
        String nombreArchivo = archivo.getName().substring(0, archivo.getName().indexOf('.'));
        direcciones[0] = nombreArchivo;
      }
    } catch (MalformedURLException e) {
      System.err.println("el archivo no pudo se cargado error: " + e);
    }
    return direcciones;
  }
}
