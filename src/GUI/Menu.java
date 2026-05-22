package GUI;
import javax.swing.*;
import AssetsHandler.iconScaller;
import java.awt.*;
import Cipher.EndToEnd.EndToEndPanel;
import Cipher.RSA.RSAPanel;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


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

        JButton cesarCipher= newUIComp.newButton("Cifrado de Cesar", "/Assets/Cesar.png");
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
        RSA.addActionListener(e -> cardLayout.show(centroCard,"rSAPanel"));
        RSA.setToolTipText("Algoritmo RSA no esta disponible");
        RSA.setForeground(Color.RED);
        RSA.setAlignmentX(Component.CENTER_ALIGNMENT);
        RSA.setMaximumSize(new Dimension(300, 40));
        centro.add(RSA);

        centro.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton credits= newUIComp.newButton("Info", "/Assets/Code.png");
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits.setMaximumSize(new Dimension(300, 40));
        centro.add(credits);

        centro.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exit= newUIComp.newButton("Salir", "/Assets/Exit.png");
        exit.addActionListener(e -> System.exit(0));
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(300, 40));
        centro.add(exit);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel Help= new JLabel("Ayuda", SwingConstants.CENTER);
        Help.setToolTipText("Ayuda");
        Help.setFont(Fonts.SourceSansPro18);
        Help.setForeground(Color.BLACK);
        Help.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.gridwidth=3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        lowerPanel.add(Help, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridwidth=1;

        JButton helpTrans= newUIComp.newButton("", "/Assets/Spin.png");
        gbc.gridy=1;
        lowerPanel.add(helpTrans, gbc);

        JButton helpCesar= newUIComp.newButton("", "/Assets/Cesar.png");
        gbc.gridx=1;
        lowerPanel.add(helpCesar, gbc);

        JButton helpPlay= newUIComp.newButton("", "/Assets/PlayFair.png");
        gbc.gridx=2;
        lowerPanel.add(helpPlay, gbc);

        gbc.gridx=0;

        JButton helpVernam= newUIComp.newButton("", "/Assets/Vernam.png");
        gbc.gridy=2;
        lowerPanel.add(helpVernam, gbc);

        JButton helpEndtoend= newUIComp.newButton("", "/Assets/Afin.png");
        gbc.gridx=1;
        lowerPanel.add(helpEndtoend, gbc);

        JButton helpRSA= newUIComp.newButton("", "/Assets/RSA.png");
        gbc.gridx=2;
        lowerPanel.add(helpRSA, gbc);



        centroCard.add(centro, "instruc");
        JPanel transPanel = new transPanel(cardLayout);
        JPanel cesarPanel = new cesarPanel(cardLayout);
        JPanel playPanel = new playPanel(cardLayout);
        JPanel vernamPanel= new VernamPanel(cardLayout);
        JPanel endtoendPanel= new EndToEndPanel(cardLayout);
        JPanel rSAPanel= new RSAPanel(cardLayout);

        centroCard.add(cesarPanel, "cesarPanel");
        centroCard.add(transPanel, "transPanel");
        centroCard.add(playPanel, "playPanel");
        centroCard.add(vernamPanel, "vernamPanel");
        centroCard.add(endtoendPanel, "endtoendPanel");
        centroCard.add(rSAPanel, "rSAPanel");
        add(centroCard, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);


        setVisible(true);
    }
    private void vernamLaunch(JPanel centroCard){
        if(JOptionPane.showConfirmDialog(this.getParent(),"Advertencia: El cifrado de Vernam funciona por medio del código de Baudot y encripta en Binario a diferencia del tradicional que funciona en ASCII y encripta en texto, debido a la limitación de caracteres del codigo de Baudot el cifrado sera mostrado en binario")==0){
            cardLayout.show(centroCard,"vernamPanel");
        }
    }
}
