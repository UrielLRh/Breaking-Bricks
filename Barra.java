import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Barra{
    
        private int iX;               //posicion en x.       
	private int iY;               //posicion en y.
	private ImageIcon imaIcon;    //imaIcon.
        
        
        public Barra(int iX, int iY ,Image image){
		this.iX=iX;
		this.iY=iY;
		imaIcon = new ImageIcon(image);
	}
        
        public void setPosX(int iX) {
		this.iX = iX;
	}
    
        public void setPosY(int iY) {
		this.iY = iY;
	}
        
        public int getPosX() {
		return iX;
	}
        
        public int getPosY() {
		return iY;
	}
        
        public ImageIcon getImageIcon() {
		return imaIcon;
	}
        
        public int getAlto() {
		return imaIcon.getIconHeight();
	}
        
        public int getAncho() {
		return imaIcon.getIconWidth();
	}
        
        public Rectangle getParte1Izquierda(){
		return new Rectangle(getPosX(),getPosY(),24,1);
	}
        public Rectangle getParte2Izquierda(){
		return new Rectangle(getPosX() + 24,getPosY(),24,1);
	}
        public Rectangle getParte3Izquierda(){
		return new Rectangle(getPosX() + 48,getPosY(),24,1);
	}
        public Rectangle getParteCentro(){
		return new Rectangle(getPosX() + 72,getPosY(),24,1);
	}
        public Rectangle getParte1Derecha(){
		return new Rectangle(getPosX() + 96,getPosY(),24,1);
	}
        public Rectangle getParte2Derecha(){
		return new Rectangle(getPosX() + 120,getPosY(),24,1);
	}
        public Rectangle getParte3Derecha(){
		return new Rectangle(getPosX() + 144,getPosY(),24,1);
	}
        
        public boolean intersectaParte1Izquierda(Pelota obj){
            return getParte1Izquierda().intersects(obj.getPerimetro());
        }
        public boolean intersectaParte2Izquierda(Pelota obj){
            return getParte2Izquierda().intersects(obj.getPerimetro());
        }
        public boolean intersectaParte3Izquierda(Pelota obj){
            return getParte3Izquierda().intersects(obj.getPerimetro());
        }
        public boolean intersectaCentro(Pelota obj){
            return getParteCentro().intersects(obj.getPerimetro());
        }
        public boolean intersectaParte1Derecha(Pelota obj){
            return getParte1Derecha().intersects(obj.getPerimetro());
        }
        public boolean intersectaParte2Derecha(Pelota obj){
            return getParte2Derecha().intersects(obj.getPerimetro());
        }
        public boolean intersectaParte3Derecha(Pelota obj){
            return getParte3Derecha().intersects(obj.getPerimetro());
        }
}
