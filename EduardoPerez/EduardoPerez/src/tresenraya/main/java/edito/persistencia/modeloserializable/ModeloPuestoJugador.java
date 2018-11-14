package tresenraya.main.java.edito.persistencia.modeloserializable;

import java.io.Serializable;

public class ModeloPuestoJugador implements Comparable<ModeloPuestoJugador>, Serializable {

  public int posicionRange;
  public String nombreJugador;

  ModeloPuestoJugador(String nombre, int puntaje) {
    nombreJugador = nombre;
    posicionRange = puntaje;
  }

  void incremento() {
    posicionRange++;
  }

  @Override
  public int compareTo(ModeloPuestoJugador jugadorPosicionado) {
    return Integer.compare(posicionRange, jugadorPosicionado.posicionRange);
  }
}
