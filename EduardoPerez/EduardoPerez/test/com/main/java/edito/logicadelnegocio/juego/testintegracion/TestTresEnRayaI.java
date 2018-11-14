package com.main.java.edito.logicadelnegocio.juego.testintegracion;

import tresenraya.Posicion;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRaya;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRayaI;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.junit.Assert;
import org.junit.Test;

public class TestTresEnRayaI {

  public List<Posicion> cargarJuegoGanaX() {
    List<Posicion> jugadas = new ArrayList<Posicion>();

    jugadas.add(new Posicion(1,1));
    jugadas.add(new Posicion(0,1));
    jugadas.add(new Posicion(1,0));
    jugadas.add(new Posicion(1,2));
    jugadas.add(new Posicion(2,0));
    jugadas.add(new Posicion(0,0));
    jugadas.add(new Posicion(0,2));

    return jugadas;
  }

  public List<Posicion> cargarJuegoGanaO() {
    List<Posicion> jugadas = new ArrayList<Posicion>();

    jugadas.add(new Posicion(0,0));
    jugadas.add(new Posicion(1,1));
    jugadas.add(new Posicion(0,1));
    jugadas.add(new Posicion(0,2));
    jugadas.add(new Posicion(1,0));
    jugadas.add(new Posicion(2,0));

    return jugadas;
  }

  public List<Posicion> cargarJuegoEmpate() {
    List<Posicion> jugadas = new ArrayList<Posicion>();

    jugadas.add(new Posicion(1,1));
    jugadas.add(new Posicion(0,1));
    jugadas.add(new Posicion(0,0));
    jugadas.add(new Posicion(2,2));
    jugadas.add(new Posicion(2,1));
    jugadas.add(new Posicion(1,0));
    jugadas.add(new Posicion(2,0));
    jugadas.add(new Posicion(0,2));
    jugadas.add(new Posicion(1,2));

    return jugadas;
  }

  public char jugar(TresEnRaya juego, List<Posicion> jugadas) {

    ListIterator<Posicion> iteradorJugadas = jugadas.listIterator();
    Posicion posicion;
    while(iteradorJugadas.hasNext()) {
      posicion = iteradorJugadas.next();
      juego.hacerJugadaX(posicion.fila, posicion.columna);
      if(juego.terminado() || !iteradorJugadas.hasNext()) {
        break;
      } else {
        posicion = iteradorJugadas.next();
        juego.hacerJugadaO(posicion.fila,posicion.columna);
        if(juego.terminado()) {
          break;
        }
      }
    }

    return juego.obtenerGanador();

  }

  @Test
  public void testXEsGanador() {
    TresEnRaya juego = new TresEnRayaI();
    char ganador = jugar(juego,cargarJuegoGanaX());
    Assert.assertEquals(ganador,'x');
  }

  @Test
  public void testOEsGanador() {
    TresEnRaya juego = new TresEnRayaI();
    char ganador = jugar(juego,cargarJuegoGanaO());
    Assert.assertEquals(ganador,'o');
  }

  @Test
  public void testEmpate() {
    TresEnRaya juego = new TresEnRayaI();
    char ganador = jugar(juego,cargarJuegoEmpate());
    Assert.assertTrue(ganador=='\0');
  }

}