package rpgmanager.model;

public abstract class Personaje {
    protected String nombre;
    protected int nivel;
    protected int puntosVida;
    protected int puntosVidaMax;
    
    public Personaje(String nombre, int nivel, int puntosVidaMax){
        this.nombre=nombre;
        this.nivel=nivel;
        this.puntosVidaMax=puntosVidaMax;
        this.puntosVida=puntosVidaMax;
    }
    public void recibirDano(int d){
        puntosVida=Math.max(0,puntosVida-d);
    }
    public boolean estaVivo(){
        boolean estado;
        if (puntosVida>0){
            estado=true;
            return estado;   
        }else{
            estado=false;
            return estado;
        }
    }
    public abstract void atacar(Personaje objetivo);
    public abstract String getTipoPersonaje();
    
    @Override
    public String toString(){
        return "[" + getTipoPersonaje() + "] " + nombre + "  Nv." + nivel + " HP: " + puntosVida + "/" + puntosVidaMax;
    }
    
}
