import java.util.ArrayList;

public class ListaEstudiantes{
    Nodo primero;
    int tamano;

    public ListaEstudiantes(){
        this.primero=null;
        tamano=0;
    }

    public boolean esVacia(){
        return this.primero == null;
    }

    public int Size(){return this.tamano;}

    public boolean AgregarUltimo(Estudiante estudiante){
        boolean agregado=false;
        Nodo nodo = new Nodo(estudiante);
        if(esVacia()){
            primero=nodo;
            agregado=true;
        }else{
            if(BuscarCod(estudiante.codigo)==null){
                Nodo tmp = primero;
                while(tmp.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                tmp.siguiente=nodo;
                agregado=true;
            }else{agregado=false;}
        }
        tamano++;
        return agregado;
    }

    public boolean AgregarInicio(Estudiante estudiante){
        Nodo nodo = new Nodo(estudiante);
        boolean agregado=false;
        if(esVacia()){
            primero=nodo;
            agregado=true;
        }else{
            if(BuscarCod(estudiante.codigo)==null){
                nodo.siguiente=primero;
                primero=nodo;
                agregado=true;
            }else{agregado=false;}
        }
        tamano++;
        return agregado;
    }

    public boolean AgregarenPos(Estudiante estudiante, int pos){
        boolean agregado=false;
        Nodo nodo = new Nodo(estudiante);
        if(((tamano+1)<pos) || (pos<1)){
            agregado = false;
        }else if(pos==(tamano+1)){
            agregado = AgregarUltimo(estudiante);
        }else if(pos==1){
            agregado = AgregarInicio(estudiante);
        }else{
            if(BuscarCod(estudiante.codigo)==null){
                int count = 1;
                Nodo tmp = primero;
                while(count<(pos-1)){
                    count++;
                    tmp=tmp.siguiente;
                }
                nodo.siguiente=tmp.siguiente;
                tmp.siguiente=nodo;
                tamano++;
                agregado=true;
            }else{agregado=false;}
        }
        return agregado;
    }

    public Estudiante QuitarUltimo(){
        Estudiante quitado = null;
        if(esVacia()){
            quitado=null;
        }else{
            if(primero.siguiente!=null){
                Nodo tmp = primero;
                while(tmp.siguiente.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                quitado = tmp.siguiente.estudiante;
                tmp.siguiente=null;
                tamano--;
            }else{quitado = QuitarInicio();}
        }
        return quitado;
    }

    public Estudiante QuitarInicio(){
        Estudiante quitado = null;
        if(esVacia()){
            quitado = null;
        }else{
            quitado = primero.estudiante;
            primero=primero.siguiente;
            tamano--;
        }
        return quitado;
    }

    public Estudiante QuitarPos(int pos){
        Estudiante quitado = null;
        if((tamano<pos) || (pos<1)){
            quitado = null;
        }else if(pos==tamano){
            quitado=QuitarUltimo();
        }else if(pos==1){
            quitado=QuitarInicio();
        }else{
            int count = 2;
            Nodo tmp = primero;
            while(count < pos){
                count++;
                tmp=tmp.siguiente;
            }
            quitado = tmp.siguiente.estudiante;
            tmp.siguiente = tmp.siguiente.siguiente;
            tamano--;
        }
        return quitado;
    }

    public Estudiante QuitarCod(String cod){
        Estudiante est = null;
        Nodo tmp = primero;
        boolean encontado = false;
        int count = 1;
        while((tmp!=null)&&(!encontado)){
            if(cod.equalsIgnoreCase(tmp.estudiante.codigo)){
                est = tmp.estudiante;
                QuitarPos(count);
                encontado=true;
            }
            tmp=tmp.siguiente;
            count++;
        }
        return est;
    }

    public Estudiante BuscarPos(int pos){
        Estudiante est = null;
        if((tamano<pos) || (pos<1)){
            est = null;
        }else if(pos==tamano){
            Nodo tmp = primero;
            while(tmp.siguiente.siguiente!=null){
                tmp=tmp.siguiente;
            }
            est = tmp.siguiente.estudiante;
        }else if(pos==1){
            est=primero.estudiante;
        }else{
            int count = 2;
            Nodo tmp = primero;
            while(count < pos){
                count++;
                tmp=tmp.siguiente;
            }
            est = tmp.siguiente.estudiante;
        }
        return est;
    }

    public Estudiante BuscarCod(String cod){
        Estudiante est = null;
        Nodo tmp = primero;
        boolean encontado = false;
        while((tmp!=null)&&(!encontado)){
            if(cod.equalsIgnoreCase(tmp.estudiante.codigo)){
                est = tmp.estudiante;
                encontado=true;
            }
            tmp=tmp.siguiente;
        }
        return est;
    }

    public Estudiante BuscarNom(String nom){
        Estudiante est = null;
        Nodo tmp = primero;
        boolean encontado = false;
        while((tmp!=null)&&(!encontado)){
            if(nom.equalsIgnoreCase(tmp.estudiante.nombre)){
                est = tmp.estudiante;
                encontado=true;
            }
            tmp=tmp.siguiente;
        }
        return est;
    }

    public void Imprimir(){
        Nodo tmp = primero;
        while(tmp!=null){
            System.out.println(tmp.estudiante.nombre+" - "+tmp.estudiante.codigo+" - "+tmp.estudiante.promedio);
            tmp=tmp.siguiente;
        }
    }

    public void actualizarPromedios(ArrayList<ListaNotas> lista){
        //actualizar promedios
        Nodo tmp = primero;
        while(tmp!=null){
                    
            double promedio = -1;
            for (ListaNotas lista_notas : lista) {
                if(lista_notas.condigo_est.equalsIgnoreCase(tmp.estudiante.codigo)){
                    promedio = lista_notas.calcularPromedio();
                }
            }
            tmp.estudiante.promedio = promedio;
            tmp=tmp.siguiente;
        }
    }

}
