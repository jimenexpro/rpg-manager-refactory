public class Paladin extends Personaje {

    protected void atacar(String objetivo) {
        System.out.println("  " + "Paladín golpea a " + objetivo + " con su arma sagrada.");
    }

    @Override
    protected void aplicar_efecto_especial() {
        System.out.println("  Paladín bendice al equipo: +20 HP para todos.");
    }
}
