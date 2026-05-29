package rpgmanager.factory;

import rpgmanager.model.Arquero;
import rpgmanager.model.Personaje;

/**
 * Fábrica concreta que crea instancias de Arquero.
 */
public class ArqueroFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre, int nivel) {
        return new Arquero(nombre, nivel);
    }
}
