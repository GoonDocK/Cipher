package Cipher.Vernam;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;

import javax.swing.*;
import java.awt.*;

public class VernamPanel extends JPanel {
    private final VernamCipher Cipher = new VernamCipher();
    private final NewUIComp newUIComp = new NewUIComp();
    public VernamPanel(CardLayout cardLayout) {
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new GridLayout(2, 1));
        JPanel upperPanel = new JPanel();
        JScrollPane upperPanelScroll = new JScrollPane(upperPanel);
        upperPanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        upperPanelScroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel lowerPanel = new JPanel();


        lowerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel info1 = new JLabel("Texto cifrado: ");
        info1.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 0, 10);
        lowerPanel.add(info1, gbc);

        JTextArea CipheredText= new JTextArea();
        CipheredText.setWrapStyleWord(true);
        CipheredText.setLineWrap(true);
        CipheredText.setFont(Fonts.SourceSansPro18);
        CipheredText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        CipheredText.setRows(5);
        CipheredText.setColumns(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        lowerPanel.add(CipheredText, gbc);

        JLabel info2 = new JLabel("Texto descifrado: ");
        info2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 1;
        gbc.gridy = 0;
        lowerPanel.add(info2, gbc);

        JTextArea decryptedText= new JTextArea();
        decryptedText.setWrapStyleWord(true);
        decryptedText.setLineWrap(true);
        decryptedText.setFont(Fonts.SourceSansPro18);
        decryptedText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        decryptedText.setRows(5);
        decryptedText.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        lowerPanel.add(decryptedText, gbc);

        JLabel info3 = new JLabel("Clave: ");
        info3.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 2;
        gbc.gridy = 0;
        lowerPanel.add(info3, gbc);

        JTextArea key= new JTextArea();
        key.setWrapStyleWord(true);
        key.setLineWrap(true);
        key.setFont(Fonts.SourceSansPro18);
        key.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        key.setRows(5);
        key.setColumns(20);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        lowerPanel.add(key, gbc);
        gbc.gridwidth = 1;
        JButton encrypt= newUIComp.newButton("Cifrar", "/Assets/Key.png");
        encrypt.addActionListener(e -> cipher(decryptedText,key,CipheredText, upperPanel));
        gbc.gridx = 0;
        gbc.gridy = 2;
        lowerPanel.add(encrypt, gbc);

        JButton decrypt= newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        decrypt.addActionListener(e -> decrypt(CipheredText,key,decryptedText, upperPanel));
        gbc.gridx = 1;
        gbc.gridy = 2;
        lowerPanel.add(decrypt, gbc);

        JButton back= newUIComp.newButton("Atras","/Assets/Back.png");
        back.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.gridx = 2;
        gbc.gridy = 2;
        lowerPanel.add(back, gbc);

        JButton clear= newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> clear(CipheredText,key,decryptedText, upperPanel));
        gbc.gridx = 3;
        gbc.gridy = 2;
        lowerPanel.add(clear, gbc);


        add(upperPanelScroll);
        add(lowerPanel);
    }
    private void cipher(JTextArea decryptedText, JTextArea key, JTextArea CipheredText, JPanel upperPanel){
        try{
            upperPanel.removeAll();
            Cipher.setText(decryptedText.getText());
            Cipher.setKey(key.getText());
            CipheredText.setText(Cipher.getCipheredText(""));
            createUIComponents(upperPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            Cipher.clear();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    // This method creates the table that contains both key and text characters, displays its binary value and XOR operation
    private void createUIComponents(JPanel upperPanel) {
        int rows;
        if(Cipher.getKey().length()%2==0){
            rows=Cipher.getKey().length()/2;
        }else{
            rows=Cipher.getKey().length()/2+1;
        }
        upperPanel.setLayout(new GridLayout(rows, 2));
        int keyAndTextIndex=0;
        boolean decryption=false;
        String decryptedText=Cipher.getDecryptedText("");
       while(keyAndTextIndex<(Cipher.getKey().length())){
               JPanel panel= new JPanel();
               panel.setBackground(Color.WHITE);
               panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               panel.setLayout(new GridLayout(3, 6));
               for(int l=0;l<3;l++){
                   switch(l){
                       case 0:
                           panel.add(newUIComp.matrixComponent(Cipher.getKey().charAt(keyAndTextIndex)+"",50,50));
                           for(int k=0;k<5;k++){
                               panel.add(newUIComp.matrixComponent(VernamCipher.baudotToChar.get(Cipher.getKey().charAt(keyAndTextIndex)).charAt(k)+"",50,50));
                           }
                       break;
                       case 1:
                           try{
                               panel.add(newUIComp.matrixComponent(Cipher.getText().charAt(keyAndTextIndex)+"",50,50));
                               for(int k=0;k<5;k++){
                                   panel.add(newUIComp.matrixComponent(VernamCipher.baudotToChar.get(Cipher.getText().charAt(keyAndTextIndex)).charAt(k)+"",50,50));
                               }
                           }catch (NullPointerException e){
                               panel.add(newUIComp.matrixComponentIcon("/Assets/Cipher.png"));
                               for(int k=0;k<5;k++){
                                   panel.add(newUIComp.matrixComponent(Cipher.getCipheredTextBinary()[keyAndTextIndex].charAt(k)+"",50,50));
                               }
                               decryption=true;
                           }
                       break;
                       case 2:
                           if(!decryption){
                               panel.add(newUIComp.matrixComponent("XOR",50,50));
                           }else{
                               panel.add(newUIComp.matrixComponent("XOR: "+decryptedText.charAt(keyAndTextIndex),50,50));
                           }
                           for(int k=0;k<5;k++){
                               panel.add(newUIComp.matrixComponent(Cipher.getCipheredTextBinary()[keyAndTextIndex].charAt(k)+"",50,50));
                           }
                       break;
                   }
               }
               keyAndTextIndex++;
               upperPanel.add(panel);
       }
    }
    private void decrypt(JTextArea CipheredText, JTextArea key, JTextArea decryptedText, JPanel upperPanel){
        try{
            upperPanel.removeAll();
            Cipher.setCipheredText(CipheredText.getText());
            Cipher.setKey(key.getText());
            decryptedText.setText(Cipher.getDecryptedText(""));
            createUIComponents(upperPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            Cipher.clear();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void clear(JTextArea CipheredText, JTextArea key, JTextArea decryptedText, JPanel upperPanel){
        upperPanel.removeAll();
        CipheredText.setText("");
        key.setText("");
        decryptedText.setText("");
    }

}
