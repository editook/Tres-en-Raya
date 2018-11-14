package tresenraya.main.java.edito.logicadelnegocio.tablerojuego;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Casilla;
import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.Posicion;

public class TableroTresEnRaya implements Tablero{

  private Casilla[]casillas;

  public TableroTresEnRaya(){
    casillas = new Casilla[9];
    inicializar();
  }

  @Override
  public void inicializar() {
    int rango = 0;
    while(rango < 9){
      casillas[rango]= new Casilla();
      rango++;
    }
  }

  @Override
  public void marcarUnaCasilla(Posicion posicion, Ficha ficha) {
    int indiceTablero = ((3 * posicion.fila) + posicion.columna);
    casillas[indiceTablero].setCasilla(ficha);
  }
  @Override
  public boolean comparaCasilla(Posicion posicion, Ficha ficha){
    Casilla casilla = new Casilla(ficha);
    int indiceTablero = ((3 * posicion.fila) + posicion.columna);
    return casillas[indiceTablero].compareTo(casilla)==0;
  }

}
