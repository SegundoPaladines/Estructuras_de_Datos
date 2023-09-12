import java.io.*;
import java.util.ArrayList;

public class Escritor
{
    public Escritor(){}

    public void EscribirEstudiantes(ListaEstudiantes lista)
    {
        FileWriter archivo = null;
        PrintWriter pw = null;
        try
        {
            if(lista.tamano>0){

                //abre el archivo para comenzar a escribir
                archivo = new FileWriter("estudiantes.txt");
                pw = new PrintWriter(archivo);
    
                Nodo tmp = lista.primero;
                //escribe linea por linea
                while(tmp!=null){
                    pw.println(tmp.estudiante.nombre+":"+tmp.estudiante.codigo+":"+tmp.estudiante.promedio);
                    tmp=tmp.siguiente;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           try {
           if (null != archivo)
              archivo.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    public void EscribirNotas(ArrayList<ListaNotas> lista)
    {
        FileWriter archivo = null;
        PrintWriter pw = null;
        try
        {
            if(lista.size()>0){

                //abre el archivo para comenzar a escribir
                archivo = new FileWriter("notas.txt");
                pw = new PrintWriter(archivo);
                
                //escribe linea por linea
                for (ListaNotas listanotas : lista) {
                    pw.print(listanotas.condigo_est+";");
                    Nota tmp = listanotas.primera;
                    while(tmp!=null){
                        pw.print(tmp.nombre+":"+tmp.nota+";");
                        tmp=tmp.siguiente;
                    }
                    pw.print("\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           try {
           if (null != archivo)
              archivo.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
