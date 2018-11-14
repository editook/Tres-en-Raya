package tresenraya.main.java.edito.persistencia.acceso;

import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloListaJugadores;
import tresenraya.main.java.edito.persistencia.modeloserializable.ModeloSerializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Conexion {
  private static final String DIRECCION = "database/registro.obj";

  public static ModeloSerializable getInputStream() {
    ModeloSerializable listaJugadores = new ModeloListaJugadores();
    try {
      ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DIRECCION));
      String str = (String) inputStream.readObject();
      listaJugadores = (ModeloSerializable) inputStream.readObject();
      inputStream.close();
    } catch (IOException | ClassNotFoundException ignored) {
    }
    return listaJugadores;
  }

  public static void setOutputStream(ModeloSerializable listaJugadores) {
    ObjectOutputStream outputStream;
    try {
      outputStream = new ObjectOutputStream(new FileOutputStream(DIRECCION));
      outputStream.writeObject("");
      outputStream.writeObject(listaJugadores);
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
