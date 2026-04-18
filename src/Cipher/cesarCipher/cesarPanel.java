package Cipher.cesarCipher;
import Miscellaneous.Fonts;
import GUI.ComponentCreator.NewUIComp;
import javax.swing.*;
import java.awt.*;

public class cesarPanel extends JPanel {
    private JPanel panelInferior= new JPanel();
    private final NewUIComp newUIComp = new NewUIComp();
    private final cesarCipher Cipher = new cesarCipher();
    public cesarPanel(CardLayout cardLayout) {

        setLayout(new BorderLayout());

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panelCentral = new JPanel(gridBagLayout);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel text1 = new JLabel("Texto descifrado: ");
        text1.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCentral.add(text1, gbc);

        JLabel text2= new JLabel("Texto Cifrado:");
        text2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx=1;
        panelCentral.add(text2, gbc);

        JTextArea textoDescifrado= new JTextArea();
        textoDescifrado.setWrapStyleWord(true);
        textoDescifrado.setLineWrap(true);
        textoDescifrado.setFont(Fonts.SourceSansPro18);
        textoDescifrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textoDescifrado.setRows(10);
        gbc.gridy=1;
        gbc.gridx=0;

        panelCentral.add(textoDescifrado, gbc);

        JTextArea textoCifrado= new JTextArea();
        textoCifrado.setWrapStyleWord(true);
        textoCifrado.setLineWrap(true);
        textoCifrado.setFont(Fonts.SourceSansPro18);
        textoCifrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textoCifrado.setRows(10);
        gbc.gridx=1;

        gbc.gridwidth=1;

        panelCentral.add(textoCifrado, gbc);

        JButton Descifrar=newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        Descifrar.addActionListener(e -> {uncipheredMatrix(textoDescifrado,textoCifrado);});
        gbc.gridy=2;
        gbc.gridx=0;
        panelCentral.add(Descifrar, gbc);

        JButton Cifrar=newUIComp.newButton("Cifrar", "/Assets/Key.png");
        Cifrar.addActionListener(e -> {createMatrix(textoDescifrado,textoCifrado);});
        gbc.gridx=1;
        panelCentral.add(Cifrar, gbc);

        gbc.gridy=3;
        JButton atras=newUIComp.newButton("Atras","/Assets/Back.png");
        atras.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.gridx=0;
        panelCentral.add(atras, gbc);

        JButton clear=newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> {clear(textoDescifrado,textoCifrado);});
        gbc.gridx=1;
        panelCentral.add(clear, gbc);

        panelInferior.setLayout(new GridBagLayout());
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane scrollPane = new JScrollPane(panelInferior);
        add(scrollPane, BorderLayout.SOUTH);


        add(panelCentral, BorderLayout.CENTER);
    }
    private void createMatrix(JTextArea TextoDescifrado, JTextArea TextoCifrado){
        panelInferior.removeAll();
        GridBagConstraints gbc2 = new GridBagConstraints();
        Cipher.setCorrectedText(TextoDescifrado.getText());
        for(int i=0;i<TextoDescifrado.getText().length();i++){
            gbc2.gridx=i;
            JPanel panel= newUIComp.matrixComponent(Cipher.getTextCorrected(TextoDescifrado.getText().charAt(i)+""),50,50);
            panelInferior.add(panel, gbc2);
        }
        gbc2.gridy=2;
        String textoCifradoAux=Cipher.getCipheredText(TextoDescifrado.getText());
        for(int i=0;i<textoCifradoAux.length();i++){
            gbc2.gridx=i;
            JPanel panel= newUIComp.matrixComponent(textoCifradoAux.charAt(i)+"",50,50);
            panelInferior.add(panel, gbc2);
        }
        TextoCifrado.setText(textoCifradoAux);
        revalidate();
        repaint();
    }
    private void uncipheredMatrix(JTextArea TextoDescifrado, JTextArea TextoCifrado){
        panelInferior.removeAll();
        Cipher.setCorrectedText(TextoCifrado.getText());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridy=0;
        String textoDescifradoAux=Cipher.getDecryptedText(TextoCifrado.getText());
        TextoDescifrado.setText(textoDescifradoAux);
        for(int i=0;i<textoDescifradoAux.length();i++){
            gbc2.gridx=i;
            JPanel panel= newUIComp.matrixComponent(textoDescifradoAux.charAt(i)+"",50,50);
            panelInferior.add(panel, gbc2);
        }
        gbc2.gridy=1;
        for(int i=0;i<TextoCifrado.getText().length();i++){
            gbc2.gridx=i;
            JPanel panel= newUIComp.matrixComponent(TextoCifrado.getText().charAt(i)+"",50,50);
            panelInferior.add(panel, gbc2);
        }
        panelInferior.revalidate();
        panelInferior.repaint();
    }
    private void clear(JTextArea TextoDescifrado, JTextArea TextoCifrado){
        TextoDescifrado.setText("");
        TextoCifrado.setText("");
        panelInferior.removeAll();
        revalidate();
        repaint();
    }
}
