package Cipher.playFair;
import Cipher.Alphabet.AlphabetBuilder;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class playPanel extends JPanel {
    private final playFairCipher Cipher = new playFairCipher();
    private final NewUIComp newUIComp = new NewUIComp();
    public playPanel(CardLayout card) {
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridLayout(1, 2));
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
        cifrar.addActionListener(e -> {Cipher(textoDescifrado,panelIzquierdo,key,textoCifrado);});
        gbc.gridy=6;
        panelDerecho.add(cifrar, gbc);

        JButton descifrar = newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        gbc.gridx=1;
        panelDerecho.add(descifrar, gbc);

        JButton atras = newUIComp.newButton("Atras","/Assets/Back.png");
        atras.addActionListener(e -> {card.show(this.getParent(), "instruc");});
        gbc.gridy=7;
        gbc.gridx=0;
        panelDerecho.add(atras, gbc);

        JButton clear= newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> {clear(textoCifrado,textoDescifrado,panelIzquierdo,key);});
        gbc.gridx=1;
        panelDerecho.add(clear, gbc);


        add(panelIzquierdo,BorderLayout.WEST);
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
    private void clear(JTextArea textoCifrado, JTextArea textoDescifrado, JPanel PanelIzquiedo, JTextField key){
        textoCifrado.setText("");
        textoDescifrado.setText("");
        PanelIzquiedo.removeAll();
        key.setText("");
        createMatrix(PanelIzquiedo);
        PanelIzquiedo.revalidate();
        PanelIzquiedo.repaint();
    }
    private void Cipher(JTextArea textoDescifrado, JPanel PanelIzquiedo, JTextField key, JTextArea textoCifrado){
        Cipher.setKey(key.getText());
        Cipher.getTextCorrected(textoDescifrado.getText());
        createCipherMatrix(PanelIzquiedo);
        textoCifrado.setText(Cipher.getCipheredText(""));
    }
}
