package tresenraya.main.java.edito.jugadores.estrategia;

import estrategia.Estrategia;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import tresenraya.Posicion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
class ReflexionEstrategica {

  private Method metodoHacerJugada, metodoRegistrarJugada, reiniciarJuego;
  private static final String HACER_JUGADA = "hacerJugada";
  private static final String REGISTRAR_JUGADA_CONTRARIA = "registrarJugadaContraria";
  private static final String REINCIAR_JUEGO = "reiniciarJuego";
  private Estrategia instancia;

  ReflexionEstrategica(String[] archivo) {
    construirHabilidad(archivo);
  }

  private void construirHabilidad(String[] file) {
    file[1] = file[1].replace("estrategia/","");
    try {
      URLClassLoader loader;
      System.out.println(file[1]+file[0]);
      loader = new URLClassLoader(new URL[]{new URL(file[1])});
      Class claseExterna = loader.loadClass("estrategia."+file[0]);
      instancia =(Estrategia) claseExterna.newInstance();
      metodoHacerJugada = claseExterna.getMethod(HACER_JUGADA);
      metodoRegistrarJugada = claseExterna.getMethod(REGISTRAR_JUGADA_CONTRARIA, Posicion.class);
      reiniciarJuego = claseExterna.getMethod(REINCIAR_JUEGO);

    } catch (  ClassNotFoundException | MalformedURLException |
        IllegalAccessException | InstantiationException | NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  Posicion ejecutarMetodoHacerJugada() {
    Posicion posicion = null;
    try {
      posicion = (Posicion)metodoHacerJugada.invoke(instancia);
    } catch ( IllegalAccessException |
        InvocationTargetException e) {
      e.printStackTrace();
    }
    return posicion;
  }

  void metodoRegistrarJugada(Posicion posicion) {
    try {
      metodoRegistrarJugada.invoke(instancia, posicion);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  public void reiniciarJuego() {
    try {
      reiniciarJuego.invoke(instancia);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

  }
}
