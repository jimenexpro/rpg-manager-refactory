package rpgmanager.factory;

import rpgmanager.model.Guerrero;
import rpgmanager.model.Personaje;

/**
 * Fábrica concreta que crea instancias de Guerrero.
 */
public class GuerreroFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre, int nivel) {
        return new Guerrero(nombre, nivel);
    }
}
