package tresenraya.main.java.edito.jugadores;

import tresenraya.Posicion;
import tresenraya.main.java.edito.jugadores.estrategia.IEstrategia;
import tresenraya.main.java.edito.jugadores.estrategia.EstrategiaJugador;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRaya;

public class Jugador{

  private String nombre;
  private IEstrategia IEstrategia;
  public Jugador(String nombre){
    this.nombre = nombre;
    IEstrategia = null;
  }

  public String getNombre(){
    return nombre;
  }

  public void setIEstrategia(String []direccion){
    IEstrategia = new EstrategiaJugador(direccion);
  }

  public int realizaJugada(){
    if(IEstrategia !=null){
      Posicion posicion = IEstrategia.hacerJugada();
      return ((3 * posicion.fila) + posicion.columna);
    }
    return -1;
  }
  public void realizaJugada(int x, int y, TresEnRaya tresEnRaya) {
      if(tresEnRaya.esTurnoX()){
        tresEnRaya.hacerJugadaX(x,y);
      }
      else{
        tresEnRaya.hacerJugadaO(x,y);
      }
  }

  public void marcarJugada(int indice) {
    IEstrategia.registrarJugadaContraria(new Posicion(indice / 3, indice % 3));
  }
  public void reiniciarEstrategiaJuego(){
    IEstrategia.reiniciarJuego();
  }
}
