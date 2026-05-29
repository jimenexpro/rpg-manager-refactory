package rpgmanager.factory;

import rpgmanager.model.Personaje;

/**
 * Factory Method abstracta para crear personajes.
 * Cada subclase decide qué tipo de Personaje instanciar.
 */
public abstract class PersonajeFactory {
    public abstract Personaje crearPersonaje(String nombre, int nivel);
}
