package GUI;
import javax.swing.*;
import AssetsHandler.iconScaller;
import java.awt.*;
import Cipher.EndToEnd.EndToEndPanel;
import Cipher.Vernam.VernamPanel;
import Cipher.cesarCipher.cesarPanel;
import Cipher.playFair.playPanel;
import Cipher.simpleTransposition.transPanel;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;
public class Menu extends JFrame {
    private final CardLayout cardLayout;
    private final NewUIComp newUIComp = new NewUIComp();
    public Menu() {
        super("Cipher");
        setSize(1200, 900);
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

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridBagLayout());


        JLabel instruc = new JLabel("Seleccione la opción de cifrado");
        instruc.setForeground(Color.BLACK);
        instruc.setFont(Fonts.SourceSansPro18);
        instruc.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(instruc);

        centro.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton transCipher= newUIComp.newButton("Transposición simple", "/Assets/Spin.png");
        transCipher.setAlignmentX(Component.CENTER_ALIGNMENT);
        transCipher.setMaximumSize(new Dimension(300, 40));
        transCipher.addActionListener(e ->  {cardLayout.show(centroCard,"transPanel");});
        centro.add(transCipher);

        centro.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton cesarCipher= newUIComp.newButton("Cifrado de cesar", "/Assets/Cesar.png");
        cesarCipher.setAlignmentX(Component.CENTER_ALIGNMENT);
        cesarCipher.setMaximumSize(new Dimension(300, 40));
        cesarCipher.addActionListener(e -> {cardLayout.show(centroCard,"cesarPanel");});
        centro.add(cesarCipher);

        centro.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton playCipher= newUIComp.newButton("Cifrado de PlayFair", "/Assets/PlayFair.png");
        playCipher.setAlignmentX(Component.CENTER_ALIGNMENT);
        playCipher.setMaximumSize(new Dimension(300, 40));
        playCipher.addActionListener(e -> {cardLayout.show(centroCard,"playPanel");});
        centro.add(playCipher);

        centro.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton vernamCipher = newUIComp.newButton("Cifrado de Vernam", "/Assets/Vernam.png");
        vernamCipher.setAlignmentX(Component.CENTER_ALIGNMENT);
        vernamCipher.addActionListener(e -> {vernamLaunch(centroCard);});
        vernamCipher.setMaximumSize(new Dimension(300, 40));
        centro.add(vernamCipher);

        centro.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton EndToEnd= newUIComp.newButton("Cifrado Afín", "/Assets/Afin.png");
        EndToEnd.addActionListener(e->cardLayout.show(centroCard,"endtoendPanel"));
        EndToEnd.setToolTipText("Cifrado Afín");
        EndToEnd.setAlignmentX(Component.CENTER_ALIGNMENT);
        EndToEnd.setMaximumSize(new Dimension(300, 40));
        centro.add(EndToEnd);

        centro.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton RSA= newUIComp.newButton("Algoritmo RSA", "/Assets/RSA.png");
        RSA.setToolTipText("Algoritmo RSA no esta disponible");
        RSA.setForeground(Color.RED);
        RSA.setEnabled(false);
        RSA.setAlignmentX(Component.CENTER_ALIGNMENT);
        RSA.setMaximumSize(new Dimension(300, 40));
        centro.add(RSA);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel Help= new JLabel("Ayuda");
        Help.setToolTipText("Ayuda");
        Help.setFont(Fonts.SourceSansPro18);
        Help.setForeground(Color.BLACK);
        Help.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lowerPanel.add(Help, gbc);

        centroCard.add(centro, "instruc");
        JPanel transPanel = new transPanel(cardLayout);
        JPanel cesarPanel = new cesarPanel(cardLayout);
        JPanel playPanel = new playPanel(cardLayout);
        JPanel vernamPanel= new VernamPanel(cardLayout);
        JPanel endtoendPanel= new EndToEndPanel(cardLayout);

        centroCard.add(cesarPanel, "cesarPanel");
        centroCard.add(transPanel, "transPanel");
        centroCard.add(playPanel, "playPanel");
        centroCard.add(vernamPanel, "vernamPanel");
        centroCard.add(endtoendPanel, "endtoendPanel");
        add(centroCard, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);


        setVisible(true);
    }
    private void vernamLaunch(JPanel centroCard){
        if(JOptionPane.showConfirmDialog(this.getParent(),"Advertencia: El cifrado de Vernam funciona por medio del código de Baudot y encripta en Binario a diferencia del tradicional que funciona en ASCII y encripta en texto, debido a la limitación de caracteres del codigo de Baudot el cifrado sera mostrado en binario")==0){
            cardLayout.show(centroCard,"vernamPanel");
        }
    }
}
