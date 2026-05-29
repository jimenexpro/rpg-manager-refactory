public class FabricaInventario {

    public static Item crear(String tipo, String nombre, int valor) {
        switch (tipo.toLowerCase()) {
            case "arma":
                return new Arma(nombre, valor);      
            case "pocion":
                return new Pocion(nombre, valor);
            case "armadura":
                return new Armadura(nombre, valor);
            default:
                throw new IllegalArgumentException(
                    "Tipo de item no válido: " + tipo);               
        }
    }
}