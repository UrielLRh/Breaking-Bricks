import java.awt.Image;
import javax.swing.ImageIcon;


public class Vidas {
    private int iX, iY;                 // Cordenadas
    private int iCantVidas;             // Cantidad de Vidas
    private ImageIcon imaVidas3;        // Imagen de 3 Vidas
    private ImageIcon imaVidas2;        // Imagen de 2 Vidas
    private ImageIcon imaVidas1;        // Imagen de 1 Vida
    
    public Vidas(int iX, int iY, int iVidas, Image image1, Image image2, Image image3)
    {
        this.iX=iX;
        this.iY=iY;
	imaVidas3 = new ImageIcon(image3);
        imaVidas2 = new ImageIcon(image2);
        imaVidas1 = new ImageIcon(image1);
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

    public ImageIcon getImageVidas3() {
        return imaVidas3;
    }
    public ImageIcon getImageVidas2() {
        return imaVidas2;
    }
    public ImageIcon getImageVidas1() {
        return imaVidas1;
    }
}