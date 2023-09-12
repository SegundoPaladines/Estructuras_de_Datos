import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Ventana extends JFrame{
    public JButton boton1, boton2, boton3, boton4, boton5, boton6;
    public JTable tabla;
    public DefaultTableModel modeloTabla;
    public JTextField tf_codigo, tf_nombre, tf_nota;
    public JLabel lb_msg, promedio; 

    public Ventana(String curso) {
        // Configuramos la ventana
        setTitle("Curso de: "+curso);
        setSize(630, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        // Creamos los botones
        boton1 = new JButton("Añadir Estudiante");
        boton2 = new JButton("Quitar Estudiante");
        boton3 = new JButton("Gestionar Notas");
        boton4 = new JButton("Salir");

        // Creamos la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Codigo Estudiante");
        modeloTabla.addColumn("Nombre Estudiante");
        modeloTabla.addColumn("Promedio Estudiante");
        tabla = new JTable(modeloTabla);
        tabla.setDefaultEditor(Object.class, null);

        JPanel panelBotones = new JPanel();
        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);

        JPanel panelBotones2 = new JPanel();
        panelBotones2.add(boton4);

        JPanel panelTabla = new JPanel();
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(580, 180));
        panelTabla.add(scrollPane);

        //setear tamaños y posisciones

        panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());
        panelBotones2.setBorder(BorderFactory.createLoweredBevelBorder());
        panelTabla.setBorder(BorderFactory.createLoweredBevelBorder());

        panelBotones.setLocation(10,20);
        panelBotones.setSize(600,40);

        panelBotones2.setLocation(10,270);
        panelBotones2.setSize(600,40);
        
        panelTabla.setLocation(10,70);
        panelTabla.setSize(600,200);

        getContentPane().add(panelBotones);
        getContentPane().add(panelBotones2);
        getContentPane().add(panelTabla);

        setVisible(true);
    }

    public Ventana(int tipo, String titulo) {
        //tipo 1 es un formulario con 2 botores y 2 labels y 1 campo de texto -> 1 in

        if(tipo==1){
            // Configuramos la ventana
            setTitle(titulo);
            setSize(330, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);

            //elementos para el codigo
            JLabel lb_cod = new JLabel("Codigo:");
            tf_codigo = new JTextField(10);

            // Creamos los botones
            boton1 = new JButton("Aceptar");
            boton2 = new JButton("Atrás");

            // mensaje deexito o error
            JPanel msg = new JPanel();
            lb_msg = new JLabel();

            // Añadimos los componentes a la ventana
            JPanel panelBotones = new JPanel();
            panelBotones.add(boton1);
            panelBotones.add(boton2);

            //componentes del codigo
            JPanel jp_codigo = new JPanel();
            jp_codigo.add(lb_cod);
            jp_codigo.add(tf_codigo);

             //añadir mensaje de eror
             msg.add(lb_msg);

            jp_codigo.setBorder(BorderFactory.createLoweredBevelBorder());
            msg.setBorder(BorderFactory.createLoweredBevelBorder());
            panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());

            jp_codigo.setLocation(10,10);
            jp_codigo.setSize(300,40);
            msg.setLocation(10,50);
            msg.setSize(300,30);
            panelBotones.setLocation(10,90);
            panelBotones.setSize(300,40);

            getContentPane().add(jp_codigo);
            getContentPane().add(msg);
            getContentPane().add(panelBotones);

            setVisible(true);
        }

        // tipo 2 es un formulario con 2 botones, 3 labels y 2 campos de texto -> 2 in
        if(tipo==2){
            // Configuramos la ventana
            setTitle(titulo);
            setSize(330, 270);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);

            //elementos para el codigo
            JLabel lb_cod = new JLabel("Codigo:");
            tf_codigo = new JTextField(10);

            // elementos para el nombre
            JLabel lb_nombre = new JLabel("Nombres Y Apellidos:");
            tf_nombre = new JTextField(10);

            // Creamos los botones
            boton1 = new JButton("Aceptar");
            boton2 = new JButton("Atrás");

            // mensaje deexito o error
            JPanel msg = new JPanel();
            lb_msg = new JLabel();

            // Añadimos los componentes a la ventana
            JPanel panelBotones = new JPanel();
            panelBotones.add(boton1);
            panelBotones.add(boton2);

            //componentes del codigo
            JPanel jp_codigo = new JPanel();
            jp_codigo.add(lb_cod);
            jp_codigo.add(tf_codigo);

             //componentes del nombre
             JPanel jp_nombre = new JPanel();
             jp_nombre.add(lb_nombre);
             jp_nombre.add(tf_nombre);

             //añadir mensaje de eror
             msg.add(lb_msg);

            jp_codigo.setBorder(BorderFactory.createLoweredBevelBorder());
            jp_nombre.setBorder(BorderFactory.createLoweredBevelBorder());
            msg.setBorder(BorderFactory.createLoweredBevelBorder());
            panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());

            jp_codigo.setLocation(10,10);
            jp_codigo.setSize(300,40);
            jp_nombre.setLocation(10,60);
            jp_nombre.setSize(300,40);
            msg.setLocation(10,120);
            msg.setSize(300,30);
            panelBotones.setLocation(10,170);
            panelBotones.setSize(300,40);

            getContentPane().add(jp_codigo);
            getContentPane().add(jp_nombre);
            getContentPane().add(msg);
            getContentPane().add(panelBotones);

            setVisible(true);
        }
        if(tipo==3){

            // Configuramos la ventana
            setTitle(titulo);
            setSize(330, 270);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);

            //Elementos para el nombre de la actividad
            JLabel lb_nom = new JLabel("Nombre de la Actividad:");
            tf_nombre = new JTextField(10);

            // elementos para el nombre
            JLabel lb_nota = new JLabel("Calificacion:");
            tf_nota = new JTextField(10);

            // Creamos los botones
            boton1 = new JButton("Aceptar");
            boton2 = new JButton("Atrás");

            // mensaje deexito o error
            JPanel msg = new JPanel();
            lb_msg = new JLabel();

            // Añadimos los componentes a la ventana
            JPanel panelBotones = new JPanel();
            panelBotones.add(boton1);
            panelBotones.add(boton2);

            //componentes del codigo
            JPanel jp_nombre = new JPanel();
            jp_nombre.add(lb_nom);
            jp_nombre.add(tf_nombre);

             //componentes del nombre
             JPanel jp_nota = new JPanel();
             jp_nota.add(lb_nota);
             jp_nota.add(tf_nota);

             //añadir mensaje de eror
             msg.add(lb_msg);

            jp_nombre.setBorder(BorderFactory.createLoweredBevelBorder());
            jp_nota.setBorder(BorderFactory.createLoweredBevelBorder());
            msg.setBorder(BorderFactory.createLoweredBevelBorder());
            panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());

            jp_nombre.setLocation(10,10);
            jp_nombre.setSize(300,40);
            jp_nota.setLocation(10,60);
            jp_nota.setSize(300,40);
            msg.setLocation(10,120);
            msg.setSize(300,30);
            panelBotones.setLocation(10,170);
            panelBotones.setSize(300,40);

            getContentPane().add(jp_nombre);
            getContentPane().add(jp_nota);
            getContentPane().add(msg);
            getContentPane().add(panelBotones);

            setVisible(true);
        }

        if(tipo==4){

            // Configuramos la ventana
            setTitle(titulo);
            setSize(330, 270);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);

            //Elementos para el nombre de la actividad
            JLabel lb_nom = new JLabel("Nombre de la Actividad:");
            tf_nombre = new JTextField(10);

            // Creamos los botones
            boton1 = new JButton("Aceptar");
            boton2 = new JButton("Atrás");

            // mensaje deexito o error
            JPanel msg = new JPanel();
            lb_msg = new JLabel();

            // Añadimos los componentes a la ventana
            JPanel panelBotones = new JPanel();
            panelBotones.add(boton1);
            panelBotones.add(boton2);

            //componentes del codigo
            JPanel jp_nombre = new JPanel();
            jp_nombre.add(lb_nom);
            jp_nombre.add(tf_nombre);

             //añadir mensaje de eror
             msg.add(lb_msg);

            jp_nombre.setBorder(BorderFactory.createLoweredBevelBorder());
            msg.setBorder(BorderFactory.createLoweredBevelBorder());
            panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());

            jp_nombre.setLocation(10,10);
            jp_nombre.setSize(300,40);
            msg.setLocation(10,60);
            msg.setSize(300,30);
            panelBotones.setLocation(10,100);
            panelBotones.setSize(300,40);

            getContentPane().add(jp_nombre);
            getContentPane().add(msg);
            getContentPane().add(panelBotones);

            setVisible(true);
        }
    }

    public Ventana(String titulo, Estudiante est) {
        // Configuramos la ventana
        setTitle("Notas: ");
        setSize(630, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        //Label Titulo:
        JLabel label = new JLabel("Notas del estudiante: "+est.nombre + " Codigo: "+est.codigo);
        //label primedio
        double prom = est.promedio;
        if(prom>=0){
             promedio= new JLabel("Promedio: "+prom);
        }else{
            promedio= new JLabel("Promedio: "+"N/A");
        }
        
        // Creamos los botones
        boton1 = new JButton("Agregar Nota");
        boton2 = new JButton("Quitar Nota");
        boton3 = new JButton("Editar Nota");
        boton4 = new JButton("Atrás");

        // Creamos la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre nota");
        modeloTabla.addColumn("Nota");

        tabla = new JTable(modeloTabla);
        tabla.setDefaultEditor(Object.class, null);

        JPanel panelTitulo = new JPanel();
        panelTitulo.add(label);

        JPanel panelPromedio = new JPanel();
        panelPromedio.add(promedio);

        JPanel panelBotones = new JPanel();
        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        panelBotones.add(boton4);

        JPanel panelTabla = new JPanel();
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(580, 180));
        panelTabla.add(scrollPane);

        //setear tamaños y posisciones

        panelBotones.setBorder(BorderFactory.createLoweredBevelBorder());
        panelTabla.setBorder(BorderFactory.createLoweredBevelBorder());
        panelTitulo.setBorder(BorderFactory.createLoweredBevelBorder());
        panelPromedio.setBorder(BorderFactory.createLoweredBevelBorder());

        panelBotones.setLocation(10,20);
        panelBotones.setSize(600,40);

        panelTitulo.setLocation(10,70);
        panelTitulo.setSize(600,30);

        panelPromedio.setLocation(10,110);
        panelPromedio.setSize(600,30);
        
        panelTabla.setLocation(20,160);
        panelTabla.setSize(600,200);

        getContentPane().add(panelBotones);
        getContentPane().add(panelTitulo);
        getContentPane().add(panelPromedio);
        getContentPane().add(panelTabla);

        setVisible(true);
    }
}
