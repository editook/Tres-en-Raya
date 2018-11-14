package tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;

public interface NotificadorI {
  void alertaErrorPosicion();

  void notificarGanador(String nombreGanador);

  void notificarEmpate();

  void notificarAbout(String autor);

  String nuevoNombreJugador(Ficha ficha);

  String[] adquirirEstrategia(Ficha cruz);

}
