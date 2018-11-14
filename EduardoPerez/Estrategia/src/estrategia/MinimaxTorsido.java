package estrategia;

import java.util.Random;
import tresenraya.Posicion;

public class MinimaxTorsido implements Estrategia {
  private int[][]matriz;

  public MinimaxTorsido(){
    matriz = new int[3][3];
  }
  public Posicion hacerJugada() {


    Posicion posicion = taparAgujero();
    if(posicion.columna== -1){
      posicion = obtenerPosicion();
    }
    /*
    if(matriz[posicion.fila][posicion.columna]==1){
      for (int i = 0;i<3;i++){
        for (int j = 0;j<3;j++) {
          if(matriz[i][j]==0){
            return new Posicion(i,j);
          }
        }
      }
    }
    */
    matriz[posicion.fila][posicion.columna]=1;
    return posicion;
  }
  private Posicion taparAgujero(){
    int cantidad;int aux;
    Posicion posicion = null;
    for(int i=0;i<3;i++){
      cantidad =0;
      aux = -1;
      for(int j=0;j<3;j++){
        if(matriz[i][j]==1){
          cantidad++;
        }
        else{
          aux = j;
        }
      }
      int valorTres = cantidad==2?aux:-1;
      posicion = new Posicion(i, valorTres);
    }
    return posicion;
  }
  @Override
  public void registrarJugadaContraria(Posicion posicion) {
    matriz[posicion.fila][posicion.columna] = 1;
  }

  @Override
  public void reiniciarJuego() {
    matriz = new int[3][3];
  }

  private Posicion obtenerPosicion(){
    Random random = new Random();
    int fila = random.nextInt(3);
    int columna = random.nextInt(3);
    return new Posicion(fila, columna);
  }
}
