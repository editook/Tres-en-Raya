package tresenraya.main.java.edito.logicadelnegocio.juego;

public interface TresEnRaya {
  void hacerJugadaO(int x, int y);
  void hacerJugadaX(int x, int y);
  char obtenerGanador();// 'X', '' o '\0', 'O'
  void reiniciar();
  boolean terminado();
  boolean esTurnoX();
}
