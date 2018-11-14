package tresenraya.main.java.edito.jugadores.estrategia;

import tresenraya.Posicion;

public class EstrategiaJugador implements IEstrategia {

  private ReflexionEstrategica reflexionEstrategica;

  public EstrategiaJugador( String[] archivo) {
    reflexionEstrategica = new ReflexionEstrategica(archivo);
  }

  @Override
  public Posicion hacerJugada() {
    return reflexionEstrategica.ejecutarMetodoHacerJugada();
  }

  @Override
  public void registrarJugadaContraria(Posicion posicion) {
    reflexionEstrategica.metodoRegistrarJugada(posicion);
  }

  @Override
  public void reiniciarJuego() {
    reflexionEstrategica.reiniciarJuego();
  }

}
