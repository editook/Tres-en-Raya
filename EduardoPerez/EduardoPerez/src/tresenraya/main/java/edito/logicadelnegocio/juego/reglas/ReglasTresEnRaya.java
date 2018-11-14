package tresenraya.main.java.edito.logicadelnegocio.juego.reglas;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.Posicion;
import tresenraya.main.java.edito.logicadelnegocio.tablerojuego.Tablero;
import tresenraya.main.java.edito.logicadelnegocio.tablerojuego.TableroTresEnRaya;

public class ReglasTresEnRaya {

  private Tablero tablero;
  private char turno;
  private char ganador;
  private int cantidadJugadas;
  private EstrategiaTresEnRaya estrategiaTresEnRaya;

  public ReglasTresEnRaya(){
    tablero = new TableroTresEnRaya();
    estrategiaTresEnRaya = new EstrategiaTresEnRaya(tablero);
    cantidadJugadas = 0;
    tablero.inicializar();

  }

  public void setTurno(char jugadorTurno) {
    turno = jugadorTurno;
    Ficha ficha = turno=='x'?Ficha.CRUZ:Ficha.CIRCULO;
    estrategiaTresEnRaya.setFicha(ficha);
  }

  public void hacerJugada(int x, int y, Ficha ficha) {
    Posicion posicion = new Posicion(x,y);
    if (realizarJugada(posicion)) {
      tablero.marcarUnaCasilla(posicion, ficha);
      ganador = estrategiaTresEnRaya.tresEnRaya() ? turno : '\0';
      cantidadJugadas++;
    }
  }

  public void reiniciarJuego() {
    cantidadJugadas = 0;
    ganador = '\0';
    tablero.inicializar();
  }

  public char getGanador() {
    return ganador;
  }

  public boolean juegoTerminado() {
    return cantidadJugadas==9 || ganador!='\0';
  }

  public boolean realizarJugada(Posicion posicion) {
    int valor = ((3 * posicion.fila) + posicion.columna);
    if(valor<9){
      return tablero.comparaCasilla(posicion,Ficha.VACIO);
    }
    return false;
  }
}
