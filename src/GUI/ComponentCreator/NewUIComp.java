package GUI.ComponentCreator;
import AssetsHandler.iconScaller;
import Miscellaneous.Fonts;
import javax.swing.*;
import java.awt.*;

public class NewUIComp {
    public JButton newButton(String name, String Icon){
        JButton button = new JButton(name);
        button.setIcon(iconScaller.scale(Icon, 32, 32));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(Fonts.SourceSansPro18Bold);
        return button;
    }
    public JPanel matrixComponent(String i, int w, int h){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(i);
        panel.add(label);
        panel.setPreferredSize(new Dimension(50, 50));
        panel.setMinimumSize(new Dimension(50, 50));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setFont(Fonts.SourceSansPro18Bold);
        return panel;
    }
}
