package rpgmanager.model;

import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;

public class Mago extends Personaje implements Habilidoso, Sanador {
    private int mana;
    private int manaMax;
    
    public Mago(String nombre, int nivel){
        super(nombre,nivel,60 + nivel * 5);
        this.mana=80 + nivel * 10;
        this.manaMax=80 + nivel * 10;
    }
    @Override
    public void atacar(Personaje objetivo){
        if(mana >=20){
            System.out.println(nombre + " Lanza un hechizo!");
            objetivo.recibirDano(25 + nivel * 5);
            mana=mana-20;
        }
        else{
            System.out.println("Mana insuficiente");
        }
    }
    @Override
    public String getTipoPersonaje(){
        return "Mago";
    }
    public void recuperarMana(int c){
        mana=Math.min(manaMax,mana+c);
    }
    @Override
    public void usarHabilidadEspecial(Personaje objetivo){
        if(mana >=20){
            System.out.println(nombre + " Uso su" + getNombreHabilidad());
            objetivo.recibirDano(40);
            mana=mana-20;
        }
        else{
            System.out.println("Mana insuficiente");
        }
    }
    @Override
    public int getCostoHabilidad(){
        return 20;
    }
    @Override
    public String getNombreHabilidad(){
        return "Bola de fuego";
    }
    @Override
    public void sanar (Personaje objetivo){
        objetivo.puntosVida = Math.min(objetivo.puntosVidaMax, objetivo.puntosVida + 25);
        System.out.println(nombre + " sanó a " + objetivo.nombre + " por 25 HP");
    }
    @Override
    public int getPotenciaSanacion(){
        return 25;
    }
    
}
