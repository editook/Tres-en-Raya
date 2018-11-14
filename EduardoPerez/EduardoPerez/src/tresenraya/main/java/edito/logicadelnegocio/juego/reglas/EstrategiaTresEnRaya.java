package tresenraya.main.java.edito.logicadelnegocio.juego.reglas;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.Posicion;
import tresenraya.main.java.edito.logicadelnegocio.tablerojuego.Tablero;

public class EstrategiaTresEnRaya {

  private Tablero tablero;
  private Ficha ficha;

  public EstrategiaTresEnRaya(Tablero tablero) {
    this.tablero = tablero;
  }

  public boolean tresEnRaya() {
    return tresEnRayaVertical() || tresEnRayaHorizontal() ||
        tresEnRayaDiagonalX() || tresEnRayaDiagonalY();
  }

  private boolean tresEnRayaVertical(){
    int fila=0,columna=0;
    int cantidad = 0;
    while(fila < 3){
      columna = 0;
      cantidad = 0;
      while (columna < 3){
        cantidad += iterador(fila,columna)?1:0;
        columna++;
      }
      if(cantidad == 3){
        return true;
      }
      fila++;
    }
    return false;
  }
  private boolean iterador(int fila,int columna){
    Posicion posicion = new Posicion(fila, columna);
    return tablero.comparaCasilla(posicion, ficha);
  }
  private boolean tresEnRayaHorizontal(){
    int fila=0,columna = 0;
    int cantidad = 0;
    while(fila < 3) {
      columna = 0;
      cantidad = 0;
      while (columna < 3) {
        cantidad += iterador(columna, fila) ? 1 : 0;
        columna++;
      }
      if (cantidad == 3) {
        return true;
      }
      fila++;
    }
    return false;
  }
  private boolean tresEnRayaDiagonalX(){
    int columna=0;int fila = 0;
    int cantidad = 0;
    while(columna<3){
      cantidad += iterador(fila,columna)?1:0;
      columna++;
      fila++;
    }
    return cantidad == 3;
  }
  private boolean tresEnRayaDiagonalY(){
    int columna=0;int fila = 2;
    int cantidad = 0;
    while(fila>=0){
      cantidad += iterador(fila,columna)?1:0;
      columna++;
      fila--;
    }
    return cantidad == 3;
  }
  public void setFicha(Ficha ficha) {
    this.ficha = ficha;
  }
}
