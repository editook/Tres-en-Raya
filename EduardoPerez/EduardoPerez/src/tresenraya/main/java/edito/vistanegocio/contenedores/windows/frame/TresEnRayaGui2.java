package tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame;

import tresenraya.main.java.edito.logicadelnegocio.elementos.Ficha;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRaya;
import tresenraya.main.java.edito.logicadelnegocio.juego.TresEnRayaI;
import tresenraya.main.java.edito.jugadores.Jugador;
import tresenraya.main.java.edito.persistencia.acceso.Conexion;
import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloSerializable;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoEntradaGui2;
import tresenraya.main.java.edito.vistanegocio.contenedores.panels.ContenidoTableroGui2;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.Notificador;
import tresenraya.main.java.edito.vistanegocio.contenedores.windows.dialogos.NotificadorI;
import tresenraya.main.java.edito.vistanegocio.menus.MenuTresEnRaya;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class TresEnRayaGui2 extends Frame implements TresEnRayaGui {

  private TresEnRaya tresEnRaya;
  private ContenidoTableroGui2 panelTablero;
  private ContenidoEntradaGui2 contenidoEntradaGui2;
  private NotificadorI notificador;
  private static final Timer TIEMPO_ESPERA = new Timer();
  private TimerTask tarea;
  private TablaResultadosGui tablaResultadosGui;
  private ModeloSerializable listaJugadores;
  private Jugador jugadorX;
  private JLabel logJugadores;
  private Random random;
  public TresEnRayaGui2(){

    tresEnRaya = new TresEnRayaI();
    inicomponents();
    panelTablero = new ContenidoTableroGui2();
    add(panelTablero);
    contenidoEntradaGui2 = new ContenidoEntradaGui2(this);
    add(contenidoEntradaGui2);
    componentesSecundarios();
  }

  private void componentesSecundarios() {
    crearMenuJuego();
    random = new Random();
    notificador = new Notificador(this);
    tarea =null;
    jugadorX = new Jugador("anonimo");
    tablaResultadosGui = new TablaResultadosGui(this);
    listaJugadores = Conexion.getInputStream();
    listaJugadores.agregarJugador("anonimo");
    crearApuntadoresLog();
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
          panelTablero.cambiarText(indice, "O");
          verificarJuego();
        }
      }
    };
  }

  @Override
  public void marcarCasilla(int indice) {
    if (realizarJugada(indice) && !tresEnRaya.terminado()) {
      panelTablero.cambiarText(indice, "X");
      tresEnRaya.hacerJugadaX(indice / 3, indice % 3);
      verificarJuego();
      nextMove();
    } else {
      notificador.alertaErrorPosicion();
    }

  }
  private void crearMenuJuego() {
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
    menuTresEnRaya.addItem("Window", "Window Clasic View");
    menuTresEnRaya.addItem("Window", "Window Alternative View",false);//disable
    menuTresEnRaya.addItem("Window", "Window Player vs Player");
    menuTresEnRaya.addMenu("Help");
    menuTresEnRaya.addItem("Help", "About");
    this.setMenuBar(menuTresEnRaya);
  }
  private void crearApuntadoresLog() {
    int posY = 140, posX = 40;
    String[] textoLetras = new String[] {"1", "2", "3"};
    JLabel contexto;
    for (int i = 0; i < 3; i++) {
      contexto = new JLabel(textoLetras[i]);
      contexto.setBounds(posX, posY, 30, 30);
      add(contexto);
      posY += 100;
    }
    posX = 120;
    posY = 70;
    textoLetras = new String[] {"A", "B", "C"};
    for (int i = 0; i < 3; i++) {
      contexto = new JLabel(textoLetras[i]);
      contexto.setBounds(posX, posY, 30, 30);
      add(contexto);
      posX += 100;
    }
    logJugadores = new JLabel();
    logJugadores.setBounds(70, 470, 300, 30);
    add(logJugadores);
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
    notificador.notificarAbout("edito.com");
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
    TIEMPO_ESPERA.schedule(tarea,2000);
    tarea=null;
  }
  @Override
  public void nuevoJuego() {
    panelTablero.reset();
    tresEnRaya.reiniciar();
  }
  private int randomMove() {
    return (random.nextInt(9));
  }

  private boolean realizarJugada(int indice){
    if(indice>=0){
      return panelTablero.marcaVacia(indice);
    }
    return false;
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
