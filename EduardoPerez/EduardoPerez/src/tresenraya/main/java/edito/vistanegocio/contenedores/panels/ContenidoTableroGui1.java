package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.Posicion;
import tresenraya.main.java.edito.vistanegocio.botones.Boton;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import tresenraya.main.java.edito.vistanegocio.listeners.Escuchador;
import tresenraya.main.java.edito.vistanegocio.listeners.EscuchadorTableroGui1;

public class ContenidoTableroGui1 implements ContenidoTablero {

  private Escuchador escuchador;
  private Boton[] botones;
  private static final int CANTIDAD_BOTONES = 9;

  public ContenidoTableroGui1(TresEnRayaGui tresEnRayaGui){
    escuchador = new EscuchadorTableroGui1(tresEnRayaGui);
    botones = new Boton[CANTIDAD_BOTONES];
  }
  @Override
  public void cambiarText(int indice, String marca) {
      botones[indice].setText(marca);
  }

  @Override
  public Posicion convertir2D(int indice) {
    return new Posicion(indice / 3, indice % 3);
  }

  @Override
  public Boton agregarContenido(int indice) {
    Boton boton = new Boton();
    boton.nombreAccion(Integer.toString(indice), escuchador);
    botones[indice] = boton;
    return boton;
  }

  @Override
  public void reset() {
    for (int i = 0; i < CANTIDAD_BOTONES; i++) {
      botones[i].setText("");
    }
  }

  @Override
  public boolean marcaVacia(int indice) {
    return botones[indice].getText().isEmpty();
  }
}
