package tresenraya.main.java.edito.persistencia.modeloserializable;

import tresenraya.main.java.edito.persistencia.acceso.Conexion;
import java.util.ArrayList;
import java.util.List;

public class ModeloListaJugadores implements ModeloSerializable {

  private List<ModeloPuestoJugador> jugadores;

  public ModeloListaJugadores() {
    jugadores = new ArrayList<>();
  }

  @Override
  public void incrementarGanada(String nombreJugador) {
    int indice = getJugador(nombreJugador);
    if(indice!=-1){
      jugadores.get(indice).incremento();
      Conexion.setOutputStream(this);
    }
  }
  @Override
  public int getJugador(String nombre){
    int respuesta = -1;
    for (int i = 0; i < jugadores.size(); i++) {
      if (jugadores.get(i).nombreJugador.equals(nombre)) {
        respuesta = i;
        break;
      }
    }
    return respuesta;
  }

  @Override
  public List<ModeloPuestoJugador> getListaJugadores() {
    return jugadores;
  }

  @Override
  public void agregarJugador(String nombre) {
    int indice = getJugador(nombre);
    if(indice==-1){
      jugadores.add(new ModeloPuestoJugador(nombre,0));
      Conexion.setOutputStream(this);
    }
  }
}
