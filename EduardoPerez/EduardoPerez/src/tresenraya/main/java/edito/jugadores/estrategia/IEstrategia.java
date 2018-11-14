package tresenraya.main.java.edito.jugadores.estrategia;

import tresenraya.Posicion;
public interface IEstrategia {
  Posicion hacerJugada();
  void registrarJugadaContraria(Posicion posicion);
  void reiniciarJuego();
}
