/**
 * Clase Objeto
 *
 * @author
 * @version 1.00 2015/9/2
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Objeto {
	
        //  Declaracion de variables
        //  iPosX -> posicion en X del objeto
        //  iPosY -> posicion en Y del objeto
        //  imaIcoObjeto -> imagen del objeto
	private int iPosX;        
	private int iPosY;
        private int iTipo;
	private ImageIcon imaIcoObjeto;
        private int iCant;
        private int iTamano;
	
	/**
	 * Metodo constructor usado para crear el objeto
	 * @param iPosX es la <code>posicion en x</code> del objeto.
	 * @param iPosY es la <code>posicion en y</code> del objeto.
	 * @param imaImagen es la <code>imagen</code> del objeto.
	 */
	public Objeto(int iPosX, int iPosY ,Image imaImagen) {
            this.iPosX=iPosX;
            this.iPosY=iPosY;
            imaIcoObjeto = new ImageIcon(imaImagen);
            iCant = 0;
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en x del objeto 
	 * @param iPosX es la <code>posicion en x</code> del objeto.
	 */
	public void setPosX(int iPosX) {
            this.iPosX = iPosX;
	}
	
        public void setiCant(int iCant) {
            this.iCant = iCant;
	}
        
        public void setTipo(int iTipo) {
            this.iTipo = iTipo;
	}
        public int getTipo() {
            return iTipo;
	}
        
        public void setTamano(int iTamano) {
            this.iTamano = iTamano;
	}
        public int getTamano() {
            return iTamano;
	}
        
	/**
	 * Metodo de acceso que regresa la posicion en x del objeto 
	 * @return iPosX es la <code>posicion en x</code> del objeto.
	 */
	public int getPosX() {
            return iPosX;
	}
        
        public int getiCant() {
            return iCant;
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en y del objeto 
	 * @param iPosY es la <code>posicion en y</code> del objeto.
	 */
	public void setPosY(int iPosY) {
            this.iPosY = iPosY;
	}
	
	/**
	 * Metodo de acceso que regresa la posicion en y del objeto 
	 * @return iPosY es la <code>posicion en y</code> del objeto.
	 */
	public int getPosY() {
            return iPosY;
	}
	
	/**
	 * Metodo modificador usado para cambiar el imaIcoObjeto del objeto 
	 * @param imaIcoObjeto es el <code>imaIcoObjeto</code> del objeto.
	 */
	public void setImageIcon(ImageIcon imaIcoObjeto) {
            this.imaIcoObjeto = imaIcoObjeto;
	}
	
	/**
	 * Metodo de acceso que regresa el imaIcoObjeto del objeto 
	 * @return imaIcoObjeto es el <code>imaIcoObjeto</code> del objeto.
	 */
	public ImageIcon getImageIcon() {
            return imaIcoObjeto;
	}
	
	/**
	 * Metodo de acceso que regresa el ancho del imaIcoObjeto 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del imaIcoObjeto.
	 */
	public int getAncho() {
            return imaIcoObjeto.getIconWidth();
	}
	
	/**
	 * Metodo de acceso que regresa el alto del imaIcoObjeto 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el alto del imaIcoObjeto.
	 */
	public int getAlto() {
            return imaIcoObjeto.getIconHeight();
	}
	
	/**
	 * Metodo de acceso que regresa la imagen del imaIcoObjeto 
	 * @return un objeto de la clase <code>Image</code> que es la imagen del imaIcoObjeto.
	 */
	public Image getImagenI() {
            return imaIcoObjeto.getImage();
	}
	
	/**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPerimetro(){
            return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
	
	/**
	 * Checa si el objeto <code>Objeto</code> intersecta a otro <code>Objeto</code>
	 *
	 * @return un valor boleano <code>true</code> si lo intersecta <code>false</code>
	 * en caso contrario
	 */
	public boolean intersecta(Objeto objObjeto){
            
            Rectangle rctSoy;
            rctSoy = new Rectangle(getPosX(), getPosY(), getAncho(),getAlto());
            
            //  Obtengo las posiciones de las ezquinas del objeto que voy a 
            //  colisionar
            int iPos1X = objObjeto.getPosX();
            int iPos1Y = objObjeto.getPosY() + objObjeto.getAlto();
            int iPos2Y = objObjeto.getPosY() + objObjeto.getAlto();
            int iPos2X = objObjeto.getPosX() + objObjeto.getAncho();
            int iPos3X = objObjeto.getPosX() + objObjeto.getAncho();
            int iPos3Y = objObjeto.getPosY();
            int iPos4X = objObjeto.getPosX();
            int iPos4Y = objObjeto.getPosY();
            
            //  Si el objeto intersecta al otro objeto por la parte inferior
            //  retorna true, en caso contrario false
            if(rctSoy.contains(iPos3X, iPos3Y) && rctSoy.contains(iPos2X,iPos2Y))
            {
                return false;
            }
            if(rctSoy.contains(iPos4X, iPos4Y) && rctSoy.contains(iPos1X,iPos1Y))
            {
                return false;
            }
            if(rctSoy.contains(iPos4X, iPos4Y) && rctSoy.contains(iPos3X,iPos3Y))
            {
                return false;
            }
            if(rctSoy.contains(iPos1X,iPos1Y) && rctSoy.contains(iPos2X,iPos2Y))
            {
                return true;
            }
           
            return false;
	}
        
        public boolean intersectaArriba(Objeto objObjeto){
            
            Rectangle rctSoy;
            rctSoy = new Rectangle(getPosX(), getPosY(), getAncho(),getAlto());
            
            //  Obtengo las posiciones de las ezquinas del objeto que voy a 
            //  colisionar
            int iPos3X = objObjeto.getPosX() + objObjeto.getAncho();
            int iPos3Y = objObjeto.getPosY();
            int iPos4X = objObjeto.getPosX();
            int iPos4Y = objObjeto.getPosY();
            
            //  Si el objeto intersecta al otro objeto por la parte inferior
            //  retorna true, en caso contrario false
            if(rctSoy.contains(iPos4X, iPos4Y) && rctSoy.contains(iPos3X,iPos3Y))
            {
                return true;
            }
           
            return false;
	}
        
        // Funcion que regresa la base del Objeto
        public Rectangle getBase(){
            return new Rectangle(getPosX(), getPosY() + getAlto() - 1, getAncho(), 1);
	}
        // Funcion que regresa el lado derecho del Objeto
        public Rectangle getLadoDerecho(){
            return new Rectangle(getPosX() + getAncho(), getPosY(), 1, getAlto());
	}
        // Funcion que regresa el lado izquierdo del Objeto
        public Rectangle getLadoIzquierdo(){
            return new Rectangle(getPosX(), getPosY(), 1, getAlto());
	}
        // Funcion que regresa el lado superior del Objeto
        public Rectangle getLadoSuperior(){
            return new Rectangle(getPosX(), getPosY(), getAncho(), 1);
	}
        // Funcion que determina si hay una colision por el lado inferior(Base)
        public boolean intersectaBase(Pelota obj){
            return getBase().intersects(obj.getPerimetro());
        }
        // Determina si ha habido una colision por el lado derecho
        public boolean intersectaLadoDerecho(Pelota obj){
            return getLadoDerecho().intersects(obj.getPerimetro());
        }
        // Determina si ha habido una colision por el lado izquierdo
        public boolean intersectaLadoIzquierdo(Pelota obj){
            return getLadoIzquierdo().intersects(obj.getPerimetro());
        }
        // Determina si ha habido una colision por el lado superior
        public boolean intersectaLadoSuperior(Pelota obj){
            return getLadoSuperior().intersects(obj.getPerimetro());
        }
	
}