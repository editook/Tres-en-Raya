package tresenraya.main.java.edito.vistanegocio.menus;

import tresenraya.main.java.edito.vistanegocio.contenedores.windows.frame.TresEnRayaGui;
import tresenraya.main.java.edito.vistanegocio.listeners.Escuchador;
import tresenraya.main.java.edito.vistanegocio.listeners.EscuchadorMenu;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class MenuTresEnRaya extends MenuBar {
  private Escuchador escuchador;
  private int elementos;
  public MenuTresEnRaya(TresEnRayaGui tresEnRayaGui) {
    escuchador = new EscuchadorMenu(tresEnRayaGui);
    elementos = 0;
  }
  public void addMenu(String nombre){
    Menu menu = new Menu(nombre);
    add(menu);
    elementos++;
  }
  public void addItem(String nombreMenu, String nombreItem){
    Menu menu = getMenu(nombreMenu);
    if(menu!=null){
      menu.add(crearMenuItem(nombreItem));
    }
  }
  private Menu getMenu(String nombreMenu) {
    Menu menu = null;
    for (int indice = 0; indice < elementos; indice++) {
      menu = getMenu(indice);
      if (menu.getName().equals(nombreMenu)) {
        break;
      }
    }
    return menu;
  }
  private MenuItem crearMenuItem(String nombreItem) {
    MenuItem menuItem = new MenuItem();
    menuItem.setLabel(nombreItem);
    menuItem.setActionCommand(nombreItem);
    menuItem.addActionListener(escuchador);
    return menuItem;
  }
  public void addItem(String nombreMenu, String nombreItem, boolean estado) {
    Menu menu = getMenu(nombreMenu);
    if (menu != null) {
      MenuItem menuItem = crearMenuItem(nombreItem);
      menuItem.setEnabled(estado);
      menu.add(menuItem);
    }
  }
}
