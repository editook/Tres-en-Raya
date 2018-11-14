package estrategia;

import tresenraya.Posicion;

public class Verticalisimo implements Estrategia{

  private int[][]matriz;
  public Verticalisimo(){
    matriz = new int[3][3];
  }

  @Override
  public Posicion hacerJugada() {
    Posicion posicion = new Posicion(-1,-1);
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        if(matriz[i][j]==0){
          posicion = new Posicion(i, j);
          break;
        }
      }
    }
    matriz[posicion.fila][posicion.columna]=2;
    return posicion;
  }

  @Override
  public void registrarJugadaContraria(Posicion posicion) {
    matriz[posicion.fila][posicion.columna] = 2;
  }

  @Override
  public void reiniciarJuego() {
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        matriz[i][j] = 0;
      }
    }
  }
}
