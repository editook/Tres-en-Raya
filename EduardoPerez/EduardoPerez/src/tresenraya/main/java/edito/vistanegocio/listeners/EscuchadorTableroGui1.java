package tresenraya.main.java.edito.vistanegocio.listeners;

import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import java.awt.event.ActionEvent;

public class EscuchadorTableroGui1 implements Escuchador {
  private TresEnRayaGui tresEnRayaGui;
  public EscuchadorTableroGui1(TresEnRayaGui tresEnRayaGui) {
    this.tresEnRayaGui = tresEnRayaGui;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    int indice = Integer.parseInt(event.getActionCommand());
    tresEnRayaGui.marcarCasilla(indice);
  }
}
