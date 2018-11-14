package tresenraya.main.java.edito.logicadelnegocio.tablerojuego;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.Posicion;

public interface Tablero {

  void inicializar();

  void marcarUnaCasilla(Posicion posicion, Ficha ficha);
  boolean comparaCasilla(Posicion posicion, Ficha ficha);
}
