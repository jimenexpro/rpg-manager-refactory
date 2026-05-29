package rpgmanager.model;
import rpgmanager.interfaces.Equipable;
import rpgmanager.interfaces.Habilidoso;

public class Guerrero extends Personaje implements Habilidoso, Equipable{
    
    private int fuerza;
    private int defensa;
    private String itemEquipado = "Sin equipo";
    private int costoHabilidad = 30;
    
    public Guerrero(String nombre, int nivel){
        super(nombre,nivel,100+nivel*10);
        this.fuerza=15+nivel*3;
        this.defensa=10+nivel*2;
    }
    @Override
    public void atacar(Personaje objetivo){
        System.out.println(nombre + " ataca con su espada!");
        objetivo.recibirDano(fuerza);
    }
    @Override
    public String getTipoPersonaje(){
        return "Guerrero";
    }
 
    public void usarEscudo() {
        System.out.println(nombre + " bloquea con defensa " + defensa);
    }
    @Override
    public void usarHabilidadEspecial(Personaje objetivo){
        System.out.println(nombre + " Uso su habilidad " + getNombreHabilidad());
        objetivo.recibirDano(50);
    }
    @Override
    public int getCostoHabilidad(){
        return costoHabilidad;
    }
    @Override
    public String getNombreHabilidad(){
        return "Golpe Devastador";
    }
    @Override
    public void equiparItem (String item){
        itemEquipado = item;
        System.out.println(nombre +  " se equipó " + item);
    }
    @Override
    public String getItemEquipado() {
    return itemEquipado;
    }
}
