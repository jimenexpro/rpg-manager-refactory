package rpgmanager.model;

import rpgmanager.interfaces.Equipable;

public class Arquero extends Personaje implements Equipable{
    private int flechas;
    private int alcance;
    private String itemEquipado= "Arco basico";
    
    public Arquero(String nombre, int nivel){
        super(nombre,nivel,75 + nivel * 7);
        this.flechas=10+nivel*2;
        this.alcance=30;
    }
    @Override
    public void atacar(Personaje objetivo){
    if(flechas>0){
        System.out.println(nombre + " Lanza un proyectil!");
        int dano = 12 + nivel * 4;
        if(!itemEquipado.equals("Arco basico")){
            dano += 5;
        }
        objetivo.recibirDano(dano);
        flechas -= 1;
    }
    else{
        System.out.println(nombre + " no tiene flechas!");
    }
}
    @Override
    public String getTipoPersonaje(){
        return "Arquero";
    }
    public void recargarFlechas(int c){
        flechas+=c;
    }
    @Override
    public void equiparItem (String item){
        itemEquipado = item;
        System.out.println(nombre + " se equipó " + item);
    }
    @Override
    public String getItemEquipado() {
    return itemEquipado;
    }
    
}
