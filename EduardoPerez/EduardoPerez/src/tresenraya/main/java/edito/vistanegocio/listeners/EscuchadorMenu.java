package tresenraya.main.java.edito.vistanegocio.listeners;

import java.awt.Frame;
import javax.swing.JOptionPane;
import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui1;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui2;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui3;
import java.awt.event.ActionEvent;

public class EscuchadorMenu implements Escuchador {

  private static final String NEW_GAME = "New Game";
  private static final String EXIT = "Exit";
  private static final String NEW_PLAYER_CROSS = "New Player Cross";
  private static final String NEW_PLAYER_CIRCLE = "New Player Circle";
  private static final String ABOUT = "About";
  private static final String RESULTS_TABLE = "Results Table";
  private static final String WINDOW_ALTERNATIVE_VIEW = "Window Alternative View";
  private static final String WINDOW_CLASIC_VIEW = "Window Clasic View";
  private static final String WINDOW_PLAYER_VS_PLAYER = "Window Player vs Player";
  private TresEnRayaGui tresEnRayaGui;
  public EscuchadorMenu(TresEnRayaGui tresEnRayaGui) {
    this.tresEnRayaGui = tresEnRayaGui;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case NEW_GAME:
        this.JuegoNuevo();
        break;
      case NEW_PLAYER_CROSS:
        nuevoJugador(Ficha.CRUZ);
        break;
      case NEW_PLAYER_CIRCLE:
        nuevoJugador(Ficha.CIRCULO);
        break;
      case ABOUT:
        aboutActionPerformed();
        break;
      case RESULTS_TABLE:
        mostrarTablero();
        break;
      case WINDOW_ALTERNATIVE_VIEW: cargarNuevaVentanaAlternativa();break;
      case WINDOW_PLAYER_VS_PLAYER: cargarVentanaJugadorVsJugador();break;
      case WINDOW_CLASIC_VIEW: cargarJuegoClasico();break;
      case EXIT:
        exitActionPerformed();
    }
  }
  private void cargarJuegoClasico(){
    tresEnRayaGui.visibility(1);
    TresEnRayaGui tresEnRayaGui1 = new TresEnRayaGui1();
    tresEnRayaGui1.visibility(0);
  }
  private void cargarNuevaVentanaAlternativa(){
    tresEnRayaGui.visibility(1);
    TresEnRayaGui tresEnRayaGui2 = new TresEnRayaGui2();
    tresEnRayaGui2.visibility(0);
  }
  private void cargarVentanaJugadorVsJugador(){
    tresEnRayaGui.visibility(1);
    TresEnRayaGui tresEnRayaGui = new TresEnRayaGui3();
    tresEnRayaGui.visibility(0);
    JOptionPane.showMessageDialog((Frame)tresEnRayaGui,"carge estrategia para cada jugador");
  }
  private void mostrarTablero() {
    tresEnRayaGui.mostrarTablero();
  }

  private void nuevoJugador(Ficha ficha) {
    if (ficha == Ficha.CRUZ) {
      tresEnRayaGui.nuevoJugadorX();
    } else {
      tresEnRayaGui.nuevoJugadorO();
    }
  }

  private void exitActionPerformed() {
    System.exit(0);
  }

  private void JuegoNuevo() {
    tresEnRayaGui.nuevoJuego();
  }

  private void aboutActionPerformed() {
    tresEnRayaGui.notificarAbout();
  }
}
