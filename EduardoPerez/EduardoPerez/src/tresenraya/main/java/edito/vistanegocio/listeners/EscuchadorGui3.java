package tresenraya.main.java.edito.vistanegocio.listeners;

import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import java.awt.event.ActionEvent;

public class EscuchadorGui3 implements Escuchador {

  private TresEnRayaGui tresEnRayaGui;

  public EscuchadorGui3(TresEnRayaGui tresEnRayaGui) {
    this.tresEnRayaGui = tresEnRayaGui;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    tresEnRayaGui.marcarCasilla(4);
  }
}
