package AssetsHandler;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
public class iconScaller {
     public static ImageIcon scale(String path, int width, int height){
        URL url = iconScaller.class.getResource(path);
        if(url == null){
            JOptionPane.showMessageDialog(null, "No se pudo cargar uno o mas iconos");
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image img= icon.getImage();
        Image newimg=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
