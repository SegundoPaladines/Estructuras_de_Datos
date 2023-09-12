public class Nota {
    double nota;
    String nombre;
    Nota siguiente;
    public Nota(double nota, String nombre){
        this.nombre=nombre;
        this.nota=nota;
        this.siguiente=null;
    }
}
