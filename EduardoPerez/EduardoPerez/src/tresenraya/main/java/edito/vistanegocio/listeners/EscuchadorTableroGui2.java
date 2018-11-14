package tresenraya.main.java.edito.vistanegocio.listeners;

import javax.print.attribute.standard.PDLOverrideSupported;
import tresenraya.Posicion;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoEntradaGui2;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import java.awt.event.ActionEvent;

public class EscuchadorTableroGui2 implements Escuchador {
  private ContenidoEntradaGui2 contenidoEntradaGui2;
  private TresEnRayaGui tresEnRayaGui;
  public EscuchadorTableroGui2(ContenidoEntradaGui2 contenidoEntradaGui2, TresEnRayaGui tresEnRayaGui) {
    this.contenidoEntradaGui2 = contenidoEntradaGui2;
    this.tresEnRayaGui = tresEnRayaGui;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    String fila = contenidoEntradaGui2.getDato123();
    String columna = contenidoEntradaGui2.getDatoABC();
    int valorPosicion = -1;
    if(!fila.isEmpty() && !columna.isEmpty()){
      char aux = columna.charAt(0);
      int fil = (aux % 65);
      int col = Integer.parseInt(fila) - 1;
      Posicion posicion = new Posicion(col, fil);
      valorPosicion = ((3 * posicion.fila) + posicion.columna);
    }
    tresEnRayaGui.marcarCasilla(valorPosicion);
    contenidoEntradaGui2.reset();
  }
}
