package Cipher.playFair;
import Cipher.Alphabet.AlphabetBuilder;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class playPanel extends JPanel {
    private final playFairCipher Cipher = new playFairCipher();
    private final NewUIComp newUIComp = new NewUIComp();
    public playPanel(CardLayout card) {
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridLayout(1, 2));

        JPanel alterPanelIzquierdo = new JPanel();
        alterPanelIzquierdo.setLayout(new BorderLayout());

        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));



        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridBagLayout());
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        createMatrix(panelIzquierdo);

        JLabel info1 = new JLabel("Texto cifrado: ");
        info1.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.gridwidth=2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDerecho.add(info1, gbc);

        JTextArea textoCifrado= new JTextArea();
        textoCifrado.setWrapStyleWord(true);
        textoCifrado.setLineWrap(true);
        textoCifrado.setFont(Fonts.SourceSansPro18);
        textoCifrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textoCifrado.setRows(10);
        gbc.gridy=1;
        gbc.gridx=0;
        panelDerecho.add(textoCifrado, gbc);

        JLabel info2 = new JLabel("Texto descifrado: ");
        info2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy=2;
        panelDerecho.add(info2, gbc);

        JTextArea textoDescifrado= new JTextArea();
        textoDescifrado.setWrapStyleWord(true);
        textoDescifrado.setLineWrap(true);
        textoDescifrado.setFont(Fonts.SourceSansPro18);
        textoDescifrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textoDescifrado.setRows(10);
        gbc.gridy=3;
        panelDerecho.add(textoDescifrado, gbc);

        JLabel info3 = new JLabel("Palabra clave: ");
        info3.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy=4;
        panelDerecho.add(info3, gbc);

        JTextField key = new JTextField(){
            @Override
            public void setBorder(Border border) {
                super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        };
        key.setFont(Fonts.SourceSansPro18);
        key.setColumns(20);
        gbc.gridy=5;
        panelDerecho.add(key, gbc);

        gbc.gridwidth=1;

        JButton cifrar= newUIComp.newButton("Cifrar", "/Assets/Key.png");
        cifrar.addActionListener(e -> {Cipher(textoDescifrado,panelIzquierdo,key,textoCifrado, panelInferior);});
        gbc.gridy=6;
        panelDerecho.add(cifrar, gbc);

        JButton descifrar = newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        descifrar.addActionListener(e -> {deCipher(textoDescifrado,panelIzquierdo,key,textoCifrado, panelInferior );});
        gbc.gridx=1;
        panelDerecho.add(descifrar, gbc);

        JButton atras = newUIComp.newButton("Atras","/Assets/Back.png");
        atras.addActionListener(e -> {card.show(this.getParent(), "instruc");});
        gbc.gridy=7;
        gbc.gridx=0;
        panelDerecho.add(atras, gbc);

        JButton clear= newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> {clear(textoCifrado,textoDescifrado,panelIzquierdo,key, panelInferior);});
        gbc.gridx=1;
        panelDerecho.add(clear, gbc);
        alterPanelIzquierdo.add(panelInferior,BorderLayout.SOUTH);
        alterPanelIzquierdo.add(panelIzquierdo,BorderLayout.CENTER);
        add(alterPanelIzquierdo,BorderLayout.WEST);
        add(panelDerecho,BorderLayout.EAST);
    }
    private void createMatrix(JPanel PanelIzquiedo){
        GridBagConstraints gbc = new GridBagConstraints();
        char[] Alphabet = AlphabetBuilder.build();
        JPanel[][] matrix= new JPanel[5][5];
        int h=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(Alphabet[h]!='I'){
                    matrix[i][j]= newUIComp.matrixComponent(Alphabet[h]+"",60,60);
                    h++;
                }else{
                    matrix[i][j]= newUIComp.matrixComponent("I/J",60,60);
                    h+=2;
                }
                gbc.gridx=j;
                gbc.gridy=i;
                PanelIzquiedo.add(matrix[i][j], gbc);
            }
        }
        PanelIzquiedo.revalidate();
        PanelIzquiedo.repaint();
    }
    private void createCipherMatrix(JPanel PanelIzquiedo){
        PanelIzquiedo.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        String Matrix=Cipher.getMatrix();
        JPanel[][] matrix= new JPanel[5][5];
        int h=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(Matrix.charAt(h)!='I'){
                    matrix[i][j]= newUIComp.matrixComponent(Matrix.charAt(h)+"",60,60);
                }else{
                    matrix[i][j]= newUIComp.matrixComponent("I/J",60,60);
                }
                h++;
                gbc.gridx=j;
                gbc.gridy=i;
                PanelIzquiedo.add(matrix[i][j], gbc);
            }
        }
        PanelIzquiedo.revalidate();
        PanelIzquiedo.repaint();
    }
    private void clear(JTextArea textoCifrado, JTextArea textoDescifrado, JPanel PanelIzquiedo, JTextField key, JPanel panelInferior){
        textoCifrado.setText("");
        textoDescifrado.setText("");
        PanelIzquiedo.removeAll();
        key.setText("");
        createMatrix(PanelIzquiedo);
        PanelIzquiedo.revalidate();
        PanelIzquiedo.repaint();
        panelInferior.removeAll();
        panelInferior.revalidate();
        panelInferior.repaint();
    }
    private void deCipher(JTextArea textoDescifrado, JPanel PanelIzquiedo, JTextField key, JTextArea textoCifrado, JPanel panelInferior){
        try{
            Cipher.setKey(key.getText());
            Cipher.getTextCorrected(textoCifrado.getText());
            createCipherMatrix(PanelIzquiedo);
            textoDescifrado.setText(Cipher.getDecryptedText(""));
            createGroups(panelInferior);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getParent(),e.getMessage());
        }
    }
    private void Cipher(JTextArea textoDescifrado, JPanel PanelIzquiedo, JTextField key, JTextArea textoCifrado, JPanel panelInferior){
        try{
            Cipher.setKey(key.getText());
            Cipher.getTextCorrected(textoDescifrado.getText());
            createCipherMatrix(PanelIzquiedo);
            textoCifrado.setText(Cipher.getCipheredText(""));
            createGroups(panelInferior);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getParent(),e.getMessage());
        }
    }
    private void createGroups(JPanel panelInferior){
        panelInferior.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        panelInferior.setLayout(new GridBagLayout());
        ArrayList<String> division= Cipher.getDivision();
        int k=0;
        for (String s : division) {
            gbc.insets=new Insets(0,0,0,0);
            for (int j = 0; j < 2; j++) {
                if(j==1){
                    gbc.insets=new Insets(0,0,0,10);
                }
                JPanel panel = newUIComp.matrixComponent(s.charAt(j) + "", 50, 50);
                gbc.gridx = k;
                panelInferior.add(panel, gbc);
                k++;
            }
        }
        panelInferior.revalidate();
        panelInferior.repaint();
    }
}
