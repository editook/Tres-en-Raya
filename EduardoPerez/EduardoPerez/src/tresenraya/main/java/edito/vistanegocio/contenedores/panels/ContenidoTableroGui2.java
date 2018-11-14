package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.Posicion;
import tresenraya.main.java.edito.vistanegocio.botones.Boton;
import tresenraya.main.java.edito.vistanegocio.canvas.Figura;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ContenidoTableroGui2 extends JPanel implements ContenidoTablero {

  private Figura[][] figuras;
  public ContenidoTableroGui2(){
    figuras = new Figura[3][3];
    setBounds(70, 110, 302, 302);
    setLayout(null);
    setBackground(Color.GRAY);
    crearCasillas();
  }
  private void crearCasillas() {
    int x = 0;
    int y = 0;
    int aux = y;
    for (int i = 0; i < figuras.length; i++) {
      y = aux;
      for (int j = 0; j < figuras.length; j++) {
        figuras[i][j] = new Figura(x, y);
        y = y + 100;
      }
      x = x + 100;
    }
  }
  @Override
  public void cambiarText(int indice, String marca) {
    Posicion posicion = new Posicion(indice / 3, indice % 3);
    figuras[posicion.columna][posicion.fila].cambiarTexto(marca);
  }

  @Deprecated
  public Posicion convertir2D(int indice) {
    return null;
  }

  @Deprecated
  public Boton agregarContenido(int indice) {
    return null;
  }

  @Override
  public void reset() {
    for (Figura[] figura : figuras) {
      for (int j = 0; j < figuras.length; j++) {
        figura[j].cambiarTexto(" ");
      }
    }
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D) g;
    graphics2D.setColor(Color.BLACK);
    graphics2D.fill3DRect(0, 0, 302, 302,true);
    for (Figura[] figura : figuras) {
      for (int j = 0; j < figuras.length; j++) {
        figura[j].paint(graphics2D);
      }
    }
    repaint();
  }
  @Override
  public boolean marcaVacia(int indice) {
    if(indice>=0){
      return figuras[indice % 3][indice / 3].vacio();
    }
    return false;
  }
}
