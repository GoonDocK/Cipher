package GUI;
import javax.swing.*;
import AssetsHandler.iconScaller;
import java.awt.*;
import java.awt.event.ActionListener;

import Cipher.simpleTransposition.transPanel;
import Miscellaneous.Fonts;
public class Menu extends JFrame {
    private final CardLayout cardLayout;
    public Menu() {
        super("Cipher");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setIconImage(iconScaller.scale("/Assets/Cipher.png", 64, 64).getImage());
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK);
        JLabel title = new JLabel("Bill Cipher");
        title.setIcon(iconScaller.scale("/Assets/Bill.png", 64, 64));
        title.setForeground(Color.WHITE);
        title.setFont(Fonts.SourceSansPro25);
        panel.add(title);

        JPanel centroCard= new JPanel();
        cardLayout = new CardLayout();
        centroCard.setLayout(cardLayout);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instruc = new JLabel("Seleccione la opción de cifrado");
        instruc.setForeground(Color.BLACK);
        instruc.setFont(Fonts.SourceSansPro18);
        instruc.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(instruc);

        centro.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton cifrar = new JButton("Transposición simple");
        cifrar.setFont(Fonts.SourceSansPro18Bold);
        cifrar.setForeground(Color.WHITE);
        cifrar.setBackground(Color.BLACK);
        cifrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        cifrar.setIcon(iconScaller.scale("/Assets/Spin.png", 32, 32));
        cifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardLayout.show(centroCard,"transPanel");
            }
        });
        centro.add(cifrar);


        centroCard.add(centro, "instruc");
        JPanel transPanel = new transPanel(cardLayout);

        centroCard.add(transPanel, "transPanel");
        add(centroCard, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);


        setVisible(true);
    }
}
