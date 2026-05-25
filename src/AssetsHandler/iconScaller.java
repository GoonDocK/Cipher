package AssetsHandler;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
public class iconScaller {
     public static ImageIcon scale(String path, int width, int height){
        URL url = iconScaller.class.getResource(path);
        if(url == null){
            throw new RuntimeException("No se pudo cargar la imagen");
        }
        ImageIcon icon = new ImageIcon(url);
        Image img= icon.getImage();
        Image newimg=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
    public static ImageIcon sprite(String path){
        URL url = iconScaller.class.getResource(path);
        if(url == null){
            throw new RuntimeException("No se pudo cargar la imagen");
        }
        ImageIcon icon = new ImageIcon(url);
        Image img= icon.getImage();
        Image newimg=img.getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
