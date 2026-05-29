package rpgmanager.model;

import java.util.ArrayList;
import java.util.List;
import rpgmanager.GameManager;

/**
 * Batalla simple refactorizada.
 * Usa GameManager (Singleton) para crear los personajes con Factory Method.
 */
public class Batalla {
    public static void main(String[] args) {

        GameManager gm = GameManager.getInstance();
        gm.resetearPersonajes();
        gm.resetearTurnos();

        // Creación de personajes vía Factory (a través del Singleton)
        Personaje thorin  = gm.crearGuerrero("Thorin",  3);
        Personaje gandalf = gm.crearMago    ("Gandalf", 5);
        Personaje legolas = gm.crearArquero ("Legolas", 4);

        List<Personaje> heroes = new ArrayList<>();
        heroes.add(thorin);
        heroes.add(gandalf);
        heroes.add(legolas);

        // Enemigo también creado con Factory
        Personaje orco = gm.crearGuerrero("Orco", 100);

        System.out.println("\n=== Inicio de Batalla ===");
        gm.mostrarEstado();

        while (orco.estaVivo()) {
            System.out.println("\n--- Turno " + gm.getTurnoActual() + " ---");
            for (Personaje h : heroes) {
                if (orco.estaVivo()) {
                    h.atacar(orco);
                    System.out.println(orco);
                }
            }
            gm.incrementarTurno();
        }

        System.out.println("\nBatalla terminada en " + gm.getTurnoActual() + " turnos.");
    }
}
