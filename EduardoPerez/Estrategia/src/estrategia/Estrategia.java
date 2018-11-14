package estrategia;

import tresenraya.Posicion;

public interface Estrategia {

  Posicion hacerJugada();
  void registrarJugadaContraria(Posicion posicion);
  void reiniciarJuego();
}
