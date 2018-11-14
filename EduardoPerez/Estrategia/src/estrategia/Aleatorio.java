package estrategia;
import tresenraya.Posicion;

public class Aleatorio implements Estrategia {
  private int[] board;
  private static final int MI_PIEZA = 1;
  private static final int PIEZA_RIVAL = 2;
  private static final int TAMANIO = 3;

  public Aleatorio() {
    this.reiniciarJuego();
  }

  public Posicion hacerJugada() {
    int posicionRandom;
    for(posicionRandom = this.getPosicionAleatoaria(9);
        this.board[posicionRandom] != 0;
        posicionRandom = this.getPosicionAleatoaria(9)) {

    }

    this.board[posicionRandom] = 1;
    Posicion posicion = this.getPosicionBidimensional(posicionRandom, 3);
    return posicion;
  }

  public void registrarJugadaContraria(Posicion posicion) {
    this.board[this.getPosicionUnidimensional(posicion, 3)] = 2;
  }

  public void reiniciarJuego() {
    this.board = new int[9];
  }

  private int getPosicionUnidimensional(Posicion posicion, int columnas) {
    return posicion.fila * columnas + posicion.columna;
  }

  private Posicion getPosicionBidimensional(int posicion, int columnas) {
    return new Posicion(posicion / columnas, posicion % columnas);
  }

  private int getPosicionAleatoaria(int limite) {
    return (int)(Math.random() * (double)limite);
  }
}
