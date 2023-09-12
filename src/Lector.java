import java.io.*;
import java.util.ArrayList;

public class Lector {

    public Lector(){}

    public void LeerEstudiantes(ListaEstudiantes lista) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File ("estudiantes.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            String linea;
            while((linea=br.readLine())!=null){
                String [] subcadenas = linea.split(":");
                lista.AgregarUltimo(new Estudiante(subcadenas[0], subcadenas[1], Double.parseDouble(subcadenas[2])));
            }
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el archivo, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
     }

     public ArrayList<ListaNotas> LeerNotas() {
      ArrayList<ListaNotas> curso = new ArrayList<ListaNotas>();
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      try {
          archivo = new File ("notas.txt");
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
          
          String linea;
          while((linea=br.readLine())!=null){
              String [] subcadenas = linea.split(";");
              ListaNotas ltmp = new ListaNotas(subcadenas[0]);
              for(int i=1; i<subcadenas.length; i++){
                  String [] sbtmp = subcadenas[i].split(":");
                  Nota ntmp = new Nota(Double.parseDouble(sbtmp[1]),sbtmp[0]);
                  ltmp.AgregarUltimo(ntmp);
              }
              if(!existeListaNotas(curso, ltmp)){curso.add(ltmp);}
          }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el archivo, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return curso;
   }

   public boolean existeListaNotas(ArrayList<ListaNotas> curso, ListaNotas lista) {
      boolean existe = false;
      if(curso.size()>0){
         for (ListaNotas lista_notas : curso) {
            if(lista_notas.condigo_est.equalsIgnoreCase(lista.condigo_est)){existe=true;}
         }
      }
      return existe;
   }
}
