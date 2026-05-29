package rpgmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rpgmanager.factory.ArqueroFactory;
import rpgmanager.factory.GuerreroFactory;
import rpgmanager.factory.MagoFactory;
import rpgmanager.factory.PersonajeFactory;
import rpgmanager.model.Personaje;

/**
 * Singleton que actúa como punto central de control del juego.
 * Administra el registro de personajes y las fábricas disponibles.
 *
 * Patrón Singleton: una única instancia accesible globalmente.
 * Patrón Factory Method: delega la creación de personajes a fábricas.
 */
public class GameManager {

    // ── Singleton ──────────────────────────────────────────────────────────
    private static GameManager instancia;

    private GameManager() {
        personajes = new ArrayList<>();
        registrarFabricasDefault();
    }

    public static GameManager getInstance() {
        if (instancia == null) {
            instancia = new GameManager();
        }
        return instancia;
    }

    // ── Estado global ──────────────────────────────────────────────────────
    private final List<Personaje> personajes;
    private int turnoActual = 1;

    // ── Fábricas registradas ───────────────────────────────────────────────
    private GuerreroFactory guerreroFactory;
    private MagoFactory     magoFactory;
    private ArqueroFactory  arqueroFactory;

    private void registrarFabricasDefault() {
        guerreroFactory = new GuerreroFactory();
        magoFactory     = new MagoFactory();
        arqueroFactory  = new ArqueroFactory();
    }

    // ── Factory Method: creación de personajes ─────────────────────────────

    public Personaje crearGuerrero(String nombre, int nivel) {
        Personaje p = guerreroFactory.crearPersonaje(nombre, nivel);
        personajes.add(p);
        System.out.println("[GameManager] Personaje registrado: " + p);
        return p;
    }

    public Personaje crearMago(String nombre, int nivel) {
        Personaje p = magoFactory.crearPersonaje(nombre, nivel);
        personajes.add(p);
        System.out.println("[GameManager] Personaje registrado: " + p);
        return p;
    }

    public Personaje crearArquero(String nombre, int nivel) {
        Personaje p = arqueroFactory.crearPersonaje(nombre, nivel);
        personajes.add(p);
        System.out.println("[GameManager] Personaje registrado: " + p);
        return p;
    }

    /**
     * Crea un personaje usando el tipo como String: "guerrero", "mago", "arquero".
     */
    public Personaje crearPersonaje(String tipo, String nombre, int nivel) {
        PersonajeFactory factory;
        switch (tipo.toLowerCase()) {
            case "guerrero": factory = guerreroFactory; break;
            case "mago":     factory = magoFactory;     break;
            case "arquero":  factory = arqueroFactory;  break;
            default: throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
        Personaje p = factory.crearPersonaje(nombre, nivel);
        personajes.add(p);
        System.out.println("[GameManager] Personaje registrado: " + p);
        return p;
    }

    // ── Gestión de estado ──────────────────────────────────────────────────

    public List<Personaje> getPersonajes() {
        return Collections.unmodifiableList(personajes);
    }

    public void eliminarPersonaje(Personaje p) {
        personajes.remove(p);
    }

    public void resetearPersonajes() {
        personajes.clear();
        System.out.println("[GameManager] Registro de personajes limpiado.");
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void incrementarTurno() {
        turnoActual++;
    }

    public void resetearTurnos() {
        turnoActual = 1;
    }

    public void mostrarEstado() {
        System.out.println("=== Estado del Juego (Turno " + turnoActual + ") ===");
        if (personajes.isEmpty()) {
            System.out.println("  (sin personajes registrados)");
        } else {
            for (Personaje p : personajes) {
                System.out.println("  " + p);
            }
        }
    }
}
