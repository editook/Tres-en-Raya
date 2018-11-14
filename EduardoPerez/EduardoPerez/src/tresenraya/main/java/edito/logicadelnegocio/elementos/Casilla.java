package tresenraya.main.java.edito.logicadelnegocio.elementos;

public class Casilla implements Comparable<Casilla>{
  private Ficha ficha;
  public Casilla(){
    ficha = Ficha.VACIO;
  }
  public Casilla(Ficha ficha){
    this.ficha = ficha;
  }
  public void setCasilla(Ficha ficha){
    this.ficha = ficha;
  }

  @Override
  public int compareTo(Casilla casilla) {
    return this.ficha.compareTo(casilla.ficha);
  }
}
