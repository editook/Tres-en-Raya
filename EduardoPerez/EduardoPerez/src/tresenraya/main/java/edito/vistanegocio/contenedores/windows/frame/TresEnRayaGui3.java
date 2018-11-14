package tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRaya;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRayaI;
import tresenraya.main.java.edito.jugadores.Jugador;
import tresenraya.main.java.edito.persistencia.acceso.Conexion;
import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloSerializable;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoEntradaGui3;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoTableroGui2;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.Notificador;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.NotificadorI;
import tresenraya.main.java.edito.vistanegocio.menus.MenuTresEnRaya;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TresEnRayaGui3 extends Frame implements TresEnRayaGui {

  private TresEnRaya tresEnRaya;
  private ContenidoTableroGui2 panelTablero;
  private ContenidoEntradaGui3 contenidoEntradaGui3;
  private NotificadorI notificador;
  private TablaResultadosGui tablaResultadosGui;
  private ModeloSerializable listaJugadores;
  private Jugador jugadorX,jugadorO;
  private boolean turno;

  public TresEnRayaGui3(){
    tresEnRaya = new TresEnRayaI();
    inicomponents();
    panelTablero = new ContenidoTableroGui2();
    add(panelTablero);
    contenidoEntradaGui3 = new ContenidoEntradaGui3(this);
    add(contenidoEntradaGui3);
    notificador = new Notificador(this);
    tablaResultadosGui = new TablaResultadosGui(this);
    listaJugadores = Conexion.getInputStream();
    turno = true;
  }
  private void inicomponents() {
    setLayout(null);
    setTitle("Tic Tac Toe");
    setSize(500, 520);
    setResizable(false);
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent evt) {
            salir();
          }
        });
    crearMenuJuego();
  }

  private void crearMenuJuego() {
    MenuTresEnRaya menuTresEnRaya = new MenuTresEnRaya(this);
    menuTresEnRaya.addMenu("File");
    menuTresEnRaya.addItem("File", "New Game");
    menuTresEnRaya.addItem("File", "Exit");
    menuTresEnRaya.addMenu("Edit");
    menuTresEnRaya.addItem("Edit", "New Player Cross");
    menuTresEnRaya.addItem("Edit", "New Player Circle");
    menuTresEnRaya.addMenu("View");
    menuTresEnRaya.addItem("View", "Results Table");
    menuTresEnRaya.addMenu("Window");
    menuTresEnRaya.addItem("Window", "Window Clasic View");
    menuTresEnRaya.addItem("Window", "Window Alternative View");
    menuTresEnRaya.addItem("Window", "Window Player vs Player",false);//disable
    menuTresEnRaya.addMenu("Help");
    menuTresEnRaya.addItem("Help", "About");
    this.setMenuBar(menuTresEnRaya);
  }
  @Override
  public void marcarCasilla(int indice) {
    if(turno){
        realizarJugada(jugadorX,"X");
      }
      else {
        realizarJugada(jugadorO,"O");
      }
      verificarJuego();
      turno=!turno;
  }
  private void realizarJugada(Jugador jugador,String marca){
    while (true){
      int indice = jugador.realizaJugada();
      if(panelTablero.marcaVacia(indice)){
        if(tresEnRaya.esTurnoX()){

          tresEnRaya.hacerJugadaX(indice / 3, indice % 3);
          jugadorO.marcarJugada(indice);
        }
        else {
          tresEnRaya.hacerJugadaO(indice / 3, indice % 3);
          jugadorX.marcarJugada(indice);
        }
        jugador.marcarJugada(indice);
        panelTablero.cambiarText(indice, marca);
        marca = marca.equals("X")?"O":"X";
        contenidoEntradaGui3.marcado(marca);
        break;
      }
    }
  }
  private String getGanador(){
    char ganador = tresEnRaya.obtenerGanador();
    return ganador=='o'?jugadorO.getNombre():ganador=='x'?jugadorX.getNombre():"";
  }
  private void verificarJuego(){
    if(tresEnRaya.terminado()) {
      String nombreGan = getGanador();
      if(!nombreGan.isEmpty()){
        listaJugadores.incrementarGanada(nombreGan);
        tablaResultadosGui.refrescarPuntuacion();
      }
      juegoTerminado(nombreGan);
    }
  }

  @Override
  public void nuevoJugadorX() {
    String[]datos = notificador.adquirirEstrategia(Ficha.CRUZ);
    jugadorX = new Jugador(datos[2]);
    jugadorX.setIEstrategia(datos);
    listaJugadores.agregarJugador(jugadorX.getNombre());
    tablaResultadosGui.refrescarPuntuacion();
  }
  private void juegoTerminado(String nombre){
    if (nombre.isEmpty()) {
      notificador.notificarEmpate();
    } else {
      notificador.notificarGanador(nombre);
    }
    jugadorO.reiniciarEstrategiaJuego();
    jugadorX.reiniciarEstrategiaJuego();
  }
  @Override
  public void notificarAbout() {
    notificador.notificarAbout("edito.com");
  }

  @Override
  public void nuevoJuego() {
    panelTablero.reset();
    tresEnRaya.reiniciar();
  }

  @Override
  public void salir() {
    System.exit(0);
  }

  @Override
  public void mostrarTablero() {
    tablaResultadosGui.setVisible(true);
    tablaResultadosGui.refrescarPuntuacion();
    tablaResultadosGui.repaint();
  }

  @Override
  public void nuevoJugadorO() {
    String[]datos = notificador.adquirirEstrategia(Ficha.CIRCULO);
    jugadorO = new Jugador(datos[2]);
    jugadorO.setIEstrategia(datos);
    listaJugadores.agregarJugador(jugadorO.getNombre());
    tablaResultadosGui.refrescarPuntuacion();
  }

  @Override
  public void visibility(int estado) {
    if(estado==0)
      this.setVisible(true);
    else
      this.dispose();
  }
  @Override
  public String toString(){
    return "Sin Asignar"+" "+"Sin Asignar";
  }
}
