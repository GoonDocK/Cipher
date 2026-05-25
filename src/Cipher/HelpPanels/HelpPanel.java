package Cipher.HelpPanels;
import AssetsHandler.iconScaller;
import GUI.ComponentCreator.NewUIComp;
import javax.swing.*;
import java.awt.*;
public class HelpPanel extends JPanel {
    protected CardLayout cardLayout;
    protected NewUIComp newUIComp;
    protected JPanel panelCentral;
    public HelpPanel(CardLayout externalPanel, String cipher, String Start, int TOTAL_IMAGES) {
        super();
        cardLayout= new CardLayout();
        newUIComp = new NewUIComp();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton back= newUIComp.newButton("Atras", "/Assets/Back.png");
        back.setPreferredSize(new Dimension(300, 50));
        back.addActionListener(e -> externalPanel.show(this.getParent(), "instruc"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInferior.add(back, gbc);
        add(panelInferior, BorderLayout.SOUTH);

        JButton button = newUIComp.newButton("Como "+cipher+" Funciona", "/Assets/Help.png");
        button.setEnabled(false);
        button.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 1;
        panelInferior.add(button, gbc);

        JButton next= newUIComp.newButton("", "/Assets/Next.png");
        next.addActionListener(e -> next());
        add(next, BorderLayout.EAST);

        JButton prev= newUIComp.newButton("", "/Assets/Previous.png");
        prev.addActionListener(e -> prev());
        add(prev, BorderLayout.WEST);


        panelCentral = new JPanel();
        panelCentral.setLayout(this.cardLayout);
        panelCentral.setBackground(Color.WHITE);



        try {
            for (int i = 1; i <= TOTAL_IMAGES; i++) {
                JPanel panel = new JPanel();
                ImageIcon helpImage = iconScaller.sprite("/Assets/HelpLabels/"+Start+i+ ".png");
                JLabel label = new JLabel(helpImage);
                panel.add(label);
                panelCentral.add(panel, String.valueOf(i));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el directorio de ayuda", "Error", JOptionPane.ERROR_MESSAGE);
        }
        add(panelCentral, BorderLayout.CENTER);
    }
    protected void next() {
        cardLayout.next(panelCentral);
    }
    protected void prev() {
        cardLayout.previous(panelCentral);
    }
}
