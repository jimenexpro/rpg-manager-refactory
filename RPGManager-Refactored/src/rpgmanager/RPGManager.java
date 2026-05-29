package rpgmanager;

/**
 * Punto de entrada principal.
 * Demuestra el uso del Singleton GameManager con Factory Method.
 */
public class RPGManager {
    public static void main(String[] args) {

        // Una sola instancia de GameManager en toda la app
        GameManager gm = GameManager.getInstance();

        // Creación de personajes vía Factory Method (sin usar "new" directamente)
        gm.crearGuerrero("Thorin",  3);
        gm.crearMago    ("Gandalf", 5);
        gm.crearArquero ("Legolas", 4);

        // También se puede crear con tipo como String
        gm.crearPersonaje("guerrero", "Aragorn", 7);

        // El GameManager tiene el estado global
        gm.mostrarEstado();

        // Verificamos que siempre es la misma instancia
        GameManager gm2 = GameManager.getInstance();
        System.out.println("\n¿Misma instancia? " + (gm == gm2)); // true
    }
}
