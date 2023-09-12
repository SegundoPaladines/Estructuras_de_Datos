import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class App {
    public static Escritor escritor = new Escritor();
    public static Lector lector = new Lector();
    public static String curso;

    public static void main(String[] args) throws Exception {

        curso=JOptionPane.showInputDialog(null , "Ingrese el nombre del curso");

        // variables y contenedores principales
        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);

        MostrarVentana();

        escritor.EscribirNotas(curso);
        escritor.EscribirEstudiantes(estudiantes);
    }

    public static void agregarEstudiantes(ListaEstudiantes estudiantes, ArrayList<ListaNotas> lista, Ventana ventana){

        Nodo tmp = estudiantes.primero;
        while(tmp!=null){
            if(tmp.estudiante.promedio>=0){
                Object[] fila = {tmp.estudiante.codigo, tmp.estudiante.nombre, tmp.estudiante.promedio};
                ventana.modeloTabla.addRow(fila);
            }else{
                Object[] fila = {tmp.estudiante.codigo, tmp.estudiante.nombre, "N/A"};
                ventana.modeloTabla.addRow(fila);
            }
            
            tmp=tmp.siguiente;
        }

    }

    public static void formularioEstudiante(ListaEstudiantes estudiantes){
        Ventana formulario = new Ventana(2, "Formulario Estudiantes");

        formulario.boton1.addActionListener(new ActionListener() {

            //Formato de codigos A-2020-0333 por ejemplo
            //semeste-año-numero el semestre A o B va en mayusculas y el años del 2020 al 2029
            Pattern cod = Pattern.compile("^([A|B])(\\-)(20(1|2))([0-9])(\\-)([0-9])+$");
            Pattern nom = Pattern.compile("^[a-zA-Z \\s \\t]+$");

            public void actionPerformed(ActionEvent e) {
                String nombre = formulario.tf_nombre.getText();
                String codigo = formulario.tf_codigo.getText();

                //si nombre es valido
                Matcher nombre_valido = nom.matcher(nombre);
                //si codigo es valido
                Matcher codigo_valido = cod.matcher(codigo);

                if((nombre_valido.find())&&(codigo_valido.find())){
                    if((estudiantes.BuscarCod(codigo))==null){
                        estudiantes.AgregarUltimo(new Estudiante(nombre, codigo));
                        formulario.tf_codigo.setText("");
                        formulario.tf_nombre.setText("");
                        formulario.lb_msg.setText("Estudiante "+nombre+" Agregado con exito");
                        escritor.EscribirEstudiantes(estudiantes);
                    }else{
                        formulario.lb_msg.setText("Codigo: "+codigo+" Ya está siendo Utilizado");
                    }
                }else{
                    formulario.lb_msg.setText("Nombre o codigo no validos");
                }
            }	
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               MostrarVentana();
            }
        });
    }

    private static void MostrarVentana() {
        Ventana ventana=new Ventana(curso);
        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);
        agregarEstudiantes(estudiantes,curso, ventana);

        //añadir los event listener a la ventana principal
        ventana.boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                formularioEstudiante(estudiantes);	
            }	
        });
        ventana.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                formularioQuitarEstudiantes(estudiantes);
            }	
        });
        ventana.boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                formularioGestionarNotas(estudiantes);
            }	
        });
        ventana.boton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }	
        });
        
    }

    protected static void formularioGestionarNotas(ListaEstudiantes estudiantes) {
        Ventana formulario = new Ventana(1, "Gestionar Notas");

        //Gestionar notas
        formulario.boton1.addActionListener(new ActionListener() {

            //Formato de codigos A-2020-0333 por ejemplo
            //semeste-año-numero el semestre A o B va en mayusculas y el años del 2020 al 2029
            Pattern cod = Pattern.compile("^([A|B])(\\-)(20(1|2))([0-9])(\\-)([0-9])+$");

            public void actionPerformed(ActionEvent e) {
                String codigo = formulario.tf_codigo.getText();

                //si codigo es valido
                Matcher codigo_valido = cod.matcher(codigo);

                if((codigo_valido.find())){
                    Estudiante est = estudiantes.BuscarCod(codigo);
                    if(est!=null){
                        formulario.dispose();
                        listarNotas(est);
                    }else{
                        formulario.lb_msg.setText("No hay ningun estuidiante con Codigo: "+codigo);
                    }
                }else{
                    formulario.lb_msg.setText("El codigo no es valido");
                }
            }	
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               MostrarVentana();
            }
        });
    }

    protected static void listarNotas(Estudiante est) {

        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);

        Ventana formulario = new Ventana("Lista Notas", est);

        Estudiante estu = estudiantes.BuscarCod(est.codigo);

        if(estu.promedio>=0){
            formulario.promedio.setText(estu.promedio+"");
        }else{
            formulario.promedio.setText("N/A");
        }
        ListaNotas notas = null;

        for (ListaNotas listaNotas : curso) {
            if(listaNotas.condigo_est.equalsIgnoreCase(est.codigo)){
                notas = listaNotas;
            }
        }

        if(notas!=null){
            Nota tmp = notas.primera;
            while(tmp!=null){
                Object[] fila = {tmp.nombre,tmp.nota};
                formulario.modeloTabla.addRow(fila);
                tmp=tmp.siguiente;
            }
        }

        //Agregar notas
        formulario.boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formulario.dispose();
                agregarNotas(est);
            }	
        });

        //Quitar Nota
        formulario.boton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                formulario.dispose();
                quitarNotas(est);
            }	
        });

        //Quitar Nota
        formulario.boton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                formulario.dispose();
                editarNotas(est);
            }	
        });

        //volver
        formulario.boton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               MostrarVentana();
            }
        });
    }

    protected static void editarNotas(Estudiante est) {
        Ventana formulario = new Ventana(3, "Actualizar Nota");
        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);

        formulario.boton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Pattern nom = Pattern.compile("^([A-Za-z \\s \\t])+([0-9]){0,3}$");
                Pattern not = Pattern.compile("^([0-5]){1}|(([0-5]{1})(\\.)([0-9]+))$");
                String nombre = formulario.tf_nombre.getText();
                String nota = formulario.tf_nota.getText();
                nota = nota.replaceAll("\\s", "");
                nota = nota.replaceAll("\\t", "");
                //si nombre es valido
                Matcher nombre_valido = nom.matcher(nombre);
                //si codigo es valido
                Matcher nota_valida = not.matcher(nota);
                if((nota_valida.find())&&(nombre_valido.find())){
                    double cal = Double.parseDouble(nota);
                    if(cal<=5){
                        for (ListaNotas listanotas : curso) {
                            if(listanotas.condigo_est.equalsIgnoreCase(est.codigo)){
                                if((listanotas.BuscarNom(nombre))!=null){
                                    int pos = listanotas.buscarNomPos(nombre);
                                    listanotas.QuitarNom(nombre);
                                    listanotas.AgregarenPos(new Nota(cal, nombre), pos);
                                    formulario.lb_msg.setText("Calificacion Actualizada con exito");
                                    formulario.tf_nombre.setText("");
                                    formulario.tf_nota.setText("");
                                    escritor.EscribirNotas(curso);
                                    estudiantes.actualizarPromedios(lector.LeerNotas());
                                }else{
                                    formulario.lb_msg.setText("Este estudiante no registra esta actividad");
                                }
                            }                            
                        }
                    }else{
                        formulario.lb_msg.setText("La nota no puede ser mayor que 5.0");
                    }
                }else{
                    formulario.lb_msg.setText("Nombre de la Actividad o Nota no validos");
                }
            }
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               listarNotas(est);
            }
        });
    }

    protected static void quitarNotas(Estudiante est) {

        Ventana formulario = new Ventana(4, "Quitar Nota");
        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);

        formulario.boton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Pattern nom = Pattern.compile("^([A-Za-z \\s \\t])+([0-9]){0,3}$");
                String nombre = formulario.tf_nombre.getText();

                //si nombre es valido
                Matcher nombre_valido = nom.matcher(nombre);

                //si nombre es valido
                if(nombre_valido.find()){

                    String texto = "";

                    for (ListaNotas listaNotas : curso) {
                        if(est.codigo.equalsIgnoreCase(listaNotas.condigo_est)){
                            if(!listaNotas.esVacia()){
                                Nota nota = listaNotas.BuscarNom(nombre);
                                if(nota!=null){
                                    int eliminar = -1;
                                    try {
                                        eliminar = JOptionPane.showConfirmDialog(null, "Está seguro de querer eliminar la nota:"+
                                                "\n Estudiante: "+est.nombre +"\n Codigo: "+est.codigo+
                                                "\n "+nota.nombre+" Nota: "+nota.nota);
                                    } catch (Exception ex) {
                                        System.out.println("Error controlado: eliminar vacio");
                                    }
                                    if(eliminar==0){
                                        listaNotas.QuitarNom(nombre);
                                        texto = nota.nombre+" Nota: "+nota.nota+" Eliminada";
                                        formulario.tf_nombre.setText("");
                                        escritor.EscribirNotas(curso);
                                        estudiantes.actualizarPromedios(lector.LeerNotas());   
                                    }
                                }else{
                                    texto = "Nombre de la actividad no encontrada";
                                }

                            }else{
                                texto = "La lista está vacia";
                            }
                        }
                    }

                    formulario.lb_msg.setText(texto);

                }else{
                    formulario.lb_msg.setText("Nombre de la Actividad no valido");
                }
            }
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               listarNotas(est);
            }
        });
    }

    protected static void agregarNotas(Estudiante est) {
        Ventana formulario = new Ventana(3, "Agregar Nota");
        ListaEstudiantes estudiantes = new ListaEstudiantes();
        ArrayList<ListaNotas> curso = lector.LeerNotas();

        //lectura de archivos
        lector.LeerEstudiantes(estudiantes);
        estudiantes.actualizarPromedios(curso);

        formulario.boton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Pattern nom = Pattern.compile("^([A-Za-z \\s \\t])+([0-9]){0,3}$");
                Pattern not = Pattern.compile("^([0-5]){1}|(([0-5]{1})(\\.)([0-9]+))$");
                String nombre = formulario.tf_nombre.getText();
                String nota = formulario.tf_nota.getText();
                nota = nota.replaceAll("\\s", "");
                nota = nota.replaceAll("\\t", "");
                //si nombre es valido
                Matcher nombre_valido = nom.matcher(nombre);
                //si codigo es valido
                Matcher nota_valida = not.matcher(nota);
                if((nota_valida.find())&&(nombre_valido.find())){
                    double cal = Double.parseDouble(nota);
                    if(cal<=5){
                        boolean agregado = false;
                        for (ListaNotas listanotas : curso) {
                            if(listanotas.condigo_est.equalsIgnoreCase(est.codigo)){
                                if((listanotas.BuscarNom(nombre))==null){
                                    listanotas.AgregarUltimo(new Nota(cal, nombre));
                                    formulario.lb_msg.setText("Calificacion Agregada con exito");
                                    formulario.tf_nombre.setText("");
                                    formulario.tf_nota.setText("");
                                    escritor.EscribirNotas(curso);
                                    agregado=true;
                                    estudiantes.actualizarPromedios(lector.LeerNotas());
                                }else{
                                    agregado=true;
                                    formulario.lb_msg.setText("Este estudiante ya registra nota en esta actividad");
                                }
                            }                            
                        }
                        if(!agregado){
                            ListaNotas lista_notas=new ListaNotas(est.codigo);
                            lista_notas.AgregarUltimo(new Nota(cal, nombre));
                            curso.add(lista_notas);
                            escritor.EscribirNotas(curso);
                            agregado=true;
                            formulario.lb_msg.setText("Calificacion Agregada con exito");
                            formulario.tf_nombre.setText("");
                            formulario.tf_nota.setText("");
                            estudiantes.actualizarPromedios(lector.LeerNotas());
                        }
                    }else{
                        formulario.lb_msg.setText("La nota no puede ser mayor que 5.0");
                    }
                }else{
                    formulario.lb_msg.setText("Nombre de la Actividad o Nota no validos");
                }
            }
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               listarNotas(est);
            }
        });
    }

    public static void formularioQuitarEstudiantes(ListaEstudiantes estudiantes){
        Ventana formulario = new Ventana(1, "Eliminar Estudiantes");

        formulario.boton1.addActionListener(new ActionListener() {

            //Formato de codigos A-2020-0333 por ejemplo
            //semeste-año-numero el semestre A o B va en mayusculas y el años del 2020 al 2029
            Pattern cod = Pattern.compile("^([A|B])(\\-)(20(1|2))([0-9])(\\-)([0-9])+$");

            public void actionPerformed(ActionEvent e) {
                String codigo = formulario.tf_codigo.getText();

                //si codigo es valido
                Matcher codigo_valido = cod.matcher(codigo);

                if((codigo_valido.find())){
                    Estudiante est = estudiantes.BuscarCod(codigo);
                    if(est!=null){
                        int eliminar = -1;
                        double promedio = est.promedio;
                        if(promedio>=0){
                            try {
                                eliminar = JOptionPane.showConfirmDialog(null, "Está seguro de querer eliminar al estudiante:"+
                                        "\n Nombre: "+est.nombre +"\n Codigo: "+est.codigo+
                                        "\n Promedio: "+promedio+
                                        "\nEl registro de notas del estudiante NO será eliminado");
                            } catch (Exception ex) {
                                System.out.println("Error controlado: eliminar vacio");
                            }
                        }else{
                            try {
                                eliminar = JOptionPane.showConfirmDialog(null, "Está seguro de querer eliminar al estudiante:"+
                                        "\n Nombre: "+est.nombre +"\n Codigo: "+est.codigo+
                                        "\n Promedio: N/A"+
                                        "\nEl registro de notas del estudiante NO será eliminado");
                            } catch (Exception ex) {
                                System.out.println("Error controlado: eliminar vacio");
                            }
                        }

                        if(eliminar==0){
                            est = estudiantes.QuitarCod(codigo);
                            formulario.lb_msg.setText("Estudiante "+est.nombre+" Eliminado con exito");
                            formulario.tf_codigo.setText("");
                            escritor.EscribirEstudiantes(estudiantes);
                        }
                        
                    }else{
                        formulario.lb_msg.setText("No hay ningun estuidiante con Codigo: "+codigo);
                    }
                }else{
                    formulario.lb_msg.setText("El codigo no es valido");
                }
            }	
        });

        formulario.boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               formulario.dispose();
               MostrarVentana();
            }
        });
    }	
}
