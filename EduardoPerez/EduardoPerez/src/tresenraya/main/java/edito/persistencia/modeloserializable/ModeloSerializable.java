package tresenraya.main.java.edito.persistencia.modeloserializable;

import java.io.Serializable;
import java.util.List;

public interface ModeloSerializable extends Serializable {

  void incrementarGanada(String nombreJugador);
  List<ModeloPuestoJugador> getListaJugadores();
  void agregarJugador(String nombre);
  int getJugador(String nombreJugador);
}
