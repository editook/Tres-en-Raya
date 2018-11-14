package tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRaya;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRayaI;
import tresenraya.main.java.edito.jugadores.Jugador;
import tresenraya.main.java.edito.persistencia.acceso.Conexion;
import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloSerializable;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoTablero;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoTableroGui1;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.Notificador;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.NotificadorI;
import tresenraya.main.java.edito.vistanegocio.menus.MenuTresEnRaya;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TresEnRayaGui1 extends Frame implements TresEnRayaGui{
  private TresEnRaya tresEnRaya;
  private Jugador jugadorX;
  private ContenidoTablero contenidoTablero;
  private NotificadorI notificador;
  private Random random;
  private TablaResultadosGui tablaResultadosGui;
  private ModeloSerializable listaJugadores;
  private static final Timer TIEMPO_ESPERA = new Timer();
  private TimerTask tarea;
  public TresEnRayaGui1(){
    tresEnRaya = new TresEnRayaI();
    contenidoTablero = new ContenidoTableroGui1(this);
    notificador = new Notificador(this);
    inicomponents();
    componentesSegundarios();
  }

  private void componentesSegundarios() {
    crearMenu();
    random = new Random();
    jugadorX = new Jugador("anonimo");
    tarea =null;
    tablaResultadosGui = new TablaResultadosGui(this);
    listaJugadores = Conexion.getInputStream();
    listaJugadores.agregarJugador("anonimo");
    tablaResultadosGui.refrescarPuntuacion();
  }

  private void inicomponents() {
    setTitle("Tic Tac Toe");
    setLayout(new GridLayout(3, 3, 1, 1));
    setResizable(false);
    for (int indice = 0; indice < 9; indice++) {
      add(contenidoTablero.agregarContenido(indice));
    }
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent evt) {
            salir();
          }
        });
    pack();
  }

  private void crearMenu() {
    MenuTresEnRaya menuTresEnRaya = new MenuTresEnRaya(this);
    menuTresEnRaya.addMenu("File");
    menuTresEnRaya.addItem("File", "New Game");
    menuTresEnRaya.addItem("File", "Exit");
    menuTresEnRaya.addMenu("Edit");
    menuTresEnRaya.addItem("Edit", "New Player Cross");
    menuTresEnRaya.addItem("Edit", "New Player Circle",false);//visibility
    menuTresEnRaya.addMenu("View");
    menuTresEnRaya.addItem("View", "Results Table");
    menuTresEnRaya.addMenu("Window");
    menuTresEnRaya.addItem("Window", "Window Clasic View",false);//visibility
    menuTresEnRaya.addItem("Window", "Window Alternative View");
    menuTresEnRaya.addItem("Window", "Window Player vs Player");
    menuTresEnRaya.addMenu("Help");
    menuTresEnRaya.addItem("Help", "About");
    this.setMenuBar(menuTresEnRaya);
  }

  @Override
  public void marcarCasilla(int indice) {
    if (realizarJugada(indice) && !tresEnRaya.terminado()) {
      jugadorX.realizaJugada(indice / 3, indice % 3, tresEnRaya);
      contenidoTablero.cambiarText(indice, "X");
      verificarJuego();
      nextMove();
    } else {
      notificador.alertaErrorPosicion();
    }
  }
  private void verificarJuego(){
    if(tresEnRaya.terminado()) {
      if(tresEnRaya.obtenerGanador()!='\0'){
        listaJugadores.incrementarGanada(getGanador());
        tablaResultadosGui.refrescarPuntuacion();
      }
      juegoTerminado(getGanador());
    }
  }
  private String getGanador(){
    char ganador = tresEnRaya.obtenerGanador();
    return ganador=='o'?"maquina":ganador=='x'?jugadorX.getNombre():"";
  }
  private void juegoTerminado(String nombre){
    if (nombre.isEmpty()) {
      notificador.notificarEmpate();
    } else {
      notificador.notificarGanador(nombre);
    }
  }

  private void nextMove(){
    turnoMaquina();
    TIEMPO_ESPERA.schedule(tarea,10);
    tarea=null;
  }
  private void turnoMaquina(){
    tarea = new TimerTask() {
      @Override
      public void run() {
        if (!tresEnRaya.terminado()) {
          int indice = 4;
          while(!realizarJugada(indice)){
            indice = randomMove();
          }
          tresEnRaya.hacerJugadaO(indice / 3, indice % 3);
          contenidoTablero.cambiarText(indice, "O");
          verificarJuego();
        }
      }
    };
  }
  private int randomMove() {
    return (random.nextInt(9));
  }

  private boolean realizarJugada(int indice){
    return contenidoTablero.marcaVacia(indice);
  }
  @Override
  public void nuevoJugadorX() {
    String nombre =notificador.nuevoNombreJugador(Ficha.CRUZ);
    nombre = nombre==null? jugadorX.getNombre() : nombre;
    jugadorX = new Jugador(nombre);
    listaJugadores.agregarJugador(nombre);
    tablaResultadosGui.refrescarPuntuacion();
    nuevoJuego();
  }
  @Override
  public void notificarAbout() {
    notificador.notificarAbout("D3M0L1SH3R");
  }
  @Override
  public void nuevoJuego() {
    contenidoTablero.reset();
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
  }
  @Deprecated
  public void nuevoJugadorO() {}

  @Override
  public void visibility(int estado) {
    if(estado==0)
      this.setVisible(true);
    else
      this.dispose();
  }

  @Override
  public String toString(){
    return jugadorX.getNombre();
  }
}