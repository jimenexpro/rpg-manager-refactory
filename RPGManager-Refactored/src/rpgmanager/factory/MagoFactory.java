package rpgmanager.factory;

import rpgmanager.model.Mago;
import rpgmanager.model.Personaje;

/**
 * Fábrica concreta que crea instancias de Mago.
 */
public class MagoFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre, int nivel) {
        return new Mago(nombre, nivel);
    }
}
