public class ListaNotas {
    String condigo_est;
    Nota primera;
    int tamano;

    public ListaNotas(String condigo_est){
        this.condigo_est=condigo_est;
        this.primera=null;
        tamano=0;
    }

    public boolean esVacia(){
        return this.primera == null;
    }

    public int Size(){return this.tamano;}

    public boolean AgregarUltimo(Nota nota){
        boolean agregada=false;
        if(esVacia()){
            primera=nota;
            agregada=true;
        }else{
            if(BuscarNom(nota.nombre)==null){
                Nota tmp = primera;
                while(tmp.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                tmp.siguiente=nota;
                agregada=true;
            }else{agregada=false;}
        }
        tamano++;
        return agregada;
    }

    public boolean AgregarInicio(Nota nota){
        boolean agregada=false;
        if(esVacia()){
            primera=nota;
            agregada=true;
        }else{
            if(BuscarNom(nota.nombre)==null){
                nota.siguiente=primera;
                primera=nota;
                agregada=true;
            }else{agregada=false;}
        }
        tamano++;
        return agregada;
    }

    public boolean AgregarenPos(Nota nota, int pos){
        boolean agregada=false;
        if(((tamano+1)<pos) || (pos<1)){
            agregada = false;
        }else if(pos==(tamano+1)){
            agregada = AgregarUltimo(nota);
        }else if(pos==1){
            agregada = AgregarInicio(nota);
        }else{
            if(BuscarNom(nota.nombre)==null){
                int count = 1;
                Nota tmp = primera;
                while(count<(pos-1)){
                    count++;
                    tmp=tmp.siguiente;
                }
                nota.siguiente=tmp.siguiente;
                tmp.siguiente=nota;
                tamano++;
                agregada=true;
            }else{agregada=false;}
        }
        return agregada;
    }

    public Nota QuitarUltimo(){
        Nota quitada = null;
        if(esVacia()){
            quitada=null;
        }else{
            if(primera.siguiente!=null){
                Nota tmp = primera;
                while(tmp.siguiente.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                quitada = tmp.siguiente;
                tmp.siguiente=null;
                tamano--;
            }else{quitada = QuitarInicio();}
        }
        return quitada;
    }

    public Nota QuitarInicio(){
        Nota quitada = null;
        if(esVacia()){
            quitada = null;
        }else{
            quitada = primera;
            primera=primera.siguiente;
            tamano--;
        }
        return quitada;
    }

    public Nota QuitarPos(int pos){
        Nota quitada = null;
        if((tamano<pos) || (pos<1)){
            quitada = null;
        }else if(pos==tamano){
            quitada=QuitarUltimo();
        }else if(pos==1){
            quitada=QuitarInicio();
        }else{
            int count = 2;
            Nota tmp = primera;
            while(count < pos){
                count++;
                tmp=tmp.siguiente;
            }
            quitada = tmp.siguiente;
            tmp.siguiente = tmp.siguiente.siguiente;
            tamano--;
        }
        return quitada;
    }

    public Nota QuitarNom(String nom){
        Nota quitada= null;
        Nota tmp = primera;
        boolean encontada = false;
        int count = 1;
        while((tmp!=null)&&(!encontada)){
            if(nom.equalsIgnoreCase(tmp.nombre)){
                quitada = tmp;
                QuitarPos(count);
                encontada=true;
            }
            tmp=tmp.siguiente;
            count++;
        }
        return quitada;
    }

    public Nota BuscarPos(int pos){
        Nota encontrada = null;
        if((tamano<pos) || (pos<1)){
            encontrada = null;
        }else if(pos==tamano){
            Nota tmp = primera;
            while(tmp.siguiente.siguiente!=null){
                tmp=tmp.siguiente;
            }
            encontrada = tmp.siguiente;
        }else if(pos==1){
            encontrada=primera;
        }else{
            int count = 2;
            Nota tmp = primera;
            while(count < pos){
                count++;
                tmp=tmp.siguiente;
            }
            encontrada = tmp.siguiente;
        }
        return encontrada;
    }

    public Nota BuscarNom(String nom){
        Nota nota = null;
        Nota tmp = primera;
        boolean encontada = false;
        while((tmp!=null)&&(!encontada)){
            if(nom.equalsIgnoreCase(tmp.nombre)){
                nota = tmp;
                encontada=true;
            }
            tmp=tmp.siguiente;
        }
        return nota;
    }

    public void Imprimir(){
        System.out.println(condigo_est+" - "+ImprimirNotas());
    }

    public String ImprimirNotas(){
        String notas =" ";
        Nota tmp = primera;
        while(tmp!=null){
            notas=notas+"-"+tmp.nombre+": "+tmp.nota+" ";
            tmp=tmp.siguiente;
        }
        return notas;
    }

    public double calcularPromedio(){
        double promedio =0;
        Nota tmp = primera;
        while(tmp!=null){
            promedio = promedio+tmp.nota;
            tmp=tmp.siguiente;
        }
        promedio=promedio/this.tamano;
        return promedio;
    }

    public Boolean cambiarNota(int pos, double nueva){
        Boolean cambiada = false;
        if((tamano<pos) || (pos<1)){
            cambiada=false;
        }else if(pos==tamano){
            Nota tmp = primera;
            while(tmp.siguiente.siguiente!=null){
                tmp=tmp.siguiente;
            }
            tmp.siguiente.nota= nueva;
            cambiada = true;
        }else if(pos==1){
            primera.nota = nueva;
            cambiada=true; 
        }else{
            int count = 2;
            Nota tmp = primera;
            while(count < pos){
                count++;
                tmp=tmp.siguiente;
            }
            tmp.siguiente.nota = nueva;
            cambiada = true;
        }
        return cambiada;
    }

    public int buscarNomPos(String nombre) {

        int pos = 0;
        Nota tmp = primera;
        boolean encontada = false;
        while((tmp!=null)&&(!encontada)){
            if(nombre.equalsIgnoreCase(tmp.nombre)){
                encontada=true;
            }
            pos++;
            tmp=tmp.siguiente;
        }
        return pos;
    }
}
