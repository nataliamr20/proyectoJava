import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends javax.swing.JFrame implements Runnable{
ArrayList<Controles> lista2=new ArrayList<Controles>(); 
    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
        this.setLocationRelativeTo(null);
        cerrar.setBackground(new Color(0,0,0,64));
        cerrar.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        historial.setBorder(null);
        
        historial.setBackground(new Color(0,0,0,64));
        
    //  cerrar.setBackground(new Color(0,0,0,64));
        cerrar.setOpaque(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setBorderPainted(false);
       //creando el hilo
       Thread mihilo = new Thread(this);
       mihilo.start();     //ejecutando el hilo
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        cerrar = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        historial = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(null);

        cerrar.setFont(new java.awt.Font("Eras Bold ITC", 2, 18)); // NOI18N
        cerrar.setForeground(new java.awt.Color(150, 191, 218));
        cerrar.setText("X");
        cerrar.setBorder(null);
        cerrar.setBorderPainted(false);
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(cerrar);
        cerrar.setBounds(450, 10, 30, 22);

        historial.setColumns(20);
        historial.setForeground(new java.awt.Color(150, 191, 218));
        historial.setRows(5);
        historial.setCaretColor(new java.awt.Color(150, 191, 218));
        historial.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        historial.setSelectedTextColor(new java.awt.Color(150, 191, 218));
        scroll.setViewportView(historial);

        getContentPane().add(scroll);
        scroll.setBounds(0, 80, 500, 420);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/fondo.jpg"))); 
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 500, 500);

        pack();
    }
    
    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
       
    }
     private int xx;
        private int yy;
    private void formMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        
                                  
         xx=evt.getX();
         yy=evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
         int x=evt.getXOnScreen();
         int y=evt.getYOnScreen();
         setLocation(x-xx,y-yy);        
    }

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton cerrar;
    private javax.swing.JTextArea historial;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scroll;

    @Override
    public void run() { 
        try {
            //puerto que debe abrir
            ServerSocket servidor = new ServerSocket(1024); // que esté a la escucha y abra ese puerto
            String nick,mensaje2,ip;
            ArrayList <String> listaIp4 = new ArrayList<String>();  //arrayList que alamcenará las ip conectadas
        
            Paquete1 paquete_recibido;
              Controles aux = new Controles();
                    
            while(true){   //bucle infinito que acepta las conexiones 
            Socket misocket = servidor.accept(); //que acepte las peticiones de conexion 
            
           
            
           
            /*...........fin de deteccion online xD*/
              //flujo de datos de entrada
            ObjectInputStream entrada =new ObjectInputStream(misocket.getInputStream()); 
            
            paquete_recibido=(Paquete1) entrada.readObject();
        
            nick=paquete_recibido.getNick2();
            mensaje2=paquete_recibido.getMensaje();
            ip=paquete_recibido.getIp();
            int puertoCliente=9999;
            if(!mensaje2.equals(" Online")){
                String aux2=null;
                for(int i=0;i<lista2.size();i++){
                if(lista2.get(i).getNombrecontrol().equalsIgnoreCase(paquete_recibido.getDestinatario())){
                 aux2=lista2.get(i).getIpcontrol();
                  puertoCliente=lista2.get(i).getPuerto();
                  System.out.println("se quiere conectar con: "+puertoCliente);
                }
                }
            historial.append("\n"+ nick+": "+mensaje2+"  para "+aux2);
            System.out.println(puertoCliente);
            Socket enviaDestinatario = new Socket(ip,puertoCliente);
            ObjectOutputStream reenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
            reenvio.writeObject(paquete_recibido);
                     
                     
            reenvio.close();
            enviaDestinatario.close();
            misocket.close();
            }else{  
               Controles hola = new Controles();  
                    InetAddress localizacion = misocket.getInetAddress();
                    int puerto = paquete_recibido.getPuerto();
                    String ipremota = localizacion.getHostAddress(); //almacenamos la direccion en string
                    aux.setIpcontrol(ipremota);
                    aux.setNombrecontrol(paquete_recibido.getNick2());
                    hola.setIpcontrol(ipremota);
                    hola.setNombrecontrol(nick);
                    hola.setPuerto(puerto);
                    lista2.add(hola);
          
                     historial.append("\n--------------------------------------------------- -Conectado------------------------------------------------------");
                     historial.append("\n"+ nick+" : ip "+ipremota +"  conectado desde el puerto "+puerto);
      
                     historial.append("\n\n");
                     paquete_recibido.setControl(lista2);
                   
                    for(int i=0;i<lista2.size();i++){
                     Socket enviaDestinatario = new Socket(lista2.get(i).getIpcontrol(),lista2.get(i).getPuerto());
                     ObjectOutputStream reenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                      reenvio.writeObject(paquete_recibido);
                      reenvio.close();
                       enviaDestinatario.close();
                       misocket.close();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class Paquete1 implements Serializable{
private String mensaje;
private String ip;
private String nick2;
private ArrayList<String> dirIps;
private ArrayList<Controles> control;
private int puerto;
private String destinatario;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public ArrayList<Controles> getControl() {
        return control;
    }

    public void setControl(ArrayList<Controles> control) {
        this.control = control;
    }
    
    
    public ArrayList<String> getDirIps() {
        return dirIps;
    }

    public void setDirIps(ArrayList<String> dirIps) {
        this.dirIps = dirIps;
    }

 
   

  
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNick2() {
        return nick2;
    }

    public void setNick2(String nick2) {
        this.nick2 = nick2;
    }

  

}
class Controles implements Serializable{
private String ipcontrol;
private String nombrecontrol;
private int puerto;

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getIpcontrol() {
        return ipcontrol;
    }

    public void setIpcontrol(String ipcontrol) {
        this.ipcontrol = ipcontrol;
    }

    public String getNombrecontrol() {
        return nombrecontrol;
    }

    public void setNombrecontrol(String nombrecontrol) {
        this.nombrecontrol = nombrecontrol;
    }

}