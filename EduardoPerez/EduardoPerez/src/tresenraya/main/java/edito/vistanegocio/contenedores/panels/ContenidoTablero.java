package tresenraya.main.java.edito.vistanegocio.contenedores.panels;

import tresenraya.Posicion;
import tresenraya.main.java.edito.vistanegocio.botones.Boton;

public interface ContenidoTablero {

  void cambiarText(int indice, String marca);

  Posicion convertir2D(int indice);

  Boton agregarContenido(int indice);

  void reset();

  boolean marcaVacia(int indice);
}
