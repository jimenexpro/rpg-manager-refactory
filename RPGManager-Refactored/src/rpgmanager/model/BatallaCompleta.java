package rpgmanager.model;

import java.util.ArrayList;
import java.util.List;
import rpgmanager.GameManager;
import rpgmanager.interfaces.Equipable;
import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;

/**
 * Batalla completa refactorizada.
 * Usa GameManager (Singleton) para crear y gestionar personajes con Factory Method.
 */
public class BatallaCompleta {
    public static void main(String[] args) {

        GameManager gm = GameManager.getInstance();
        gm.resetearPersonajes();
        gm.resetearTurnos();

        // ── Fase 1: Creación vía Factory (Singleton delega a fábricas) ──────
        Guerrero thorin  = (Guerrero) gm.crearGuerrero("Thorin",  3);
        Mago     gandalf = (Mago)     gm.crearMago    ("Gandalf", 5);
        Arquero  legolas = (Arquero)  gm.crearArquero ("Legolas", 4);
        Personaje orco   =            gm.crearGuerrero("Orco",    10);

        // ── Fase 2: Equipamiento ─────────────────────────────────────────────
        thorin.equiparItem("Nightblade");
        legolas.equiparItem("Bee Bow");

        List<Personaje> heroes = new ArrayList<>();
        heroes.add(thorin);
        heroes.add(gandalf);
        heroes.add(legolas);

        System.out.println("\n=== Inicio de Batalla Completa ===");
        gm.mostrarEstado();

        // ── Fase 3: Bucle de batalla ─────────────────────────────────────────
        while (orco.estaVivo()) {
            System.out.println("\n--- Turno " + gm.getTurnoActual() + " ---");
            for (Personaje h : heroes) {
                if (orco.estaVivo()) {
                    // En el turno 2 los Habilidosos usan su habilidad especial
                    if (gm.getTurnoActual() == 2 && h instanceof Habilidoso) {
                        ((Habilidoso) h).usarHabilidadEspecial(orco);
                    }
                    h.atacar(orco);
                    System.out.println(orco);
                }
            }
            gm.incrementarTurno();
        }

        System.out.println("\n¡Orco ha sido derrotado en " + gm.getTurnoActual() + " turnos!");

        // ── Fase 4: Sanación del héroe más débil ─────────────────────────────
        Personaje masDebil = heroes.get(0);
        for (Personaje h : heroes) {
            if (h.puntosVida < masDebil.puntosVida) {
                masDebil = h;
            }
        }

        System.out.println("\n=== Fase de Sanación ===");
        System.out.println("Héroe más débil: " + masDebil);
        for (Personaje h : heroes) {
            if (h instanceof Sanador) {
                ((Sanador) h).sanar(masDebil);
            }
        }

        System.out.println("\n=== Estado Final ===");
        gm.mostrarEstado();
    }
}
