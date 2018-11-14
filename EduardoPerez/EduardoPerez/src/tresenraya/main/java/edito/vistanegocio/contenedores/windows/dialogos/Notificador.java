package tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenedorEstrategia;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class Notificador extends JOptionPane implements NotificadorI{

  private TresEnRayaGui tresEnRayaGui;
  private static final String BREAK_LINE = "\n";

  public Notificador(TresEnRayaGui tresEnRayaGui){
    this.tresEnRayaGui = tresEnRayaGui;
  }
  @Override
  public void alertaErrorPosicion() {
    mostrarMensaje(
        "Oops...",
        "Este espacio esta ocupado," + BREAK_LINE + "Por favor elija otro.",
        ERROR_MESSAGE);
  }

  @Override
  public void notificarGanador(String nombreGanador) {
    String output;
    if (!nombreGanador.equals("maquina")) {
      output = "Felicidades \"" + nombreGanador + "\" ganaste!!! \nQuieres volver a jugar?";
    } else {
      output = "    Fue sencillo!! Te gane "+BREAK_LINE+"¿Quieres volver a jugar?";
    }
    int choice =
        showConfirmDialog(
            (Frame)tresEnRayaGui, output, "Aplausos!", YES_NO_OPTION, INFORMATION_MESSAGE);
    if (choice == 0) tresEnRayaGui.nuevoJuego();
    else System.exit(0);
  }

  @Override
  public void notificarEmpate() {
    mostrarMensaje(
        "Juego Empatado...", "Fue un Empate, choco/a!!! \nJuguemos otra vez!", INFORMATION_MESSAGE);
    tresEnRayaGui.nuevoJuego();
  }

  @Override
  public void notificarAbout(String autor) {
    mostrarMensaje(
        "Tic Tac Toe", "Este simple Juego fue creado por \n"+autor+"." , INFORMATION_MESSAGE);
  }

  @Override
  public String nuevoNombreJugador(Ficha ficha) {
    return showInputDialog(
        (Frame)tresEnRayaGui,
        "Jugaras con Ficha " + ficha.name() +BREAK_LINE+ "¿ Cual es su nombre ?",
        "Tic Tac Toe",
        WARNING_MESSAGE);
  }

  @Override
  public String[] adquirirEstrategia(Ficha ficha) {
    String datos[] = new String[3];
    ContenedorEstrategia contenedorEstrategia = new ContenedorEstrategia(datos,ficha);
    JOptionPane.showMessageDialog(null, contenedorEstrategia);
    return datos;

  }
  private void mostrarMensaje(String titulo, String mensaje, int tipoMensaje) {
    showMessageDialog((Frame)tresEnRayaGui, mensaje, titulo, tipoMensaje);
  }
}
