public class Estudiante {
    String codigo;
    String nombre;
    double promedio;

    public Estudiante (String nombre, String codigo){
        this.codigo=codigo;
        this.nombre=nombre;
        this.promedio=-1;
    }
    public Estudiante (String nombre, String codigo, double promedio){
        this.codigo=codigo;
        this.nombre=nombre;
        this.promedio=promedio;
    }
}
