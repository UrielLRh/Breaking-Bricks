import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Pelota{
    
        private int iX;               //posicion en x.       
	private int iY;               //posicion en y.
        private int iVelocidadX;
        private int iVelocidadY;
	private ImageIcon imaIcon;    //imaIcon.
        
        
        public Pelota(int iX, int iY, int iVelX, int iVelY, Image image){
		this.iX=iX;
		this.iY=iY;
                iVelocidadX = iVelX;
                iVelocidadY = iVelY;
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
        
        public Rectangle getPerimetro(){
		return new Rectangle((getPosX() + getAncho()/2), getPosY(), getAncho() + 2, getAlto());
	}

        public void setVelocidadX(int iVelX)
        {
            iVelocidadX = iVelX;
        }
        public int getVelocidadX()
        {
            return iVelocidadX;
        }
        public void setVelocidadY(int iVelY)
        {
            iVelocidadY = iVelY;
        }
        public int getVelocidadY()
        {
            return iVelocidadY;
        }
        
        public void reverseX()
        {
            iVelocidadX *= -1;
        }
        public void reverseY()
        {
            iVelocidadY *= -1;
        }

}
