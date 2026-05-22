package Cipher.EndToEnd;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;

import javax.swing.*;
import java.awt.*;
public class EndToEndPanel extends JPanel {
    private final EndToEndCipher Cipher = new EndToEndCipher();
    private final NewUIComp newUIComp = new NewUIComp();
    public EndToEndPanel(CardLayout cardLayout) {
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        upperPanel.setLayout(new GridBagLayout());

        JLabel info1 = new JLabel("Texto cifrado: ");
        info1.setToolTipText("Texto a descifrar");
        info1.setFont(Fonts.SourceSansPro18Bold);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        upperPanel.add(info1, gbc);

        JLabel info2 = new JLabel("Texto descifrado: ");
        info2.setToolTipText("Texto que va a ser cifrado");
        info2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx=1;
        upperPanel.add(info2, gbc);

        JTextArea cipheredText = new JTextArea();
        cipheredText.setWrapStyleWord(true);
        cipheredText.setLineWrap(true);
        cipheredText.setFont(Fonts.SourceSansPro18);
        cipheredText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cipheredText.setRows(10);
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.gridx=0;
        gbc.gridy=1;
        upperPanel.add(cipheredText, gbc);

        JTextArea decryptedText = new JTextArea();
        decryptedText.setWrapStyleWord(true);
        decryptedText.setLineWrap(true);
        decryptedText.setFont(Fonts.SourceSansPro18);
        decryptedText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        decryptedText.setRows(10);
        gbc.gridx=1;
        gbc.insets = new Insets(0, 0, 0, 0);
        upperPanel.add(decryptedText, gbc);

        JLabel info3 = new JLabel("Constante de decimación (Original): ");
        info3.setToolTipText("Recuerde que la constante de decimación debe ser coprima con el módulo de la cifra");
        info3.setFont(Fonts.SourceSansPro18Bold);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        upperPanel.add(info3, gbc);

        JTextField a= new JTextField();
        a.setFont(Fonts.SourceSansPro18);
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx=1;
        upperPanel.add(a, gbc);

        JLabel info4 = new JLabel("Constante de desplazamiento: ");
        info4.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx=0;
        gbc.gridy=3;
        upperPanel.add(info4, gbc);

        JTextField b= new JTextField();
        b.setFont(Fonts.SourceSansPro18);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx=1;
        upperPanel.add(b, gbc);

        JLabel info5 = new JLabel("Módulo de la cifra: ");
        info5.setToolTipText("Recuerde que la constante de decimación debe ser coprima con el módulo de la cifra");
        info5.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx=0;
        gbc.gridy=4;
        upperPanel.add(info5, gbc);

        JTextField n= new JTextField();
        n.setFont(Fonts.SourceSansPro18);
        n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx=1;
        upperPanel.add(n, gbc);


        JButton decrypt= newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        decrypt.addActionListener(e -> decrypt(cipheredText,decryptedText,a,b,n, lowerPanel));
        gbc.insets = new Insets(5, 0, 0, 5);
        gbc.gridx=0;
        gbc.gridy=5;
        upperPanel.add(decrypt, gbc);

        JButton encrypt= newUIComp.newButton("Cifrar", "/Assets/Key.png");
        encrypt.addActionListener(e -> cipher(decryptedText,cipheredText,a,b,n, lowerPanel));
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridx=1;
        upperPanel.add(encrypt, gbc);

        JButton back= newUIComp.newButton("Atras","/Assets/Back.png");
        back.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.insets= new Insets(0, 0, 0, 5);
        gbc.gridx=0;
        gbc.gridy=6;
        upperPanel.add(back, gbc);

        JButton clear= newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> clear(cipheredText,decryptedText,a,b,n, lowerPanel));
        gbc.insets= new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        upperPanel.add(clear, gbc);


        add(upperPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);

    }
    private void cipher(JTextArea decryptedText, JTextArea cipheredText, JTextField a, JTextField b, JTextField n, JPanel lowerPanel){
        try{
            Cipher.setABN(a.getText(), b.getText(), n.getText());
            cipheredText.setText(Cipher.getCipheredText(decryptedText.getText()));
            lowerPanel.removeAll();
            cipheredMatrix(cipheredText, decryptedText, lowerPanel, false);
            lowerPanel.revalidate();
            lowerPanel.repaint();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getParent(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clear(JTextArea cipheredText, JTextArea decryptedText, JTextField a, JTextField b, JTextField n, JPanel lowerPanel){
        cipheredText.setText("");
        decryptedText.setText("");
        a.setText("");
        b.setText("");
        n.setText("");
        lowerPanel.removeAll();
        lowerPanel.revalidate();
        lowerPanel.repaint();
    }
    private void decrypt(JTextArea cipheredText, JTextArea decryptedText, JTextField a, JTextField b, JTextField n, JPanel lowerPanel){
        try{
            Cipher.setABN(a.getText(), b.getText(), n.getText());
            decryptedText.setText(Cipher.getDecryptedText(cipheredText.getText()));
            lowerPanel.removeAll();
            cipheredMatrix(cipheredText, decryptedText, lowerPanel, true);
            lowerPanel.revalidate();
            lowerPanel.repaint();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getParent(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cipheredMatrix(JTextArea cipheredText, JTextArea decryptedText, JPanel lowerPanel, Boolean Decryption){
        lowerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridy=0;
        for(int i=0;i<Cipher.getIndex2().length;i++){
            JPanel panel = newUIComp.matrixComponent(Cipher.getIndex2()[i]+"",20,20);
            gbc.gridx=i;
            lowerPanel.add(panel, gbc);
        }
        gbc.gridy=1;
        for(int i=0;i<Cipher.getCipheredText().length();i++){
            JPanel panel = newUIComp.matrixComponent(Cipher.getCipheredText().charAt(i)+"",20,20);
            gbc.gridx=i;
            lowerPanel.add(panel, gbc);
        }
        gbc.gridy=2;
        for(int i=0;i<Cipher.getIndex().length;i++){
            JPanel panel = newUIComp.matrixComponent(Cipher.getIndex()[i]+"",20,20);
            gbc.gridx=i;
            lowerPanel.add(panel, gbc);
        }
        gbc.gridy=3;
        for(int i=0;i<Cipher.getText().length();i++){
            JPanel panel = newUIComp.matrixComponent(Cipher.getText().charAt(i)+"",20,20);
            gbc.gridx=i;
            lowerPanel.add(panel, gbc);
        }
        if(Decryption){
            gbc.gridy=4;
            gbc.gridx=0;
            gbc.gridwidth=Cipher.getIndex().length;
            JLabel label= new JLabel("Constante de decimación inversa: "+Cipher.InverseA());
            label.setFont(Fonts.SourceSansPro18);
            lowerPanel.add(label, gbc);
        }
    }
}
