//
// Proyecto de Desarrollo de VIdeojuegos
// Alumnos:
// Luis Uriel Avila Vargas - A00815578
// Diego Adolfo Jose Villa - A00815260
//
// JUEGO:
// Nombre: Breaking Bricks
// Juego parecido a Brick Break con tematica de Breaking Bad

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class breakingBricks extends JFrame implements Runnable, KeyListener,
        MouseListener, MouseMotionListener{

    private static final long serialVersionUID = 1L;

    //  Declaraciones de variables de graficos y recursos
    // IMAGENES
    private ImageIcon imaInicio;        // Imagen de la pantalla Incial
    private ImageIcon imaEscenario;     // Imagen del Escenario
    private Image imaImageDB;           // Objeto para dibujar el Buffer
    private ImageIcon iPausa;           // Imagen de Pausa
    private ImageIcon imaGuardar;
    private ImageIcon iGO;              // Imagen de Game Over
    private Vidas VidasJugador;         //Imagen de las vidas del jugador
    private ImageIcon imaJuegoGanado;   // Imagen del Juego Ganado
    
    // OBJETO GRAFICO
    private Graphics graGraficoDB;      // Grafico que se usa para dibujar
    
    // OBJETOS EN PANTALLA
    private Barra BarSirena;            // Barra del jugador
    private LinkedList<Objeto> lstElementos;   //  Lista de elementos
    private Pelota Pelota1;             // Pelota
    
    // BOTONES
    private Objeto obBotonI1;           // Boton de Inicio
    private Objeto obBotonI2;           // Boton de Inicio 2
    private Objeto obBAbrir1;           // Boton para cargar juego    
    private Objeto obBAbrir2;           // Boton para cargar juego 2
    private Objeto obBotonS;            // Boton de Si
    private Objeto obBotonS2;           // Boton de Si 2
    private Objeto obBotonN;            // Boton de NO
    private Objeto obBotonN2;           // Botnon de NO
    
    // MUSICA Y SONIDOS
    private SoundClip scFondo;          // Tema musical del juego
    private SoundClip scBarra;          // Sonido de Choque con la barra
    private SoundClip scCaja;           // Sonido de Choque con la caja

    // VARIABLES BOLEANAS
    private boolean bInicio;            // Bandera: Indica cuando inicia el juego
    private boolean bBotonS;            // Bandera: Indica cuando termina el juego
    private boolean bBotonI2;           // Bandera:
    private boolean bPausa;             // Bandera: Indica cuando se pausa
    private boolean bSalir;             // Bandera: Indica cuando termina el juego
    private boolean bReiniciar;         // Bandera: Indica el reinicio del juego
    private boolean bCerrar;            // Bandera: Indica que se gano el juego
    private boolean bSalirFrame;        // Bandera: Indica si hay que mostrar botones de salida
    private boolean bAbrir;             // Bandera: Indica si se cargo una partida
    private boolean bBotonAbrir;        // Bandera: Indica si se presiono el boton abrir
    private boolean bGuardar;           // Bandera: Indica que se esta guardando el juego
    
    // PARAMETROS DEL JUEGO
    private int iVelocidad;             // Velocidad de la pelota
    private int iVidas;                 // Cantidad de Vidas del Jugador
    private int iPuntuacion;            // Puntuacion
    private int iMov;                   // Movimiento de la Barra
    private int iPosBIX;                // Posicion inicial X de la barra
    private int iPosBIY;                // Posicion Y de la barra
    private String sNombreArchivo;      // Nombre del archivo donde se guardan datos de partida
    int iPosElementos[][];                  //  Matriz para guardar la posicion de los elementos
    int iPosiciones[][] = new int[3][16];   // Mapa de la pantalla

    // OTRAS VARIABLES ENTERAS
    private int iCajasEliminadas;       // Cuenta las cajas eliminadas

    public breakingBricks(){

        //Define el título de la ventana
        setTitle("Breacking Bricks");
        //Define la operación que se llevará acabo cuando la ventana sea cerrada.
        // Al cerrar, el programa terminará su ejecución
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        scFondo.play();
        //  Declaracion de un hilo
        Thread th = new Thread(this);
        //  Empieza el hilo
        th.start();
        //Define el tamaño inicial de la ventana
        setSize(1050,590);
        setVisible(true);
    }
    
    // Inicializa Todas las Variables
    public void init()
    {
        if(bReiniciar)
        {
            bReiniciar = false;
            bInicio = false;
        }
        else
        {
            bInicio = true;
            bBotonI2 = false;
            bSalir = false;
            bBotonS = false;
            bReiniciar = false;   
        }
        // Nombre del archivo donde se guarda la partida
        sNombreArchivo = "PartidasGuardadas.txt";

        bSalirFrame = false;
        bCerrar = false;
        bGuardar = false;

        //  Posiciones iniciales de la barra
        iPosBIX = 430;
        iPosBIY = 530;
        iPuntuacion = 0;
        iCajasEliminadas = 0;
        int iAnt = 0;
        iVidas = 3;

        //  Direcciones de las ubicaciones de los recursos graficos
        URL urlInicio = this.getClass().getResource("breakingBricks1050.png");
        URL urlBIni1 = this.getClass().getResource("boton1.png");
        URL urlBIni2 = this.getClass().getResource("boton2.png");
        URL urlBNo1 = this.getClass().getResource("botonNo.png");
        URL urlBNo2 = this.getClass().getResource("botonNo2.png");
        URL urlBS = this.getClass().getResource("botonSi.png");
        URL urlBS2 = this.getClass().getResource("botonSi2.png");
        URL urlEscenario = this.getClass().getResource("escenario.png");
        URL urlBarSirena = this.getClass().getResource("gifSirenas.gif");
        URL urlPausa = this.getClass().getResource("pause.png");
        URL urlGO = this.getClass().getResource("gameOver.png");
        URL urlPelota = this.getClass().getResource("pelota.png");
        URL urlVidas1 = this.getClass().getResource("Vidas1.png");
        URL urlVidas2 = this.getClass().getResource("Vidas2.png");
        URL urlVidas3 = this.getClass().getResource("Vidas3.png");
        URL urlBAbrir1 = this.getClass().getResource("botonCargar2.png");
        URL urlBAbrir2 = this.getClass().getResource("botonCargar1.png");

        iVelocidad = 2;                 // Velocidad Inial de la pelota
        VidasJugador = new Vidas(10, 10, 3, Toolkit.getDefaultToolkit()
                    .getImage(urlVidas1), Toolkit.getDefaultToolkit()
                    .getImage(urlVidas2), Toolkit.getDefaultToolkit()
                    .getImage(urlVidas3));

        //  Asignacion de recursos a las variables
        imaInicio = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlInicio));
        imaInicio = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlInicio));
        iPausa = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlPausa));
        iGO = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlGO));
        imaEscenario = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlEscenario));
        obBotonI1 = new Objeto(438, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBIni1));
        obBotonI2 = new Objeto(438, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBIni2));
        BarSirena = new Barra(iPosBIX, iPosBIY, Toolkit.getDefaultToolkit().
                getImage(urlBarSirena));
        BarSirena = new Barra(iPosBIX, iPosBIY, Toolkit.getDefaultToolkit().
                getImage(urlBarSirena));
        Pelota1 = new Pelota(iPosBIX + 47, iPosBIY - 100, 0, 2,
                Toolkit.getDefaultToolkit().getImage(urlPelota));
        obBotonS = new Objeto(525, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBS));
        obBotonS2 = new Objeto(525, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBS2));
        obBotonN = new Objeto(625, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBNo1));
        obBotonN2 = new Objeto(625, 425, Toolkit.getDefaultToolkit()
                    .getImage(urlBNo2));
        obBAbrir1 = new Objeto(438, 493, Toolkit.getDefaultToolkit()
                    .getImage(urlBAbrir1));
        obBAbrir2 = new Objeto(438, 493, Toolkit.getDefaultToolkit()
                    .getImage(urlBAbrir2));

        // Direccion de la imagen de Juego Ganado
        URL urlJuegoGanado = this.getClass().getResource("breakingbad.png");
        // Imagen para cuando se gana el juego
        imaJuegoGanado = new ImageIcon(Toolkit.getDefaultToolkit()
                    .getImage(urlJuegoGanado));

        // Sonidos
        scFondo = new SoundClip("breakingBad.wav");
        scBarra = new SoundClip("soundBarra.wav");
        scCaja = new SoundClip("choqueCaja.wav");
        //Activa la repetición del clip
        //scFondo.setLooping(true);

        //  Mouse
        addMouseListener(this);
        addMouseMotionListener(this);
        // Teclado
        addKeyListener(this);

        //  Inicializacion de recursos del objeto Elementos
        lstElementos = new LinkedList<Objeto>();
        
        int iPosIX = 75;
        //Inicializo la matriz de las posiciones
        for(int iX = 0; iX < 3; iX++ )
        {
            for(int iY = 0; iY < 16; iY++)
            {
                iPosiciones[iX][iY] = iPosIX;
                iPosIX += 50;
            }
            iPosIX = 75;
        }
        int iPosEX = 0;
        int iPosEY = 75;

        for(int iX = 0; iX < 3; iX++ )
        {
            int iY = 0;
            while(iY < 16)
            {
                int iT = 0;
                int iEle = (int) (Math.random() * 5-1+1) + 1;
                URL urlEle = this.getClass().getResource("e1.png");

                switch(iEle) {

                    case 1:
                        urlEle = this.getClass().getResource("e1.png");
                        iT = 1;
                        break;
                    case 2:
                        urlEle = this.getClass().getResource("e2.png");
                        iT = 2;
                        break;
                    case 3:
                        urlEle = this.getClass().getResource("e3.png");
                        iT = 2;
                        break;
                    case 4:
                        urlEle = this.getClass().getResource("e4.png");
                        iT = 2;
                        break;
                    case 5:
                        urlEle = this.getClass().getResource("e5.png");
                        iT = 1;
                        break;
                }
                iPosEX = iPosiciones[iX][iY];
                //  Se crea un nuevo objeto
                Objeto oElemento = new Objeto(iPosEX,iPosEY,Toolkit.
                        getDefaultToolkit().getImage(urlEle) );

                int iPosBY = iPosEY; 
                if(iT == 1)
                {
                    iY = iY + 2;
                }
                else
                {
                    int iAPY = (int) (Math.random() * 2) + 1;
                    if(iAPY == 1)
                    {
                        iPosBY = iPosBY + 50;
                        oElemento.setPosY(iPosBY);
                    }
                }
                iY ++;
                oElemento.setTamano(iT);
                oElemento.setTipo(iEle);
                lstElementos.add(oElemento);
            }
            iPosEY = iPosEY + 100;
        }
    }
    


    public void paint2(Graphics g){

        //  Imagen de Inicio
        if ( imaInicio != null && bInicio ) {

            //  Dibuja el fondo en la posicion indicada
            g.drawImage( imaInicio.getImage(), 0, 0, this );

            if(bBotonI2)
            {
                g.drawImage( obBotonI2.getImagenI(), obBotonI2.getPosX(),
                    obBotonI2.getPosY(), this);
            }
            else
            {
                g.drawImage( obBotonI1.getImagenI(), obBotonI1.getPosX(),
                    obBotonI1.getPosY(), this );
            }
            //  Dibuja el boton de cargar juego en la pantalla de inicio
            if(bBotonAbrir)
            {
                g.drawImage(obBAbrir2.getImagenI(), obBAbrir2.getPosX(),
                    obBAbrir2.getPosY(), this);
            }
            else
            {
                g.drawImage( obBAbrir1.getImagenI(), obBAbrir1.getPosX(),
                    obBAbrir1.getPosY(), this );
            }
        }
        else
        {
            // GAME OVER - Dibuja pantalla y Botones del Game over
            if(bSalir || iVidas == 0 )
            {
                g.drawImage(iGO.getImage(), 0, 0, this );
                
                if(bBotonS)
                {
                    g.drawImage( obBotonS.getImagenI(), obBotonS.getPosX(),
                    obBotonS.getPosY(), this);
                }
                else
                {
                    g.drawImage( obBotonS2.getImagenI(), obBotonS2.getPosX(),
                    obBotonS2.getPosY(), this );
                }
                
                if(bSalirFrame)
                {
                    g.drawImage( obBotonN.getImagenI(), obBotonN.getPosX(),
                    obBotonN.getPosY(), this);
                }
                else
                {
                    g.drawImage( obBotonN2.getImagenI(), obBotonN2.getPosX(),
                    obBotonN2.getPosY(), this );
                }
                g.setFont(new Font("Helvetica", Font.PLAIN, 28));
                g.setColor(Color.white);
                g.drawString("Puntuacion: " + iPuntuacion, 90, 500 );
            }
            // JUEGO GANADO: Dibuja la pantalla de Victoria y los Botones para
            // salir o reiniciar
            else if(lstElementos.size() == 0)
            {
                scFondo.stop();
                g.drawImage(imaJuegoGanado.getImage(), 0, 5, this);
                obBotonS.setPosX(120);
                obBotonS2.setPosX(120);
                obBotonN.setPosX(220);
                obBotonN2.setPosX(220);
                obBotonS.setPosY(350);
                obBotonS2.setPosY(350);
                obBotonN.setPosY(350);
                obBotonN2.setPosY(350);
                // Boton de SI (Reinicia el juego)
                if(bBotonS)
                {
                    // Imagen normal del boton
                    g.drawImage( obBotonS.getImagenI(), obBotonS.getPosX(),
                    obBotonS.getPosY(), this);
                }
                else
                {
                    // Imagen cuando el raton esta posicionado sobre el boton
                    g.drawImage( obBotonS2.getImagenI(), obBotonS.getPosX(),
                    obBotonS2.getPosY(), this );
                }
                // Boton de NO (Finaliza el juego)
                if(bSalirFrame)
                {
                    // Imagen normal del boton
                    g.drawImage( obBotonN.getImagenI(), obBotonN.getPosX(),
                    obBotonN.getPosY(), this);
                }
                else
                {
                    // Imagen cuando el raton esta posicionado sobre el boton
                    g.drawImage( obBotonN2.getImagenI(), obBotonN2.getPosX(),
                    obBotonN2.getPosY(), this );
                }
            }
            // JUEGO EN PAUSA - Muestra la pantalla de Pausa
            else if(bPausa)
            {
                // Imagen de pausa
                g.drawImage(iPausa.getImage(), 0, 0, this );
            }
            else if(bGuardar)
            {
                g.drawImage(iPausa.getImage(), 0, 0, this );
            }
            else    // PANTALLA DE JUEGO - Despliga todo lo correspondiente a la
                    // pantalla de juego
            {
                g.drawImage( imaEscenario.getImage(), 0, 0, this );
                
                g.setFont(new Font("Helvetica", Font.PLAIN, 20));
                g.setColor(Color.white);
                g.drawString("Puntos: " + iPuntuacion, 925, 575 );
                
                // Dibujar la vida
                if(iVidas == 3)
                {
                    g.drawImage(VidasJugador.getImageVidas3().getImage(), 975, 35, this);
                }
                else if(iVidas == 2)
                {
                    g.drawImage(VidasJugador.getImageVidas2().getImage(), 975, 35, this);
                }
                else if(iVidas == 1)
                {
                    g.drawImage(VidasJugador.getImageVidas1().getImage(), 975, 35, this);
                }
                // Dibuja barra sirena
                g.drawImage(BarSirena.getImageIcon().getImage(),
                BarSirena.getPosX(), BarSirena.getPosY(), this);
                g.drawImage(Pelota1.getImageIcon().getImage(),
                    Pelota1.getPosX(), Pelota1.getPosY(), this);

                //  Imagen elementos
                for (Objeto oElemento : lstElementos) {

                    //  Dibuja los elementos en la posicion actualizada
                    if (oElemento != null) {

                        g.drawImage(oElemento.getImagenI(),
                            oElemento.getPosX(), oElemento.getPosY(), this);
                    }
                }
            }
        }
    }

    // main
    public static void main(String [] args){
        // Crear un Juego
        breakingBricks nGame= new breakingBricks();
    }

    public void run() {
        // Ciclo que mantiene actualizado el juego mientras este se este ejecutando
        while (true)
        {
            actualiza();
            checaColision();
            repaint();
            try
            {
                Thread.sleep(20);       //  El thread se duerme
            }
            catch (InterruptedException ex)
            {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    //  checaColison
    //  Metodo que revisa las colisiones de los objetos
    public void checaColision()
    {
        //  Revisa colisiones entre el objeto BarSirena y los limites de la pantalla
        if (BarSirena.getPosX() < 0) {
            BarSirena.setPosX(0);
        }
        if (BarSirena.getPosX() + BarSirena.getAncho() > getWidth()) {
            BarSirena.setPosX(getWidth() - BarSirena.getAncho());
        }

        // Reviza todas las colisiones posibles de la pelota con la barra
        if(BarSirena.intersectaParte1Izquierda(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(-2*iVelocidad);
            Pelota1.setVelocidadY(-1*iVelocidad);
        }
        if(BarSirena.intersectaParte2Izquierda(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(-2*iVelocidad);
            Pelota1.setVelocidadY(-2*iVelocidad);
        }
        else if(BarSirena.intersectaParte3Izquierda(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(-1*iVelocidad);
            Pelota1.setVelocidadY(-2*iVelocidad);
        }
        else if(BarSirena.intersectaCentro(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(0*iVelocidad);
            Pelota1.setVelocidadY(-2*iVelocidad);
        }
        else if(BarSirena.intersectaParte1Derecha(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(1*iVelocidad);
            Pelota1.setVelocidadY(-2*iVelocidad);
        }
        else if(BarSirena.intersectaParte2Derecha(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(2*iVelocidad);
            Pelota1.setVelocidadY(-2*iVelocidad);
        }
        else if(BarSirena.intersectaParte3Derecha(Pelota1))
        {
            scBarra.play();
            Pelota1.setVelocidadX(2*iVelocidad);
            Pelota1.setVelocidadY(-1*iVelocidad);
        }
        
        // Cambia la direccion de la pelota cuando esta esta en los limites de
        // la pantalla
        // LIMITE IZQUIERDO
        if(Pelota1.getPosX() <= 0)
        {
            Pelota1.reverseX();
        }
        // LIMITE SUPERIOR
        if(Pelota1.getPosY() <= 20)
        {
            Pelota1.reverseY();
        }
        // LIMITE DERECHO
        if(Pelota1.getPosX() + Pelota1.getAncho() >= getWidth())
        {
            Pelota1.reverseX();
        }
        // Reviza cuando la pelota cae
        if(Pelota1.getPosY() > 570)
        {
            iVidas--;                       // Se resta una vida
            iVelocidad = 2;                 // Se reinicia la velocidad a 2
            Pelota1.setPosX(iPosBIX+47);    // Se reinicia la posicion de la Pelota
            Pelota1.setPosY(iPosBIY-100);
            Pelota1.setVelocidadX(0);       // Se cambia la direccion de la pelota
            Pelota1.setVelocidadY(2);
            BarSirena.setPosX(iPosBIX);     // Se mueve la barra al centro
        }
        
        // Comprobar si hay colision de la pelota con cada una de las cuatro
        // caras de las cajas de Elementos
        for(int iC = 0; iC < lstElementos.size(); iC++)
        {
            boolean bChoco = false;
            // Si la pelota choca con alguna caja en la parte superior o
            // inferior, remover la caja e invertir la direccion Y
            if(lstElementos.get(iC).intersectaBase(Pelota1)
                    || lstElementos.get(iC).intersectaLadoSuperior(Pelota1))
            {
                lstElementos.remove(iC);
                Pelota1.reverseY();
                iCajasEliminadas++;
                bChoco = true;
                scCaja.play();
            }
            // Si la pelota choca con alguna caja en la parte derecha o
            // izquierda, remover la caja e invertir la direccion X
            else if(lstElementos.get(iC).intersectaLadoDerecho(Pelota1)
                    || lstElementos.get(iC).intersectaLadoIzquierdo(Pelota1))
            {
                lstElementos.remove(iC);
                Pelota1.reverseX();
                iCajasEliminadas++;
                bChoco = true;   
                scCaja.play();
            }            
            if(bChoco)
            {
                iPuntuacion = iPuntuacion + 10;
                bChoco = false;
            }
        }
        
        // Si las cajas eliminadas superan las 12, aumentar la velocidad
        if(iCajasEliminadas >= 12)
        {
            iVelocidad++;
            iCajasEliminadas = 0;
        }
    }

    //  Funcion que guarda la posicion de los Elementos
    public void posElementos(){
        iPosElementos = new int[lstElementos.size()][3];
        for(int iC = 0; iC < lstElementos.size(); iC++)
        {
            Objeto oElemento = (Objeto) lstElementos.get(iC);
            
            iPosElementos[iC][0] = oElemento.getPosX();
            iPosElementos[iC][1] = oElemento.getPosY();
            iPosElementos[iC][2] = oElemento.getTipo();
        }
    }

    // Funcion: Actualiza
    // Metodo que actualiza la posicion de los objetos
    public void actualiza() {
        
        // Movimiento de la Barra
        if(iMov == 1)
        {
            BarSirena.setPosX(BarSirena.getPosX() + 8);
        }
        else if(iMov == 2)
        {
            BarSirena.setPosX(BarSirena.getPosX() - 8);
        }

        // Detiene el movimiento de la pelota cuando sea necesario
        // 1. Cuando el juego esta en pausa
        // 2. Cuando el juego esta en la pantalla de inicio
        // 3. Cuando el juego esta en la pantalla de salida
        // 4. Cuando el jugador pierde todas sus vidas
        // 5. Cuando se gano el juego
        // 6. Se esta guardando la partida
        if(!bPausa && !bInicio && !bSalir && iVidas > 0 && !(lstElementos.size() == 0) && !bGuardar)
        {
            // Movimiento de la Pelota
            Pelota1.setPosX(Pelota1.getPosX() + Pelota1.getVelocidadX());
            Pelota1.setPosY(Pelota1.getPosY() + Pelota1.getVelocidadY());
        }
        // Reinicia todos los parametros cuando el jugador lo indica
        if(bReiniciar)
        {
            init();
        }
        // Cierra la ejecucion del programa
        if(bCerrar)
        {
            System.exit(0);     // Cierra la aplicacion
        }
        if(bGuardar)
        {
            try {
                    GuardarPartida();
                } catch(IOException e) {

                      System.out.println("Error en " + e.toString());
                }
            bGuardar = false;
        }
    }

    public void GuardarPartida() throws IOException
    {
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        PrintWriter ArchivoEscritura = new PrintWriter(new FileWriter(sNombreArchivo));
        ArchivoEscritura.println(nombre);
        ArchivoEscritura.println(lstElementos.size());
        ArchivoEscritura.println(iVidas);
        ArchivoEscritura.println(iVelocidad);
        ArchivoEscritura.println(iPuntuacion);
        ArchivoEscritura.println(Pelota1.getPosX() + " " + Pelota1.getPosY());
        ArchivoEscritura.println(Pelota1.getVelocidadX() + " " + Pelota1.getVelocidadY());
        ArchivoEscritura.println(BarSirena.getPosX());
        // Guarda todas las posiciones de las cajas en pantalla
        for(int iC = 0; iC < lstElementos.size(); iC++)
        {
            ArchivoEscritura.println(iPosElementos[iC][0] + " " +
                    iPosElementos[iC][1] + " " + iPosElementos[iC][2]);
        }
        ArchivoEscritura.println("---");
        ArchivoEscritura.close();
        JOptionPane.showMessageDialog(null, "Juego Guardado", "", 
            JOptionPane.PLAIN_MESSAGE);
    }
    
    public void leeArchivo() throws IOException {                                                      
            BufferedReader fileIn;
            try {
                    fileIn = new BufferedReader(new FileReader(sNombreArchivo));
            } catch (FileNotFoundException e){
                    File PartidasGuardadas = new File(sNombreArchivo);
                    PartidasGuardadas.createNewFile();
                    //PrintWriter fileOut = new PrintWriter(PartidasGuardadas);
                    //fileOut.println("");
                    //fileOut.close();
                    fileIn = new BufferedReader(new FileReader(sNombreArchivo));
            }
            String sLinea = fileIn.readLine();
            if(sLinea == null)
            {
                bAbrir = false;
            }
            // Cargar datos
            while(sLinea != null) {
                lstElementos.clear();   // Se limpia la lista de elementos
                String sNombre = sLinea;    // Se guarda el nombre
                System.out.println(sNombre);
                // Numero de elementos
                int iElementos = Integer.parseInt(fileIn.readLine());
                System.out.println("Elementos: " + iElementos);
                iVidas = Integer.parseInt(fileIn.readLine());
                iVelocidad = Integer.parseInt(fileIn.readLine());
                iPuntuacion = Integer.parseInt(fileIn.readLine());
                sLinea = fileIn.readLine();
                int iPos = sLinea.indexOf(" ");
                // Asigna la posicion de la pelota
                Pelota1.setPosX(Integer.parseInt(sLinea.substring(0, iPos)));
                Pelota1.setPosY(Integer.parseInt(sLinea.substring(iPos + 1)));
                sLinea = fileIn.readLine();
                iPos = sLinea.indexOf(" ");
                String iVelocidadX = sLinea.substring(0, iPos);
                String iVelocidadY = sLinea.substring(iPos + 1);
                if(iVelocidadX.contains("-"))
                {
                    Pelota1.setVelocidadX(-1*(Integer.parseInt(iVelocidadX.substring(1))));
                }
                else{
                    Pelota1.setVelocidadX(Integer.parseInt(iVelocidadX));
                }
                if(iVelocidadY.contains("-"))
                {
                    Pelota1.setVelocidadY(-1*(Integer.parseInt(iVelocidadY.substring(1))));
                }
                else{
                    Pelota1.setVelocidadY(Integer.parseInt(iVelocidadY));
                }
                BarSirena.setPosX(Integer.parseInt(fileIn.readLine()));

                // Direcciones de las imagenes de los diferentes elementos
                URL urlEle1 = this.getClass().getResource("e1.png");
                URL urlEle2 = this.getClass().getResource("e2.png");
                URL urlEle3 = this.getClass().getResource("e3.png");
                URL urlEle4 = this.getClass().getResource("e4.png");
                URL urlEle5 = this.getClass().getResource("e5.png");
                
                int iPosX, iPosY, iTipo;

                // Asigna la posicion e imagen correspondiente a cada uno de los
                // elementos
                for(int iC = 0; iC < iElementos; iC++)
                {
                    sLinea = fileIn.readLine();
                    iPos = sLinea.indexOf(" ");
                    iPosX = Integer.parseInt(sLinea.substring(0, iPos));
                    sLinea = sLinea.substring(iPos + 1);
                    iPos = sLinea.indexOf(" ");
                    iPosY = Integer.parseInt(sLinea.substring(0, iPos));
                    iTipo = Integer.parseInt(sLinea.substring(iPos + 1));
                    System.out.println(iPosX + "|" + iPosY + "|" + iTipo);
                    Objeto ElementoX;
                    
                    if(iTipo == 1)
                    {
                        //  Se crea un nuevo objeto de tipo 1
                        ElementoX = new Objeto(iPosX,iPosY,Toolkit.getDefaultToolkit()
                        .getImage(urlEle1));
                    }
                    else if(iTipo == 2)
                    {
                        //  Se crea un nuevo objeto de tipo 2
                        ElementoX = new Objeto(iPosX,iPosY,Toolkit.getDefaultToolkit()
                        .getImage(urlEle2) );
                    }
                    else if(iTipo == 3)
                    {
                        //  Se crea un nuevo objeto de tipo 3
                        ElementoX = new Objeto(iPosX,iPosY,Toolkit.getDefaultToolkit()
                        .getImage(urlEle3));
                    }
                    else if(iTipo == 4)
                    {
                        //  Se crea un nuevo objeto de tipo 4
                        ElementoX = new Objeto(iPosX,iPosY,Toolkit.getDefaultToolkit()
                        .getImage(urlEle4) );
                    }
                    else
                    {
                        //  Se crea un nuevo objeto de tipo 5
                        ElementoX = new Objeto(iPosX,iPosY,Toolkit.getDefaultToolkit()
                        .getImage(urlEle5) );
                    }
                    lstElementos.add(ElementoX);
                }
                sLinea = null;
            }
            fileIn.close();
    }

    public void paint(Graphics g) {
            // Inicializan el DoubleBuffer
            if (imaImageDB == null){
                    imaImageDB = createImage (this.getSize().width, this.getSize().height);
                    graGraficoDB = imaImageDB.getGraphics ();
            }

            // Actualiza la imagen de fondo.
            graGraficoDB.setColor(getBackground ());
            graGraficoDB.fillRect(0, 0, this.getSize().width, this.getSize().height);

            // Actualiza el Foreground.
            graGraficoDB.setColor(getForeground());
            paint2(graGraficoDB);

            // Dibuja la imagen actualizada
            g.drawImage (imaImageDB, 0, 0, this);
    }

    // Funcion que determina si se colisiona con el Boton de Inicio
    public boolean colisiona(int iX, int iY) {
        Rectangle rctSoy;
        rctSoy = new Rectangle(obBotonI1.getPosX(), obBotonI1.getPosY(),
                obBotonI1.getAncho(), obBotonI1.getAlto());
        return rctSoy.contains(iX,iY);
    }

    // Funcion que determina si se colisiona con el Boton de SI
    public boolean colisiona2(int iX, int iY) {
        Rectangle rctSoy;
        rctSoy = new Rectangle(obBotonS.getPosX(), obBotonS.getPosY(),
                obBotonS.getAncho(), obBotonS.getAlto());
        return rctSoy.contains(iX,iY);
    }
    
    // Funcion que determina si se colisiona con el Boton de NO
    public boolean colisiona3(int iX, int iY) {
        Rectangle rctSoy;
        rctSoy = new Rectangle(obBotonN.getPosX(), obBotonN.getPosY(),
                obBotonN.getAncho(), obBotonN.getAlto());
        return rctSoy.contains(iX,iY);
    }
    
    // Funcion que determina si se colisiona con el Boton de Cargar/Abrir
    public boolean colisiona4(int iX, int iY)
    {
        Rectangle rctSoy;
        rctSoy = new Rectangle(obBAbrir1.getPosX(), obBAbrir1.getPosY(),
                obBAbrir1.getAncho(), obBAbrir1.getAlto());
        return rctSoy.contains(iX,iY);
    }
    
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if(colisiona(e.getX(), e.getY()))
        {
            bInicio = false;
        }
        if(colisiona2(e.getX(), e.getY()) && bBotonS)
        {
            bSalir = false;
            bReiniciar = true;
        }
        if(colisiona3(e.getX(), e.getY()) && bSalirFrame)
        {
            bCerrar = true;
        }
        if(colisiona4(e.getX(), e.getY()))
        {
            bAbrir = true;
            // CARGA DE DATOS DE LA PARTIDA GUARDADA
            try
            {
                leeArchivo();    //lee el contenido del archivo
            } catch(IOException ex) {
                System.out.println("Error en " + ex.toString());
            }
            if(!bAbrir)
            {
                JOptionPane.showMessageDialog(null,
                    "No hay partida Guardada","", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                bInicio = false;
                bPausa = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

        if(colisiona(e.getX(), e.getY()))
        {
            bBotonI2 = true;
        }
        else
        {
            bBotonI2 = false;
        }
        
        if(colisiona2(e.getX(), e.getY()))
        {
            bBotonS = true;
        }
        else
        {
            bBotonS = false;
        }
        
        if(colisiona3(e.getX(), e.getY()))
        {
            bSalirFrame = true;
        }
        else
        {
            bSalirFrame = false;
        }
        
        if(colisiona4(e.getX(), e.getY()))
        {
            bBotonAbrir = true;
        }
        else
        {
            bBotonAbrir = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        // En iValor se guarda el identificador de la tecla que es presionada
        int iValor = e.getKeyCode();

        switch(iValor) {

            // Se verifica si la tecla presionada es una de las direcciones
            // O la tecla de pausa, o de fin
            // DERECHA
            case KeyEvent.VK_RIGHT:
                iMov = 1;
                break;
            // IZQUIERDA
            case KeyEvent.VK_LEFT:
                iMov = 2;
                break;
            // PAUSA
            case KeyEvent.VK_P:
                bPausa = !bPausa;
                break;
            // GUARDAR PARTIDA
            case KeyEvent.VK_G:
                bGuardar = true;
                posElementos();
                break;
            // ESC - Para salir del juego
            case KeyEvent.VK_ESCAPE:
                bSalir = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        iMov = 0;
    }
}
