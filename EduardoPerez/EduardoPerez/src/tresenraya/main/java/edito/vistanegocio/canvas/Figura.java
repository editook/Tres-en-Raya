package tresenraya.main.java.edito.vistanegocio.canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Figura {
  private int x, y, tamanio;
  private String valor;
  private static final Font FONT = new Font("MS Reference Sans Serif", Font.PLAIN, 80);
  private static final BasicStroke BASIC_STROKE = new BasicStroke(9);

  public Figura(int x, int y) {
    this.x = x;
    this.y = y;
    this.tamanio = 100;
    valor = " ";
  }

  public void paint(Graphics2D g) {
    g.setColor(Color.WHITE);
    g.fill3DRect(x+2, y+2, tamanio-2, tamanio-2,true);
    g.setStroke(BASIC_STROKE);
    Color color = valor.equals("X")?Color.BLUE:Color.RED;
    g.setColor(color);
    g.setFont(FONT);
    g.drawString(valor, x + 25, y + 80);
  }
  public boolean vacio(){
    return valor.equals(" ");
  }
  public void cambiarTexto(String nuevoValor) {
    valor = nuevoValor;
  }
}
