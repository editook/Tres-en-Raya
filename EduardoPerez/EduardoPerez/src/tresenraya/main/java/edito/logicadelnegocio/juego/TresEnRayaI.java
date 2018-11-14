package tresenraya.main.java.edito.logicadelnegocio.juego;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.logicadelnegocio.juego.reglas.ReglasTresEnRaya;

public class TresEnRayaI implements TresEnRaya {

  private ReglasTresEnRaya reglasTresEnRaya;
  private boolean turno;/* turno true:x y false:o */

  public TresEnRayaI(){
    reglasTresEnRaya = new ReglasTresEnRaya();
    turno = true;
    reglasTresEnRaya.setTurno('x');
  }
  @Override
  public void hacerJugadaO(int x, int y) {
    if (!turno) {
      reglasTresEnRaya.hacerJugada(x, y, Ficha.CIRCULO);
      cambiarTurno();
    }
  }
  @Override
  public void hacerJugadaX(int x, int y) {
    if (turno) {
      reglasTresEnRaya.hacerJugada(x, y, Ficha.CRUZ);
      cambiarTurno();
    }
  }

  @Override
  public char obtenerGanador() {
    return reglasTresEnRaya.getGanador();
  }

  @Override
  public void reiniciar() {
  reglasTresEnRaya.reiniciarJuego();
  }
  private void cambiarTurno(){
    turno=!turno;
    char jugador = turno?'x':'o';
    reglasTresEnRaya.setTurno(jugador);
  }

  @Override
  public boolean terminado() {
    return reglasTresEnRaya.juegoTerminado();
  }

  @Override
  public boolean esTurnoX() {
    return turno;
  }

}
